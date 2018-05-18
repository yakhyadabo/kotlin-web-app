package org.yakhya.project.kotlin.web

import org.jooby.*


class App : Kooby({
  get {
    val name = param("name").value("Kotlin")
    "Hello $name!"
  }
})

/**
 * Run application:
 */
fun main(args: Array<String>) {
  run(::App, *args)
}