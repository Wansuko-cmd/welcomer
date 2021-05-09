package com.wsr.model.h2.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime

object SentMessages : IntIdTable() {
    val userId = varchar("user_id", 50)
    val message = text("message")
    val createdAt = datetime("created_at").clientDefault { DateTime.now() }
}
