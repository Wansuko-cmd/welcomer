package com.wsr.services.send_message

import com.wsr.model.json.Message
import com.wsr.model.json.slack.Action

interface SendMessageInterface {

    /**
     * イベントに応じて叩く関数を指定する関数
     * null -> 送信しない
     */
    fun makeReply(action: Action): Message?



    /**
     * 指定されたurlへメッセージを送信
     */
    suspend fun sendMessage(message: Message)



    /**
     * アプリがメンションされた際に返すメッセージを作成する関数
     */
    fun makeReplyMessage(text: String?): Message?



    /**
     * 新たなチームメンバーが入ってきたときに紹介文を用意するか決める関数
     */
    fun makeIntroductionMessage(userId: String): Message?



    /**
     * 部室に人がいるかどうかを判断してメッセージを作成する関数
     */
    fun getI10janResult(): String
}
