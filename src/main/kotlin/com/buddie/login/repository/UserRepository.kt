package com.buddie.login.repository

import com.buddie.login.database.BuddieUser
import com.buddie.login.model.User
import com.buddie.login.transactionEnvironment
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun BuddieUser.toDataObj(row: ResultRow) =
        User(row[id].toInt(),
        row[first_name].toString(),
        row[last_name].toString(),
        row[country_code].toString(),
        row[country_code].toString(),
        row[phone_number].toString())

fun getUsers(): List<User> {
    val users = transaction {
        BuddieUser.selectAll().toList();
    }.map { x -> BuddieUser.toDataObj(x) }

    return users;
}

fun getUser(id: Int): User {
    val user = transactionEnvironment {
        BuddieUser.select { BuddieUser.id eq id }
            .map { x -> BuddieUser.toDataObj(x) }
            .first()
    }
    return user
}
