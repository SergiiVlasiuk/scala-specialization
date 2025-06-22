package A_First_Look_at_Types

import org.scalatest.funsuite.AnyFunSuite

class AbstractTypesTest extends AnyFunSuite:

  test("Any can hold any type") {
    val a: Any = 42
    val b: Any = "string"
    val c: Any = List(1, 2, 3)
    assert(a == 42 && b == "string" && c == List(1, 2, 3))
  }

  test("AnyRef can hold reference types") {
    val r: AnyRef = "hello"
    assert(r.isInstanceOf[String])
  }

  test("Nothing is used for exceptions") {
    def fail(): Nothing = throw new Exception("Failure")
    assertThrows[Exception](fail())
  }

  test("Null is a subtype of all AnyRef") {
    val s: String | Null = null
    assert(s == null)
  }

  test("Matchable allows pattern matching") {
    def describe(x: Matchable): String = x match
      case i: Int    => s"Int: $i"
      case s: String => s"String: $s"
      case _         => "Unknown"

    assert(describe(5) == "Int: 5")
    assert(describe("ok") == "String: ok")
  }
