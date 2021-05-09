package com.wsr.services

import com.wsr.model.Message

object Service{

    /**
     * メインの関数
     * ここでどの関数を利用するかを指定する
     */
    fun setMessage(action: String? = null): Message {

        return Message("""
            <@${action}> Slackへの参加ありがとう！ せっかくなので，簡単な自己紹介をしてもらえると嬉しいです！
            ```
            ニックネーム or 本名
            学部学科
            趣味
            興味のある分野
            どこでこのサークルを知ったか
            ひとことコメント
            ```
            この辺を書いてもらえると！
            あと、急ぎの連絡とかもあるので、Slackのモバイルアプリをインストールしておいてください！
            """.trimIndent())

//        return when (action) {
//            "Hello World" -> sayHelloWorld()
//            null -> elseMessage()
//            else -> elseMessage()
//        }
    }

    /**
     * アクション：Hello World
     * 返り値：Hello World
     */
    private fun sayHelloWorld(): Message{
        return Message("Hello World")
    }

    /**
     * アクション：どれとも一致しない
     * 返り値：Say again please?
     */
    private fun elseMessage(): Message{
        return Message("Say again please?")
    }
}