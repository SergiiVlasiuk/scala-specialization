package _11_Types_and_the_Type_System

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DependentFunctionTypesTest extends AnyFlatSpec with Matchers:
  "evaluate" should "evaluate IntExpr to Int" in {
    val expr = IntExpr(42)
    val result: expr.Out = evaluate(expr)
    result shouldBe 42
  }

  it should "evaluate BoolExpr to Boolean" in {
    val expr = BoolExpr(true)
    val result: expr.Out = evaluate(expr)
    result shouldBe true
  }
