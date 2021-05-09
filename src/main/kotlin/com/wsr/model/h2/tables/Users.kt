package com.wsr.model.h2.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime

object Users: IntIdTable() {
    val userId = varchar("user_id", 50).uniqueIndex()
    val createdAt = datetime("created_at").clientDefault { DateTime.now() }
}
