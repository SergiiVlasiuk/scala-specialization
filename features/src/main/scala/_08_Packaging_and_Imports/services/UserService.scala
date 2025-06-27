package _08_Packaging_and_Imports.services

import _08_Packaging_and_Imports.models.User
import _08_Packaging_and_Imports.utils.MathUtils

object UserService:
  def promote(user: User): String =
    s"${user.name.toUpperCase} is now important (age² = ${MathUtils.square(user.age)})"

@main def run(): Unit =
  import _08_Packaging_and_Imports.services.UserService.promote
  println(promote(User("Serhii", 45)))

