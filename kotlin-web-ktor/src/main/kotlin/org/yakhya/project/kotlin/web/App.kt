package org.yakhya.project.kotlin.web

import com.auth0.jwk.JwkProviderBuilder
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.UserIdPrincipal
import io.ktor.auth.authenticate
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.auth.jwt.jwt
import io.ktor.features.CallLogging
import io.ktor.features.Compression
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.text.DateFormat
import java.time.LocalDate
import java.util.concurrent.TimeUnit

data class Model(val name: String, val items: List<Item>, val date: LocalDate = LocalDate.of(2018, 4, 13))
data class Item(val key: String, val value: String)

val model = Model("root", listOf(Item("A", "Apache"), Item("B", "Bing")))

fun main(args: Array<String>) {
  embeddedServer(Netty, 8082, watchPaths = listOf("BlogAppKt"), module = Application::module).start()
}

fun Application.module() {

  install(DefaultHeaders)
  install(Compression)
  install(CallLogging)
  install(ContentNegotiation) {
    gson {
      setDateFormat(DateFormat.LONG)
      setPrettyPrinting()
    }
  }

  routing {

    val jwt = SimpleJWT("my-super-secret-for-jwt")
    install(Authentication) {
      jwt {
        verifier(jwt.verifier)
        validate {
          UserIdPrincipal(it.payload.getClaim("name").asString())
        }
      }
    }

    authenticate {

      get("/v1") {
        call.respond(model)
      }
    }

    get("/v1/item/{key}") {
      val item = model.items.firstOrNull { it.key == call.parameters["key"] }
      if (item == null)
        call.respond(HttpStatusCode.NotFound)
      else
        call.respond(item)
    }


    post("/v1/user") {
      /*        val post = call.receive<PostSnippet>()
    snippets += Snippet(post.snippet.text)*/
      call.respond(mapOf("OK" to true))
    }

  }

}

open class SimpleJWT(secret: String) {
  private val algorithm = Algorithm.HMAC256(secret)
  val verifier = JWT.require(algorithm).build()
  fun sign(name: String): String = JWT.create().withClaim("name", name).sign(algorithm)
}