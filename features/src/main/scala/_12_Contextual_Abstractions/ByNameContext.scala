package _12_Contextual_Abstractions

object ByNameContext:

  trait Logger:
    def log(msg: String): Unit

  def debug(message: => String)(using logger: => Logger): Unit =
    if sys.env.getOrElse("DEBUG", "false") == "true" then
      logger.log(message)

  given Logger with
    def log(msg: String): Unit = println(s"[debug] $msg")

  def computeSomething(): Int =
    println("Computing...")
    42
