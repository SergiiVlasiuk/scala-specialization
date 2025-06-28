package _12_Contextual_Abstractions


import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import _12_Contextual_Abstractions.TypeClassDerivation.*
import _12_Contextual_Abstractions.TypeClassDerivation.ShowTypeClassDerivation.given

class TypeClassDerivationTest extends AnyFlatSpec with Matchers {

  "printValue" should "show an Int" in {
    printValue(42) shouldEqual "42"
  }

  it should "show a derived Person" in {
    val p = Person("Alice", 30)
    printValue(p) should include ("Person(")
  }
}
