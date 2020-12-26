package com.buddie.login

import com.buddie.login.hooks.CustomFederationSchemaGeneratorHooks
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import java.util.*


@SpringBootApplication
class LoginApplication{

	@Bean
	fun hooks() = CustomFederationSchemaGeneratorHooks(emptyList())
}

fun main(args: Array<String>) {

	DbSettings.db //Access the db connector once to avoid leaks
	transaction {
		addLogger(StdOutSqlLogger)
	}

	runApplication<LoginApplication>(*args)
}



