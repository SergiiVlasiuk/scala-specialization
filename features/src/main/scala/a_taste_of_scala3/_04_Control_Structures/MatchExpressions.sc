val i = 1

// later in the code ...
i match
  case 1 => println("one")
  case 2 => println("two")
  case _ => println("other")

val result = i match
  case 1 => "one"
  case 2 => "two"
  case _ => "other"

//case class Person(name: String)
class Person(val name: String)

val p = Person("Fred")
object Person {
  def apply(name: String): Person = new Person(name)
  def unapply(p: Person): Option[String] = Some(p.name)
}

// later in the code
p match
  case Person(name) if name == "Fred" =>
    println(s"$name says, Yubba dubba doo")
  case Person(name) if name == "Bam Bam" =>
    println(s"$name says, Bam bam!")
  case _ => println("Watch the Flintstones!")


def getClassAsString(x: Matchable): String = x match
  case s: String => s"'$s' is a String"
  case i: Int => "Int"
  case d: Double => "Double"
  case l: List[?] => "List"
  case _ => "Unknown"

// examples
getClassAsString(1)               // Int
getClassAsString("hello")         // 'hello' is a String
getClassAsString(List(1, 2, 3))   // List
