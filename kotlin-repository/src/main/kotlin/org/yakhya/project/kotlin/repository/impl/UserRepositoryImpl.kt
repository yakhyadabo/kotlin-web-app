package org.yakhya.project.kotlin.repository.impl

import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.yakhya.project.kotlin.domain.User
import org.yakhya.project.kotlin.repository.Tables
import org.yakhya.project.kotlin.repository.UserRepository
import java.sql.DriverManager


class UserRepositoryImpl: UserRepository {

  private val dsl = DSL.using("jdbc:postgresql://localhost:5432/iris-db", "iris-user", "iris-password")!!
  private val a = Tables.IRIS_USER!!
  private val b = Tables.USER_PROFILE!!

  override fun findUser(id: Int):User{
    return dsl.select(a.ID, a.LOGIN, a.PASSWORD)
        .from(a)
        .where(a.ID.equal(id))
        .fetchOneInto(User::class.java)

  }

  override fun all(): List<User> {

    return dsl.select(a.ID, a.LOGIN, a.PASSWORD)
        .from(a)
        .orderBy(1, 2, 3)
        .fetchInto(User::class.java)
  }

  override fun printAll(){
    dsl.select(a.ID, a.LOGIN, a.PASSWORD, b.USER_PROFILE_TYPE_ID)
        .from(a)
        .join(b).on(a.ID.eq(b.IRIS_USER_ID))
        .orderBy(1, 2, 3)
        .forEach { println("${it[b.USER_PROFILE_TYPE_ID]} by ${it[a.ID]} ${it[a.LOGIN]} ${it[a.PASSWORD]}") }
  }
}