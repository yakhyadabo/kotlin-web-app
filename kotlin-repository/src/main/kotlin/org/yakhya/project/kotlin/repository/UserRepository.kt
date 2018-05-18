package org.yakhya.project.kotlin.repository

import org.jooq.impl.DSL
import org.yakhya.project.kotlin.domain.User


class UserRepository {

  private val dsl = DSL.using("jdbc:postgresql://localhost:5432/iris-db", "iris-user", "iris-password")!!
  private val a = Tables.IRIS_USER!!
  private val b = Tables.USER_PROFILE!!

  fun findUser(id: Int):User{
    return dsl.select(a.ID, a.LOGIN, a.PASSWORD)
        .from(a)
        .where(a.ID.equal(id))
        .fetchOneInto(User::class.java)

  }

  fun all(): List<User> {

    return dsl.select(a.ID, a.LOGIN, a.PASSWORD)
        .from(a)
        .join(b).on(a.ID.eq(b.IRIS_USER_ID))
        .orderBy(1, 2, 3)
        .fetchInto(User::class.java)
  }

  fun printAll(){
    dsl.select(a.ID, a.LOGIN, a.PASSWORD, b.USER_PROFILE_TYPE_ID)
        .from(a)
        .join(b).on(a.ID.eq(b.IRIS_USER_ID))
        .orderBy(1, 2, 3)
        .forEach { println("${it[b.USER_PROFILE_TYPE_ID]} by ${it[a.ID]} ${it[a.LOGIN]} ${it[a.PASSWORD]}") }
  }
}