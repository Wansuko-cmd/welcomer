package com.wsr.model.h2

import com.typesafe.config.ConfigFactory
import com.wsr.model.h2.entities.SentMessage
import com.wsr.model.h2.entities.User
import com.wsr.model.h2.tables.SentMessages
import com.wsr.model.h2.tables.Users
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.config.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DBController {

    private val appConfig = HoconApplicationConfig(ConfigFactory.load())
    private val driverClass = appConfig.property("db.driverClass").getString()
    private val jdbcUrl = appConfig.property("db.jdbcUrl").getString()
    private val dbUser = appConfig.property("db.user").getString()
    private val dbPassword = appConfig.property("db.password").getString()

    fun init(){

        //H2のセットアップ
        Database.connect(hikari())

        transaction {
            SchemaUtils.create(Users)
            SchemaUtils.create(SentMessages)
        }
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig().apply {
            driverClassName = driverClass
            jdbcUrl = DBController.jdbcUrl
            username = dbUser
            password = dbPassword
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }
        return HikariDataSource(config)
    }

    fun seeding(){
        transaction {
            val isExistUser = User.all().count() > 0

            if (!isExistUser) {

                User.new {
                    this.userId = "Jetbrains"
                }
                User.new {
                    this.userId = "Google"
                }
            }
        }

        transaction {

            val isExistSentMessage = SentMessage.all().count() > 0

            if(!isExistSentMessage){
                SentMessage.new {
                    this.userId = "Kotlin"
                    this.comingMessage = "Kotlin is good!"
                    this.reply = "Year"
                }

                SentMessage.new {
                    this.userId = "Java"
                    this.comingMessage = "Java is better tha Kotlin"
                    this.reply = "..."
                }
            }
        }
    }
}
