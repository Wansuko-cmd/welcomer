package com.wsr.modules

import com.wsr.model.json.i10jan.I10jan
import com.wsr.model.json.i10jan.NickName
import com.wsr.services.i10jan.I10janInterface

class I10janTestService : I10janInterface {
    override suspend fun getI10janResult(): I10jan {
        return I10jan(true, listOf(NickName("いけちぃ"), NickName("オキリョウ")))
    }
}
