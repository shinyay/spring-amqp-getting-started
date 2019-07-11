package io.pivotal.shinyay.amqp.gettingstarted.receiver

import io.pivotal.shinyay.amqp.gettingstarted.handler.SimpleHandler
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SimpleReceiver : CommandLineRunner {

    val queueNameHello = "queue-hello"
    val topicNameHello = "topic-hello"

    @Bean
    fun queue(): Queue = Queue(queueNameHello, false)

    @Bean
    fun exchange(): TopicExchange = TopicExchange(topicNameHello)

    @Bean
    fun binding(queue: Queue, topicExchange: TopicExchange) : Binding
            = BindingBuilder.bind(queue).to(topicExchange).with(queueNameHello)

    @Bean
    fun listenerAdapter(handler: SimpleHandler): MessageListenerAdapter
            = MessageListenerAdapter(handler, "handleMessage")

    @Bean
    fun container(connectionFactory: ConnectionFactory,
                  messageListenerAdapter: MessageListenerAdapter): SimpleMessageListenerContainer {
        val simpleMessageListenerContainer = SimpleMessageListenerContainer()
        simpleMessageListenerContainer.connectionFactory = connectionFactory
        simpleMessageListenerContainer.setQueueNames(queueNameHello)
        simpleMessageListenerContainer.setMessageListener(messageListenerAdapter)
        return simpleMessageListenerContainer
    }

    override fun run(vararg args: String?) {
    }
}