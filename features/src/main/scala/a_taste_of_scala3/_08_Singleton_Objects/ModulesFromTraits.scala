package a_taste_of_scala3._08_Singleton_Objects

trait ModuleLogger:
  def log(msg: String): Unit

class ConsoleLogger extends ModuleLogger:
  def log(msg: String): Unit = println(s"[Console] $msg")

class TestLogger extends ModuleLogger:
  var logs: List[String] = Nil
  def log(msg: String): Unit = logs = logs :+ msg

class Service(logger: ModuleLogger):
  def doWork(): Unit =
    logger.log("Starting work")
    // some logic
    logger.log("Work finished")

trait TimestampLogger extends ModuleLogger:
  abstract override def log(msg: String): Unit =
    super.log(s"${java.time.Instant.now()} - $msg")

trait ShortLogger extends ModuleLogger:
  val maxLength = 15
  abstract override def log(msg: String): Unit =
    super.log(
      if msg.length <= maxLength then msg
      else msg.take(maxLength - 3) + "..."
    )
