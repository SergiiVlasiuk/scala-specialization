package _01_a_taste_of_scala3.how_to_trait

trait Named { def name: String }

class User(val name: String) extends Named

def sayHello(named: Named): String =
  val result = s"Hello, ${named.name}!"
  println(result)
  result

