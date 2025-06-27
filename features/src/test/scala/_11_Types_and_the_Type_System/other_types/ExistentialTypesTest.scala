package _11_Types_and_the_Type_System.other_types

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import _11_Types_and_the_Type_System.other_types.ExistentialTypes.*

class ExistentialTypesTest extends AnyFlatSpec with Matchers {

  "printUnknown" should "print value from StringContainer" in {
    val sc = new StringContainer
    printUnknown(sc) should include ("Scala")
  }

  it should "print value from IntContainer" in {
    val ic = new IntContainer
    printUnknown(ic) should include ("42")
  }
}
