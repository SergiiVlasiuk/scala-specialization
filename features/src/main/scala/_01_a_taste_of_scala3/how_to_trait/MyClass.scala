package _01_a_taste_of_scala3.how_to_trait

trait A { def hello():Unit = println("A") }
trait B { def hello():Unit = println("B") }
class MyClass extends A with B:
  override def hello(): Unit =
    println("MyClass:")

    // super.hello() тут викликає метод з останнього у ланцюжку trait-ів — у твоєму випадку це B.
    //  Порядок extends A with B означає, що B “останній” і “перемагає”.
    super.hello() // викликає B.hello()
    super[A].hello() // викликає A.hello()

