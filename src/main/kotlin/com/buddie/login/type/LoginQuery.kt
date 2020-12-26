package com.buddie.login.type

import com.buddie.login.model.User
import com.buddie.login.repository.getUser
import com.buddie.login.repository.getUsers
import com.expediagroup.graphql.annotations.GraphQLDescription
import com.expediagroup.graphql.spring.operations.Query
import org.springframework.stereotype.Component

@Component
class LoginQuery: Query {
    @GraphQLDescription("Gets a list of all users")
    fun users(): List<User> = getUsers();
    @GraphQLDescription("Gets a user by ID")
    fun user(id: Int): User = getUser(id)
}