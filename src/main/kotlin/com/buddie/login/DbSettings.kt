package com.buddie.login

import org.jetbrains.exposed.sql.Database

object DbSettings {

    val dbUrl = "jdbc:postgresql://localhost:5432/buddie"
    val dbUser = "sean"
    val dbPass = "Seanpassword!123"

    val db by lazy {
        Database.connect(dbUrl, driver = "org.postgresql.Driver", user = dbUser, password = dbPass)
    }
}