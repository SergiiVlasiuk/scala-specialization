package _11_Types_and_the_Type_System.other_types

import _11_Types_and_the_Type_System.other_types.RefinementTypes.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class RefinementTypesTest extends AnyFlatSpec with Matchers {

  "callSpeak" should "use refinement with name field" in {
    callSpeak(dog) shouldEqual "Buddy says: Woof!"
  }

  "useService" should "refine ExternalService with timeout" in {
    useService(service) shouldEqual "Connecting with timeout 42"
  }

  "printName" should "support structural refinement" in {
    printName(Person("Olga", 30)) shouldEqual "Name: Olga"
  }
}
