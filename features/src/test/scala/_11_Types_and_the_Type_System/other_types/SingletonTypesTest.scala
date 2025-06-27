package _11_Types_and_the_Type_System.other_types

import _11_Types_and_the_Type_System.other_types.SingletonTypes.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SingletonTypesTest extends AnyFlatSpec with Matchers {


  "onlyRed" should "accept only the singleton red" in {
    onlyRed(red) shouldEqual "You passed red"
//    onlyRed("red") // would fail to compile
  }

  "expect42" should "accept only value 42 tied to num" in {
    expect42(num) shouldEqual "Confirmed: 42 is 42!"
  }

  "describeColor" should "work only with Colors.Green" in {
    describeColor(Colors.Green) shouldEqual "Color is green"
  }
}
