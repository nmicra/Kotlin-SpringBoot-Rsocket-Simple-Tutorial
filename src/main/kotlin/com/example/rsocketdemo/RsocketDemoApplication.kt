package com.example.rsocketdemo

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageExceptionHandler
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import org.springframework.util.Assert
import java.util.*

@SpringBootApplication
class RsocketDemoApplication

fun main(args: Array<String>) {
	runApplication<RsocketDemoApplication>(*args)
}

data class GreetingRequest(val name : String)
data class GreetingResponse(val message : String)

@Controller
class GreetingRsocketController {

	@MessageExceptionHandler
	fun handleException(ex : Exception) : String = "Error occurred [${UUID.randomUUID()}]: ${ex.localizedMessage}"

	@MessageMapping("greetings")
	fun greet(request: GreetingRequest) : GreetingResponse{
		return GreetingResponse("Hello ${request.name}!!!")
	}

	@MessageMapping("gree.{name}")
	fun name(@DestinationVariable name: String) : Flow<GreetingResponse> {
		check(name[0].isUpperCase()) {"first letter is not capital in name: $name"}
		return flow {
			for (i in 1..500){
				emit(GreetingResponse(">>> Hello $name -> $i !!!"))
				delay(100)
			}
		}
	}

	@MessageMapping("echo")
	fun echo() : String = "echo"

}