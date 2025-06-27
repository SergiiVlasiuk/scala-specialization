package _10_Functional_Programming

import scala.util.*
import org.scalatest.funsuite.AnyFunSuite

class FunctionalErrorHandlingTest extends AnyFunSuite:

  test("risky returns Success for valid integer string") {
    assert(risky("123") == Success(123))
  }

  test("risky returns Failure for non-integer string") {
    assert(risky("abc").isFailure)
  }

  test("processInput returns parsed value string on success") {
    assert(processInput("42") == "Parsed: 42")
  }

  test("processInput returns error string on failure") {
    val result = processInput("not a number")
    assert(result.startsWith("Error:"))
  }

  test("fallbackValue returns original value on success") {
    assert(fallbackValue("99") == 99)
  }

  test("fallbackValue returns fallback value on failure") {
    assert(fallbackValue("bad") == 0)
  }

  test("safeDivide returns Right for non-zero divisor"):
    assert(safeDivide(10, 2) == Right(5))

  test("safeDivide returns Left for zero divisor"):
    assert(safeDivide(10, 0) == Left("Division by zero"))

  test("safeDivide result is mapped correctly"):
    val result = safeDivide(10, 2).map(_ * 2)
    assert(result == Right(10))

  test("safeDivide.fold handles both cases"):
    val folded = safeDivide(10, 0).fold(
      err => s"Failed: $err",
      value => s"Got: $value"
    )
    assert(folded == "Failed: Division by zero")

