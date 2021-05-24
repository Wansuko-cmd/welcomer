package com.wsr.model.db

import com.typesafe.config.ConfigFactory
import com.wsr.model.db.entities.SentMessage
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.config.*
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * データベースを操作するためのクラス
 */
class DBController {

    private val appConfig = HoconApplicationConfig(ConfigFactory.load())
    private val driverClass = appConfig.property("db.driverClass").getString()
    private val jdbcUrl = appConfig.property("db.jdbcUrl").getString()
    private val dbUser = appConfig.property("db.user").getString()
    private val dbPassword = appConfig.property("db.password").getString()

    /**
     * データベースへのマイグレーションおよび接続を行う関数
     */
    fun init(){
        Flyway.configure()
            .dataSource(jdbcUrl, dbUser, dbPassword)
            .load()
            .migrate()
        Database.connect(hikari())
    }

    /**
     * コネクションプールをするための設定を作成する関数
     */
    private fun hikari(): HikariDataSource {
        val config = HikariConfig().apply {
            driverClassName = driverClass
            jdbcUrl = this@DBController.jdbcUrl
            username = dbUser
            password = dbPassword
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }
        return HikariDataSource(config)
    }

    /**
     * メッセージ送信履歴を作成するための関数
     */
    fun makeSentMessageHistory(user: String, comingMessage: String, reply: String){
        transaction {
            SentMessage.new {
                this.userId = user
                this.comingMessage = comingMessage
                this.reply = reply
            }
        }
    }
}
