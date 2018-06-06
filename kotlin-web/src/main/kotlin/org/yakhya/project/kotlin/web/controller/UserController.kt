package org.yakhya.project.kotlin.web.controller

import org.jooby.*
import org.jooby.apitool.ApiTool
import org.slf4j.LoggerFactory
import org.yakhya.project.kotlin.repository.UserRepository
import org.yakhya.project.kotlin.web.module.UserModule

// See: https://github.com/jooby-project/apitool-kotlin-starter/blob/master/src/main/kotlin/starter/apitool/App.kt
// See: https://dev.to/edgarespina/jooby-export-your-javakotlin-api-to-swaggerraml-45g9

internal val COMPLETED = "Completed"

class UserController : Kooby({

  use (UserModule())

  /** Export API to Swagger and RAML: */
  use(ApiTool()
      .filter { r -> r.pattern().startsWith("/api") }
      .swagger()
      .raml())

  // Home page redirect to Swagger:
  get { Results.redirect("/swagger") }

  /**
   * Everything about your Users.
   */
  path("/api/v1/user") {
    /**
     * List users ordered by id.
     *
     * @return Users.
     */
    get {
      val userRepository = require(UserRepository::class)

      userRepository.all()
    }

    /**
     * Find user by ID
     *
     * @param id User ID.
     * @return Returns `200` with a single user or `404`
     */
    get("/:id") {
      val userRepository = require(UserRepository::class)

      val id = param("id")

      val user = userRepository.findUser(id.intValue()) ?: throw Err(Status.NOT_FOUND)

      user
    }

    /**
     * Upload users from CSV file
     *
     * @return Returns `200` with a single user or `404`
     */
    post("/upload") { req, res ->
      val upload = req.file("sampleFile")

      val file = upload.file()

      log.info("Script Uploaded file size: " + file.length())

      upload.close()

      res.send(COMPLETED)

    }
  }
})

{
  companion object {
    private val log = LoggerFactory.getLogger(UserController::class.java)
  }
}