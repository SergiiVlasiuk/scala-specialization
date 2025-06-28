package _12_Contextual_Abstractions

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import _12_Contextual_Abstractions.ConditionalGiven.*
import _12_Contextual_Abstractions.ConditionalGiven.given

class ConditionalGivenTest extends AnyFlatSpec with Matchers {

  "printValue" should "work for Int" in {
    printValueConditional(42) shouldEqual "Int(42)"
  }

  it should "work for List[Int] via conditional given" in {
    printValueConditional(List(1, 2)) shouldEqual "[Int(1), Int(2)]"
  }

  it should "fail to compile for unsupported type" in {
    """printValue(List("hello"))""" shouldNot compile
  }
}
