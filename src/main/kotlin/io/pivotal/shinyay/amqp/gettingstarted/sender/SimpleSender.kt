package io.pivotal.shinyay.amqp.gettingstarted.sender

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SimpleSender(val rabbitTemplate: RabbitTemplate) : CommandLineRunner {

    val queueNameHello = "queue-hello"

    @Bean
    fun queueForHelloToSend(): Queue = Queue(queueNameHello, false)

    override fun run(vararg args: String?) {
        rabbitTemplate.convertAndSend(queueNameHello, ">> Hello, RabbitMQ! from SimpleSender")
    }


}