package io.pivotal.shinyay.amqp.gettingstarted.handler

import org.springframework.stereotype.Component

@Component
class SimpleHandler {

    fun handleMessage(message: String) {
        println(">>> Received: $message")
    }
}