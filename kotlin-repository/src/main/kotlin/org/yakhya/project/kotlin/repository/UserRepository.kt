package org.yakhya.project.kotlin.repository

import org.yakhya.project.kotlin.domain.User

class UserRepository {
  fun findUser(id: String) = User(id, password = "############")
}

fun main(args : Array<String>){
  TODO("Later")
}