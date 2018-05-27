package org.yakhya.project.kotlin.web.controller

import org.jooby.Request
import org.jooby.mvc.GET
import org.jooby.mvc.Path
import org.yakhya.project.kotlin.domain.User
import org.yakhya.project.kotlin.repository.UserRepository
import javax.inject.Inject

@Path("/api")
class UserController @Inject constructor(private val userRepository: UserRepository, val req: Request) {

  @GET
  @Path("/users")
  fun list(): List<User> {

    //val name = param("name").value("Kotlin")
    // "Hello $name!"
    return userRepository.all()
  }

/*
  @GET
  @Path("/user")
  fun get(val name: JvmName): User {
    // val name = req. param("name").value
    // Get a user by it's ID
  }
*/

}
