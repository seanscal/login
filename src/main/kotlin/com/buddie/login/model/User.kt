package com.buddie.login.model

import com.expediagroup.graphql.annotations.GraphQLDescription
import com.expediagroup.graphql.federation.directives.FieldSet
import com.expediagroup.graphql.federation.directives.KeyDirective
import java.util.*

@KeyDirective(fields = FieldSet("id"))
@GraphQLDescription("A User for Buddie")
data class User(
        val id: Int,
        @GraphQLDescription("The user's first name")
        val firstName: String,
        val lastName: String,
        val email: String,
        val countryCode: String,
        val phoneNumber: String,
        val UUID: UUID = java.util.UUID.randomUUID()
) {
    @GraphQLDescription("returns full name of user")
    fun fullName() = "${firstName} ${lastName}"
}