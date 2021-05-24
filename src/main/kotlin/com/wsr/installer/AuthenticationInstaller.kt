package com.wsr.installer

import com.typesafe.config.ConfigFactory
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.config.*

/**
 * 認証機能のインストール
 */
fun Application.authenticationInstaller() {

    //Postを飛ばす先を設定ファイルから読み込む処理
    val appConfig = HoconApplicationConfig(ConfigFactory.load())

    val user = appConfig.property("account.user").getString()
    val password = appConfig.property("account.password").getString()

    install(Authentication){
        basic("auth-basic") {
            realm = "Access to the '/' path"
            validate { credentials ->
                if (credentials.name == user && credentials.password == password) {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
        }
    }

}
