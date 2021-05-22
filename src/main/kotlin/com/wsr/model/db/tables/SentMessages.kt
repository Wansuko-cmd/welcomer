package com.wsr.model.db.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime

object SentMessages : IntIdTable() {
    val userId = varchar("user_id", 50)
    val comingMessage = text("coming_message")
    val reply = text("reply")
    val createdAt = datetime("created_at").clientDefault { DateTime.now() }
}
