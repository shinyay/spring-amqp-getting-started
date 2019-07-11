package io.pivotal.shinyay.amqp.gettingstarted

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class GettingStartedApplication {

	companion object {
		val topicExchangeName = "spring-amqp-exchange"
	}

	val queueName = "spring-amqp-queue"

	@Bean
	fun queue(): Queue = Queue(queueName, false)

	@Bean
	fun exchange(): TopicExchange = TopicExchange(topicExchangeName)

	@Bean
	fun binding(queue: Queue, exchange: TopicExchange): Binding
			= BindingBuilder
					.bind(queue)
					.to(exchange)
					.with("foo.bar.#")

	@Bean
	fun messageListenerAdapter(receiver: Receiver): MessageListenerAdapter
			= MessageListenerAdapter(receiver, "receiveMessage")

	@Bean
	fun container(connectionFactory: ConnectionFactory,
				  messageListenerAdapter: MessageListenerAdapter): SimpleMessageListenerContainer {
		val simpleMessageListenerContainer = SimpleMessageListenerContainer()
		simpleMessageListenerContainer.connectionFactory = connectionFactory
		simpleMessageListenerContainer.setQueueNames(queueName)
		simpleMessageListenerContainer.setMessageListener(messageListenerAdapter)
		return simpleMessageListenerContainer
	}

}

fun main(args: Array<String>) {
	runApplication<GettingStartedApplication>(*args)
}
