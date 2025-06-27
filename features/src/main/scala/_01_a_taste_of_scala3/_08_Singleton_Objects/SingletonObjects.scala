package _01_a_taste_of_scala3._08_Singleton_Objects

// “Utility” methods
object StringUtils:
  def isNullOrEmpty(s: String): Boolean = s == null || s.trim.isEmpty
  def leftTrim(s: String): String = s.replaceAll("^\\s+", "")
  def rightTrim(s: String): String = s.replaceAll("\\s+$", "")

// Логер як singleton
object Logger:
  def log(msg: String): Unit =
    println(s"[LOG] $msg")

// Клас користувача
class User(val name: String):
  def greet(): String = s"Hello, $name!"

// Companion object для User
object User:
  def apply(name: String): User = new User(name)

// Конфігурація як singleton зі станом
object Config:
  private var env: String = "dev"

  def getEnv: String = env

  def setEnv(newEnv: String): Unit =
    env = newEnv
