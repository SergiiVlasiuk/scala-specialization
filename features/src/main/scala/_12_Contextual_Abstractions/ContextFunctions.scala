package _12_Contextual_Abstractions

object ContextFunctions:
  case class Config(appName: String)

  def logger(msg: String): Config ?=> String =
    s"[${summon[Config].appName}] $msg"

  def process(msg: String)(log: String => Unit)(using Config): Unit =
    log(logger(msg))

  def getLogger: Config ?=> String => Unit =
    msg => println(logger(msg))

