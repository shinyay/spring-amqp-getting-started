package io.pivotal.shinyay.amqp.gettingstarted

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GettingStartedApplication

fun main(args: Array<String>) {
	runApplication<GettingStartedApplication>(*args)
}
