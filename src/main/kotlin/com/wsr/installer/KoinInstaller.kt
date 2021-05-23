package com.wsr.installer


import io.ktor.application.*
import org.koin.dsl.module
import org.koin.ktor.ext.Koin

fun Application.koinInstaller(){

    install(Koin){
        module {

        }
    }
}
