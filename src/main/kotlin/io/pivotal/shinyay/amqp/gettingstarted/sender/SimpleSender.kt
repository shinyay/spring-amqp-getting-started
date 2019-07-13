package io.pivotal.shinyay.amqp.gettingstarted.sender

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import java.util.*

@Configuration
@EnableScheduling
class SimpleSender(val rabbitTemplate: RabbitTemplate) : CommandLineRunner {

    val queueNameHello = "queue-hello"

    @Bean
    fun queueForHelloToSend(): Queue = Queue(queueNameHello, false)

    override fun run(vararg args: String?) {
        val sendTime = Date().toString()
        rabbitTemplate.convertAndSend(queueNameHello, ">> Hello, RabbitMQ! by CommandLineRunner at $sendTime")
    }

    @Scheduled(fixedDelay = 5000)
    fun sendMessage() {
        val sendTime = Date().toString()
        rabbitTemplate.convertAndSend(queueNameHello, ">> Hello, RabbitMQ! by EnabledScheduling at $sendTime")
    }
}