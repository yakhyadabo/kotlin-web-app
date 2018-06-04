package org.yakhya.project.kotlin.web.controller

import org.jooby.Err
import org.jooby.Kooby
import org.jooby.Status
import org.jooby.require
import org.yakhya.project.kotlin.repository.UserRepository
import org.yakhya.project.kotlin.web.module.UserModule

// See : https://github.com/jooby-project/apitool-kotlin-starter/blob/master/src/main/kotlin/starter/apitool/App.kt
// https://dev.to/edgarespina/jooby-export-your-javakotlin-api-to-swaggerraml-45g9

class PetController : Kooby({

  use (UserModule())

  /**
   * Everything about your Pets.
   */
  path("/api/pet") {
    /**
     * List pets ordered by id.
     *
     * @param start Start offset, useful for paging. Default is `0`.
     * @param max Max page size, useful for paging. Default is `20`.
     * @return Pets ordered by name.
     */
    get {
      val db = require(UserRepository::class)

      val start = param("start").intValue(0)
      val max = param("max").intValue(20)

      db.all()
    }

    /**
     * Find pet by ID
     *
     * @param id Pet ID.
     * @return Returns `200` with a single pet or `404`
     */
    get("/:id") {
      val db = require(UserRepository::class)

      val id = param("id")

      val pet = db.findUser(id.intValue()) ?: throw Err(Status.NOT_FOUND)

      pet
    }
  }
})