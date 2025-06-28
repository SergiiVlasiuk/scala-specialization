package _12_Contextual_Abstractions

import _12_Contextual_Abstractions.ContextParameters.*
import _12_Contextual_Abstractions.ContextParameters.given
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ContextParametersTest extends AnyFlatSpec with Matchers {

  "greet" should "prepend prefix" in {
    greet("World") shouldEqual "Hi World"
  }

  "printValue" should "use Show type class" in {
    printValue(10) shouldEqual "Int(10)"
  }

  "log" should "use config from context" in {
    log("running") shouldEqual "[TestApp] running"
  }
}
