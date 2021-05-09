package com.wsr.services

import com.typesafe.config.ConfigFactory
import com.wsr.model.Message
import com.wsr.model.h2.entities.User
import com.wsr.model.h2.tables.Users
import com.wsr.model.slack.Action
import com.wsr.model.slack.Event
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.config.*
import kotlinx.coroutines.launch
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object SendMessageService{

    //Postを投げるためのクライアントのインストール
    private val client = HttpClient(CIO){
        install(JsonFeature)
    }

    //Postを飛ばす先を設定ファイルから読み込む処理
    private val appConfig = HoconApplicationConfig(ConfigFactory.load())
    private val url = appConfig.property("slack.url").getString()

    fun sendMessage(action: Action): Message?{
        return when (action.event.type) {
            "app_mention" -> makeReplyMessage(action.event.text)
            "member_joined_channel" -> makeIntroductionMessage(action.event.user)
            else -> null
        }
    }

    private fun makeReplyMessage(text: String?): Message?{
        if(text == null) return null

        return Message("Hello World")
    }

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

            return Message("""
            <@${userId}> Slackへの参加ありがとう！ せっかくなので，簡単な自己紹介をしてもらえると嬉しいです！
            ```
            ニックネーム or 本名
            学部学科
            趣味
            興味のある分野
            どこでこのサークルを知ったか
            ひとことコメント
            ```
            この辺を書いてもらえると！
            あと、急ぎの連絡とかもあるので、Slackのモバイルアプリをインストールしておいてください！
            """.trimIndent())
        }

        println("Already Exist")
        return null
    }
}
