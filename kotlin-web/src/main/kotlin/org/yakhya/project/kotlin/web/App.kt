package org.yakhya.project.kotlin.web

import org.jooby.run
import org.yakhya.project.kotlin.web.controller.UserController


/**
 * Run application:
 */
fun main(args: Array<String>) {
/*  run(*args){
    use (UserModule())
    use (FlashScope())
    use (UserController::class)
  }*/

  run(::UserController, *args)
}
