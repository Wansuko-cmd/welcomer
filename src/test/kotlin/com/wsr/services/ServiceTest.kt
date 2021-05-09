package com.wsr.services

import org.junit.Assert
import org.junit.Test

class ServiceTest {

    /**
     * アクション：Hello World
     * 返り値：Hello World
     */
    @Test
    fun helloTest(){
        val text = SendMessageService.setMessage("Hello World").text
        Assert.assertEquals("Hello World", text)
    }

    /**
     * アクション：どれとも一致しない
     * 返り値：Say again please?
     */
    @Test
    fun elseTest(){
        val text = SendMessageService.setMessage("Else").text
        Assert.assertEquals("Say again please?", text)
    }
}
