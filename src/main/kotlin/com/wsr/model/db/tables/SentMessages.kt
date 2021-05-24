package com.wsr.model.db.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime

/**
 * 送られてきたメッセージと送信したメッセージを保存するためのテーブル
 *
 * userId: 送ってきたユーザーのID
 * comingMessage: 送られてきたメッセージの内容
 * reply: 送信したメッセージ
 * createdAt: 送信した時間
 */
object SentMessages : IntIdTable() {
    val userId = varchar("user_id", 50)
    val comingMessage = text("coming_message")
    val reply = text("reply")
    val createdAt = datetime("created_at").clientDefault { DateTime.now() }
}
