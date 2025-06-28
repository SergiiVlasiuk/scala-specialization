package _12_Contextual_Abstractions

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import _12_Contextual_Abstractions.ContextFunctions.*

class ContextFunctionsTest extends AnyFlatSpec with Matchers {

  given Config = Config("ContextApp")

  "logger" should "log with context config" in {
    logger("Start") shouldEqual "[ContextApp] Start"
  }

  "process" should "invoke logger and pass to function" in {
    var output = ""
    process("Run") { msg => output = msg }
    output shouldEqual "[ContextApp] Run"
  }

  "getLogger" should "return context function" in {
    val logFn = getLogger
    noException should be thrownBy logFn("Hello")
  }
}
