package com.wsr.modules

import com.wsr.model.json.message.Message
import com.wsr.model.json.slack.Action
import com.wsr.services.send_message.SendMessageInterface

class SendMessageTestService : SendMessageInterface {
    override fun makeReply(action: Action): Message? {
        TODO("Not yet implemented")
    }

    override suspend fun sendMessage(message: Message) {
        TODO("Not yet implemented")
    }
}
