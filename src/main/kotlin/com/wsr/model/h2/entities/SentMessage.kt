package com.wsr.model.h2.entities

import com.wsr.model.h2.tables.SentMessages
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SentMessage(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SentMessage>(SentMessages)

    var userId by SentMessages.userId
    var comingMessage by SentMessages.comingMessage
    var reply by SentMessages.reply
    var createdAt by SentMessages.createdAt
}
