package com.wsr.model.db.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime

/**
 * 紹介文を送信したユーザーを保存するためのテーブル
 *
 * userId: ユーザーID
 * createdAt: 送信した時間
 */
object Users: IntIdTable() {
    val userId = varchar("user_id", 50).uniqueIndex()
    val createdAt = datetime("created_at").clientDefault { DateTime.now() }
}
