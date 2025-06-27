package _01_a_taste_of_scala3._06_Methods

object AdvancedFeatures:
  inline def log(msg: String): Unit = println(s"[LOG] $msg")

  given intOrdering: Ordering[Int] with
    def compare(x: Int, y: Int): Int = x.compareTo(y)

  lazy val cachedValue: String =
    println("Computing cachedValue")
    "Result"

  def callByName(x: => Int): Int =
    x + x

  given stringCleanup: Function1[String, Unit] = res => println(s"[default cleanup] $res")

  def withResource[T](resource: => T)(using cleanup: T => Unit): Unit =
    val res = resource
    try println(s"Using: $res")
    finally cleanup(res)
