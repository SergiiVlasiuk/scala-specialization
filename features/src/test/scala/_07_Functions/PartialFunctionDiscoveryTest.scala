package _07_Functions

import org.scalatest.funsuite.AnyFunSuite

class PartialFunctionDiscoveryTest extends AnyFunSuite:

  val pf: PartialFunction[Int, String] = {
    case 1 => "one"
    case 2 => "two"
  }

  test("apply known case") {
    assert(pf(1) == "one")
  }

  test("isDefinedAt works") {
    assert(pf.isDefinedAt(1))
    assert(!pf.isDefinedAt(3))
  }

  test("lift makes function safe") {
    val lifted = pf.lift
    assert(lifted(1) == Some("one"))
    assert(lifted(3) == None)
  }

  test("combine with orElse") {
    val pf2: PartialFunction[Int, String] = { case 3 => "three" }
    val combined = pf.orElse(pf2)
    assert(combined(3) == "three")
  }

  test("collect with partial function") {
    val list = List(1, 2, 3, 4)
    val evens: PartialFunction[Int, String] = { case x if x % 2 == 0 => s"$x even" }
    val result = list.collect(evens)
    assert(result == List("2 even", "4 even"))
  }
