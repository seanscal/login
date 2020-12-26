package com.buddie.login

import org.jetbrains.exposed.sql.transactions.transaction

fun <T> transactionEnvironment(closure: () -> T): T {
    return transaction() { closure() }
}