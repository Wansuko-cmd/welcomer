package com.wsr.installer

import io.ktor.application.*
import org.koin.core.module.Module

/**
 * インストーラーをまとめるための関数
 */
fun Application.mainInstaller(testing: Boolean = false, testModule: Module? = null){

    authenticationInstaller()
    serializerInstaller()
    koinInstaller(testing, testModule)
    freeMarkerInstaller()

}
