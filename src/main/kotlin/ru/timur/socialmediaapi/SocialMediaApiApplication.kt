package ru.timur.socialmediaapi

import org.modelmapper.ModelMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@SpringBootApplication
class SocialMediaApiApplication {
	@Bean
	fun modelMapper(): ModelMapper {
		return ModelMapper()
	}
}

fun main(args: Array<String>) {
	runApplication<SocialMediaApiApplication>(*args)
}
