package com.buddie.login.database

import org.jetbrains.exposed.sql.Table

object BuddieUser : Table("buddie_user") {
    val id = integer("id").autoIncrement()
    val first_name = varchar("first_name", 50)
    val last_name = varchar("last_name", 50)
    val email = varchar("email", 240)
    val country_code = varchar("country_code", 50)
    val phone_number = varchar("phone_number", 50)
}