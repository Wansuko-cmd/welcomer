package com.wsr.services

import com.wsr.entities.Message

object Service{

    /**
     * メインの関数
     * ここでどの関数を利用するかを指定する
     */
    fun setMessage(action: String? = null): Message {

        return when (action) {
            "Hello World" -> sayHelloWorld()
            null -> elseMessage()
            else -> elseMessage()
        }
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
