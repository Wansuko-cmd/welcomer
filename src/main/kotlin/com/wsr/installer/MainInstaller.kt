package com.wsr.installer

import io.ktor.application.*

fun Application.mainInstaller(){

    authenticationInstaller()
    serializerInstaller()
    koinInstaller()
    freeMarkerInstaller()

}
