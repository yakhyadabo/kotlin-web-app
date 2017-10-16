package org.yakhya.project.kotlin.domain

data class User(val login: String, val password: String, val roles: Set<Role> = emptySet())