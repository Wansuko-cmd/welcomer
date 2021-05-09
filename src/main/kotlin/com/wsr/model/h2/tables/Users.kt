package com.wsr.model.h2.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Users: IntIdTable() {
    val userId = varchar("user_id", 50).uniqueIndex()
}
