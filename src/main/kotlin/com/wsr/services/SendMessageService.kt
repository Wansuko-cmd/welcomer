package com.wsr.services

import com.typesafe.config.ConfigFactory
import com.wsr.model.json.Message
import com.wsr.model.db.DBController
import com.wsr.model.db.entities.User
import com.wsr.model.db.tables.Users
import com.wsr.model.json.slack.Action
import com.wsr.services.i10jan.I10janInterface
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.config.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.KoinComponent
import org.koin.core.inject

class SendMessageService : KoinComponent{

    //Postを投げるためのクライアントのインストール
    private val client = HttpClient(CIO){
        install(JsonFeature)
    }

    //Postを飛ばす先を設定ファイルから読み込む処理
    private val appConfig = HoconApplicationConfig(ConfigFactory.load())


    private val dbController by inject<DBController>()
    private val i10janService by inject<I10janInterface>()



    /**
     * イベントに応じて叩く関数を指定する関数
     * null -> 送信しない
     */
    fun makeReply(action: Action): Message? {
        if (action.event.user == null) return null

        val sendMessage = when (action.event.type) {
            "app_mention" -> makeReplyMessage(action.event.text)
            "member_joined_channel" -> makeIntroductionMessage(action.event.user)
            else -> null
        }

        sendMessage?.let { message ->
            dbController.makeSentMessageHistory(
                action.event.user,
                action.event.text ?: "${action.event.user} がチームに参加",
                message.text
            )
        }

        return sendMessage
    }

    /**
     * 指定されたurlへメッセージを送信
     */
    suspend fun sendMessage(message: Message) = withContext(Dispatchers.Default) {

        val url = appConfig.property("slack.url").getString()


        //メッセージを送信する処理
        launch {
            client.post(url) {
                headers {
                    append(io.ktor.http.HttpHeaders.ContentType, "application/json")
                }

                //メッセージ本文
                body = message
            }
        }.join()

        println("Send")
    }

    /**
     * アプリがメンションされた際に返すメッセージを作成する関数
     */
    private fun makeReplyMessage(text: String?): Message?{

        if(text == null) return null

        val getMessage = text.removePrefix("<@U0211DXNPGE>").trim()

        println(getMessage)

        val replyText = when(getMessage){
            "Hello World" -> "はろーわーるど"
            "部室" -> getI10janResult()
            "" -> "https://ja.wikipedia.org/wiki/Kotlin"
            else -> getMessage
        }

        return Message(replyText)
    }

    /**
     * 新たなチームメンバーが入ってきたときに紹介文を用意するか決める関数
     */
    private fun makeIntroductionMessage(userId: String): Message?{

        //データベースに、既に紹介文を送信したユーザーとして登録されているかどうか
        var isExist = false

        transaction {
            isExist = User.find { Users.userId eq userId }.count() > 0
        }

        if(!isExist){

            //データベースの、紹介文を送信したユーザーに登録
            transaction {
                User.new {
                    this.userId = userId
                }
            }

            //登録したことをログに吐く
            println("Registered")

            return Message("<@${userId}> " + appConfig.property("slack.introduction").getString())
        }

        println("Already Exist")
        return null
    }

    private fun getI10janResult(): String = runBlocking{


        try{

            //メッセージを送信する処理
            val result = i10janService.getI10janResult()

            return@runBlocking when{
                !result.success ->"取得に失敗しました☆"
                result.data.count() <= 0 -> "部室には誰もいないです"
                else -> {
                    var text = "部室には今"
                    result.data.forEach {
                        text += "${it.nickName}と"
                    }
                    if(text.endsWith("と")) text = text.dropLast(1)
                    text + "がいますよ！"
                }
            }

        }catch (e: Exception){
            return@runBlocking "取得に失敗しました☆"
        }
    }
}
