package org.yakhya.project.kotlin.web

import org.jooby.run
import org.yakhya.project.kotlin.web.controller.UserController
import org.yakhya.project.kotlin.web.module.UserModule


/**
 * Run application:
 */
fun main(args: Array<String>) {
  run(*args){
    use (UserModule())
    use (UserController::class)
  }
}
