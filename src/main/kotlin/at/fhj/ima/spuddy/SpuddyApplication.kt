package at.fhj.ima.spuddy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpuddyApplication

fun main(args: Array<String>) {
	runApplication<SpuddyApplication>(*args)
}
