package com.wsr.services.i10jan

import com.wsr.model.json.i10jan.I10jan

/**
 * I10janのAPIを叩く機能を実装するためのインターフェイス
 */
interface I10janInterface{

    suspend fun getI10janResult(): I10jan
}
