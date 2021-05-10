package com.wsr.routings.api

import com.wsr.model.h2.entities.SentMessage
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

fun Route.apiRoute(){

    /**
     * 今日から２日前後のメッセージ送信数を返すAPI
     */
    get("/api/message/dayToCount"){

        //DBに蓄えられているメッセージ履歴
        var sentMessages = listOf<SentMessage>()

        //本日から２日前後の日付を持つ
        val today = LocalDateTime.now().dayOfMonth
        val dayList = today-2..today+2

        //一時的にカウント数を代入するための変数
        var counter: Int

        //返り値
        val dayToCount = mutableListOf<Map<Int, Int>>()

        //データベースに登録されているユーザーを抽出
        transaction {
            sentMessages = SentMessage.all().toList()
        }

        //履歴の作成された日付ごとにグループ分けをする
        val messageGroupByDay = sentMessages
            .groupBy { it.createdAt.toLocalDateTime().dayOfMonth }

        //２日前後のものだけにする、また一切履歴の存在しない日には０を入れる
        dayList.forEach {
            counter = messageGroupByDay[it]?.count() ?: 0
            dayToCount.add(mapOf(it to counter))
        }

        call.respond(dayToCount)
    }
}
