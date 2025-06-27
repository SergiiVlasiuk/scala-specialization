package _01_a_taste_of_scala3._06_Methods

object ExtensionVsEta:
  extension (x: Int)
    def times(f: => Unit): Unit =
      for _ <- 1 to x do f

  def greet(): Unit = println("Hi")
  val greetFn: () => Unit = () => println("Hello")
