package com.wsr.routings.api

import com.wsr.model.db.entities.SentMessage
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

/**
 * API関連のルートを定義
 */
fun Route.apiRoute(){

    /**
     * 今日から4日前までのメッセージ送信数を返すAPI
     */
    get("/api/message/dayToCount"){

        //DBに蓄えられているメッセージ履歴
        var sentMessages = listOf<SentMessage>()

        //本日から4日前までの日付を持つ
        val today = LocalDateTime.now().dayOfMonth
        val dayList = today - 4..today

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
