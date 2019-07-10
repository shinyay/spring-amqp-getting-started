package io.pivotal.shinyay.amqp.gettingstarted.sender

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class SimpleSender(val rabbitTemplate: RabbitTemplate) : CommandLineRunner {

    val queueNameHello = "queue-hello"

    @Bean
    fun queueForHello(): Queue = Queue(queueNameHello, false)

    override fun run(vararg args: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}