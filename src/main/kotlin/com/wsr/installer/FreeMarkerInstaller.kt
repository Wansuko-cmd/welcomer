package com.wsr.installer

import freemarker.cache.ClassTemplateLoader
import freemarker.core.HTMLOutputFormat
import io.ktor.application.*
import io.ktor.freemarker.*

/**
 * テンプレートエンジン「FreeMarker」のインストール
 */
fun Application.freeMarkerInstaller() {

    //FreeMakerのインストール
    install(FreeMarker){
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
        outputFormat = HTMLOutputFormat.INSTANCE
    }
}
