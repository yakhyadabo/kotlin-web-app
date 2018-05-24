package org.yakhya.project.kotlin.web

import com.google.inject.Binder
import com.typesafe.config.Config
import org.jooby.Env
import org.jooby.Jooby
import org.jooby.Kooby
import org.jooby.mvc.GET
import org.jooby.mvc.Path
import org.jooby.run
import org.yakhya.project.kotlin.domain.User
import org.yakhya.project.kotlin.repository.UserRepository
import org.yakhya.project.kotlin.repository.impl.UserRepositoryImpl
import javax.inject.Inject

@Path("/api")
class App @Inject constructor(private val userRepository: UserRepository) {
// class App : Kooby({

  @GET
  @Path("/users")
  fun list(): List<User> {

    //val name = param("name").value("Kotlin")
    // "Hello $name!"
    return userRepository.all()
  }

/*  get("/:id") {
    // Get a user by it's ID
  }*/

}

// https://jooby.org/doc/lang-kotlin/
// https://jooby.org/doc/#modules-creating-modules
/*
class App2 : Kooby({
  use(BillingModule())
  val userRepository = require(UserRepository::class.java)

  get("/hello") {
    userRepository.printAll()
    "Hello"
  }
})
*/


// See https://github.com/jooby-project/jooby/issues/368
class BillingModule : Jooby.Module {
  override fun configure(env: Env?, conf: Config?, binder: Binder) {
    binder.bind(UserRepository::class.java).to(UserRepositoryImpl::class.java)
  }

}

/**
 * Run application:
 */
fun main(args: Array<String>) {
  run(*args){
    // use (Jackson())
    use (BillingModule())
    use (App::class)
    // use (App::class)
  }
}
