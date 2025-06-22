package a_taste_of_scala3._08_Singleton_Objects

import org.scalatest.funsuite.AnyFunSuite

class ModulesFromTraitsTest extends AnyFunSuite:

  test("ConsoleLogger logs message") {
    val logger = ConsoleLogger()
    logger.log("Hello world")
  }

  test("TestLogger stores messages") {
    val testLogger = new TestLogger
    testLogger.log("Test message")
    assert(testLogger.logs.contains("Test message"))
  }

  test("Service logs messages using Logger") {
    val testLogger = new TestLogger
    val service = new Service(testLogger)
    service.doWork()
    assert(testLogger.logs.head.contains("Starting work"))
    assert(testLogger.logs.last.contains("Work finished"))
  }

  test("Stackable traits modify logging") {
    val logger = new ConsoleLogger with TimestampLogger with ShortLogger
    logger.log("This is a very long log message that should be shortened")
  }
