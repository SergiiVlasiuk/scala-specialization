package _01_a_taste_of_scala3._08_Singleton_Objects

import org.scalatest.funsuite.AnyFunSuite

class SingletonObjectsTest extends AnyFunSuite:

  test("Logger logs message") {
    // Тут перевіряємо, що метод не кидає помилок
    Logger.log("Test message")
  }

  test("User companion apply creates instance") {
    val user = User("Ivan")
    assert(user.isInstanceOf[User])
    assert(user.greet() == "Hello, Ivan!")
  }

  test("Config singleton state management") {
    assert(Config.getEnv == "dev")
    Config.setEnv("prod")
    assert(Config.getEnv == "prod")
  }
