package _12_Contextual_Abstractions

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TypeClassShowTest extends AnyFlatSpec with Matchers {

  import _12_Contextual_Abstractions.TypeClassShow.given

  "printValue" should "show Int correctly" in {
    printValue(42) shouldEqual "Int(42)"
  }

  it should "show String correctly" in {
    printValue("hello") shouldEqual "hello"
  }
}
