package com.wsr.services

import com.wsr.model.Message
import com.wsr.model.h2.entities.SentMessage
import com.wsr.model.h2.entities.User
import com.wsr.model.h2.tables.SentMessages
import com.wsr.model.h2.tables.Users
import com.wsr.model.slack.Action
import com.wsr.model.slack.Event
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.exists
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object SendMessageService{

    /**
     * イベントに応じて叩く関数を指定する関数
     * null -> 送信しない
     */
    fun sendMessage(action: Action): Message? {
        if (action.event.user == null) return null

        val sendMessage = when (action.event.type) {
            "app_mention" -> makeReplyMessage(action.event.text)
            "member_joined_channel" -> makeIntroductionMessage(action.event.user)
            else -> null
        }

        sendMessage?.let { message ->
            transaction {
                SentMessage.new {
                    this.userId = action.event.user
                    this.message = message.text
                }
            }
        }

        return sendMessage
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
