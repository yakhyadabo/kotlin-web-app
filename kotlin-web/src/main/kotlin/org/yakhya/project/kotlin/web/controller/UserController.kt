package org.yakhya.project.kotlin.web.controller

import org.jooby.Request
import org.jooby.mvc.GET
import org.jooby.mvc.Path
import org.yakhya.project.kotlin.domain.User
import org.yakhya.project.kotlin.repository.UserRepository
import javax.inject.Inject
import javax.inject.Named

@Path("/api")
class UserController @Inject constructor(private val userRepository: UserRepository, val req: Request) {

  @GET
  @Path("/users")
  fun list(): List<User> {
    return userRepository.all()
  }

  @GET
  @Path("/user")
  fun get(@Named("id") userId: Int): User {
    return userRepository.findUser(userId)
  }




}
