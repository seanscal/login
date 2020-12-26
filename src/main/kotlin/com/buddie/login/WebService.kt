package com.buddie.login

import com.buddie.login.hooks.CustomFederationSchemaGeneratorHooks
import com.buddie.login.type.LoginQuery
import com.expediagroup.graphql.TopLevelObject
import com.expediagroup.graphql.federation.FederatedSchemaGeneratorConfig
import com.expediagroup.graphql.federation.toFederatedSchema
import com.expediagroup.graphql.extensions.print as gqlprint
import com.expediagroup.graphql.spring.extensions.toGraphQLResponse
import com.expediagroup.graphql.types.GraphQLRequest
import com.expediagroup.graphql.types.GraphQLResponse
import graphql.GraphQL
import graphql.schema.GraphQLSchema
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

val logger = LoggerFactory.getLogger("de.hello")!!

private fun setupSchema(): GraphQLSchema {
    val hooks = CustomFederationSchemaGeneratorHooks(listOf())
    val config = FederatedSchemaGeneratorConfig(supportedPackages = listOf("com.buddie.login", "com.buddie.login.type", "com.buddie.login.query"), hooks = hooks)
    return toFederatedSchema(config, queries = listOf(TopLevelObject(LoginQuery())))
}


@RestController
@RequestMapping()
class WebService() {

    @GetMapping("/")
    fun home(): ResponseEntity<String> {
        print("hey")
        return ResponseEntity("hi", OK)
    }

    @PostMapping("/graphql")
    fun graphql(@RequestBody request: GraphQLRequest): GraphQLResponse<*>{
        logger.info("Query is: $request.query\n")
        val schema = setupSchema()
        logger.info("Schema is\n" + schema.gqlprint() + "\n")
        /* If _service is null because SDL creation failed, maybe sending the broken sdl from schema.print to Gateway API might provide
        information about validation problems. E.g.
         call.respond(GraphQLResponse.kt(ServiceSDL(_Service(schema.print(includeDefaultSchemaDefinition = false, includeDirectives = false)))))
        */
        val builder = GraphQL.newGraphQL(schema).build()
        val result = builder.execute(request.query).toGraphQLResponse()
        return result
    }

}