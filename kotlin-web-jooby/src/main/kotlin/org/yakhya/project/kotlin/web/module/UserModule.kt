package org.yakhya.project.kotlin.web.module

import com.google.inject.Binder
import com.typesafe.config.Config
import org.jooby.Env
import org.jooby.Jooby
import org.yakhya.project.kotlin.repository.UserRepository
import org.yakhya.project.kotlin.repository.impl.UserRepositoryImpl

class UserModule : Jooby.Module {
  override fun configure(env: Env?, conf: Config?, binder: Binder) {
    binder.bind(UserRepository::class.java).to(UserRepositoryImpl::class.java)
  }

}
