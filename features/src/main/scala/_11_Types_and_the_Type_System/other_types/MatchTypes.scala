package _11_Types_and_the_Type_System.other_types

object MatchTypes:

  // Define a match type
  type Element[X] = X match
    case String => Char
    case Array[t] => t
    case Iterable[t] => t
    case _ => X

  // Sample method using the match type
  def getElementType[X](x: X): String =
    val name = x match
      case _: String => "Char"
      case _: Array[t] => "Array element"
      case _: Iterable[t] => "Iterable element"
      case _ => "Unknown"
    name
