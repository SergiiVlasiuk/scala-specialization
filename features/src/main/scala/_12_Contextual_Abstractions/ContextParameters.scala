package _12_Contextual_Abstractions

object ContextParameters:
  def greet(name: String)(using prefix: String): String =
    s"$prefix $name"

  given String = "Hi"

  trait Show[T]:
    def show(value: T): String

  given Show[Int] with
    def show(value: Int): String = s"Int($value)"

  def printValue[T](value: T)(using s: Show[T]): String =
    s.show(value)

  case class Config(appName: String)

  given Config = Config("TestApp")

  def log(msg: String)(using cfg: Config): String =
    s"[${cfg.appName}] $msg"

