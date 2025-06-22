package a_taste_of_scala3._10_Contextual_Abstractions

import org.scalatest.funsuite.AnyFunSuite

class ContextualAbstractionsTest extends AnyFunSuite:

  test("Show[Int] given instance") {
    val s = summon[Show[Int]]
    assert(s.show(42) == "Int: 42")
  }

  test("Show[String] given instance") {
    val s = summon[Show[String]]
    assert(s.show("hello") == "String: hello")
  }

  test("printValue uses given Show instance") {
    import a_taste_of_scala3._10_Contextual_Abstractions.given

    val output = new java.io.ByteArrayOutputStream()
    Console.withOut(output) {
      printValue(123)
      printValue("abc")
    }
    val result = output.toString
    assert(result.contains("Int: 123"))
    assert(result.contains("String: abc"))
  }
