package com.wsr.installer

import io.ktor.application.*

/**
 * インストーラーをまとめるための関数
 */
fun Application.mainInstaller(){

    authenticationInstaller()
    serializerInstaller()
    koinInstaller()
    freeMarkerInstaller()

}
