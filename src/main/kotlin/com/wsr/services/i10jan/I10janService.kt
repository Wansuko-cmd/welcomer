package com.wsr.services.i10jan

import com.typesafe.config.ConfigFactory
import com.wsr.model.json.i10jan.I10jan
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.config.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object I10janService : I10janInterface {

    //Postを投げるためのクライアントのインストール
    private val client = HttpClient(CIO){
        install(JsonFeature)
    }

    //Postを飛ばす先を設定ファイルから読み込む処理
    private val appConfig = HoconApplicationConfig(ConfigFactory.load())

    private val url = appConfig.property("i10jan.url").getString()

    override suspend fun getI10janResult(): I10jan = withContext(Dispatchers.IO){

        try {
            return@withContext client.get<I10jan>(url)
        } catch (e: Exception) {
            return@withContext I10jan(false, listOf())
        }
    }
}
