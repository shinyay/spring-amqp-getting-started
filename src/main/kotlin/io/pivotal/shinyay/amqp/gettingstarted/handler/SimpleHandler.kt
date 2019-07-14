package io.pivotal.shinyay.amqp.gettingstarted.handler

import org.springframework.stereotype.Component
import java.util.concurrent.CountDownLatch

@Component
class SimpleHandler {

    val latch: CountDownLatch = CountDownLatch(1)

    fun handleMessage(message: String) {
        println(">>> Received: $message")
        latch.countDown()
    }
}