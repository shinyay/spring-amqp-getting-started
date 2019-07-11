package io.pivotal.shinyay.amqp.gettingstarted.handler

class SimpleHandler {

    fun handleMessage(message: String) {
        println(">>> Received: $message")
    }
}