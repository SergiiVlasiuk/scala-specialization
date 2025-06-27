package _01_a_taste_of_scala3.how_to_trait

import org.scalatest.funsuite.{AnyFunSuite}

import java.io.{ByteArrayOutputStream, PrintStream}

class NamedTest extends AnyFunSuite {

  test("sayHello returns correct greeting string") {
    val user = new User("Serhii")
    val greeting = sayHello(user)
    assert(greeting == "Hello, Serhii!")
  }
}
