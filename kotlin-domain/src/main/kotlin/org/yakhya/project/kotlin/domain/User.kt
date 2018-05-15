package org.yakhya.project.kotlin.domain

data class User(val id: Int, val login: String, val password: String)
//data class User(val id: String, val login: String, val password: String, val roles: Set<Role> = emptySet())