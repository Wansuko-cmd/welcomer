package com.wsr.model.db.entities

import com.wsr.model.db.tables.Users
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

/**
 * 紹介文を送信したユーザーを保存するためのエンティティ
 *
 * userId: ユーザーID
 * createdAt: 送信した時間
 */
class User(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<User>(Users)

    var userId by Users.userId
    var createdAt by Users.createdAt
}
