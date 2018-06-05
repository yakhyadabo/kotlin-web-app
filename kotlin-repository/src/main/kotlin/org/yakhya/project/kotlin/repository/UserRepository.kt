package org.yakhya.project.kotlin.repository

import org.yakhya.project.kotlin.domain.User
import java.util.*

interface UserRepository {

  fun findUser(id: Int): Optional<User>

  fun all(): List<User>

  fun printAll()
}