package org.yakhya.project.kotlin.web

import org.jooby.*

class App: Kooby({
  get {
    "Hello Kotlin"
  }
})

fun main(args: Array<String>) {
  run(::App, *args)
}