package io.pivotal.shinyay.amqp.gettingstarted

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class Runner(val rabbitTemplate: RabbitTemplate, val receiver: Receiver) : CommandLineRunner {

    override fun run(vararg args: String?) {
        println(">> Sending message.....")
        rabbitTemplate.convertAndSend(GettingStartedApplication.topicExchangeName, "foo.bar.baz", "Hello, RabbitMQ!")
        receiver.latch.await(10000, TimeUnit.MILLISECONDS)
    }
}