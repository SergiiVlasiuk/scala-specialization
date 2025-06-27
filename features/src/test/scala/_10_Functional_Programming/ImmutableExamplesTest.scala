package _10_Functional_Programming

// ----------------------------------------
// ScalaTest for Immutable Values Examples
// ----------------------------------------

import org.scalatest.funsuite.AnyFunSuite
import _10_Functional_Programming.ImmutableExamples._

class ImmutableExamplesTest extends AnyFunSuite:

  test("immutable constant should not be modified"):
    assert(pi == 3.14159)

  test("immutability in list append"):
    assert(numbers == List(1, 2, 3))
    assert(moreNumbers == List(1, 2, 3, 4))

  test("immutability of case class"):
    assert(john.age == 30)
    assert(olderJohn.age == 31)
    assert(john.name == olderJohn.name)

  test("copying nested case classes"):
    assert(emp.address.city == "Kyiv")
    assert(relocated.address.city == "Lviv")
    assert(emp.name == relocated.name)

  test("function should not mutate original list"):
    val incremented = incrementAll(numbers)
    assert(incremented == List(2, 3, 4))
    assert(numbers == List(1, 2, 3))
