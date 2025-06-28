package _12_Contextual_Abstractions

import _12_Contextual_Abstractions.ContextBound.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ContextBoundTest extends AnyFlatSpec with Matchers {

  "printValue" should "print Int with Show type class" in {
    printValue(10) shouldEqual "Int(10)"
  }

  "compare" should "compare values using Ordering" in {
    compare(5, 3) > 0 shouldBe true
    compare(3, 5) < 0 shouldBe true
    compare(5, 5) shouldEqual 0
  }
}
