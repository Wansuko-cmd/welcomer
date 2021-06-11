package com.wsr.services.send_message

import com.wsr.model.json.message.Message
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


}
