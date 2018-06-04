package org.yakhya.project.kotlin.web

import org.jooby.FlashScope
import org.jooby.run
import org.yakhya.project.kotlin.web.controller.PetController
import org.yakhya.project.kotlin.web.controller.UserController
import org.yakhya.project.kotlin.web.module.UserModule


/**
 * Run application:
 */
fun main(args: Array<String>) {
/*  run(*args){
    use (UserModule())
    use (FlashScope())
    use (UserController::class)
  }*/

  run(::PetController, *args)
}
