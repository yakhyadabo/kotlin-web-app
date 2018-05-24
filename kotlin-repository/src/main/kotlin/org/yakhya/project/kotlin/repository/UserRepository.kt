package org.yakhya.project.kotlin.repository

import org.yakhya.project.kotlin.domain.User

interface UserRepository {

  fun findUser(id: Int): User

  fun all(): List<User>

  fun printAll()
}