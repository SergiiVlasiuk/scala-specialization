package _11_Types_and_the_Type_System.other_types

object SingletonTypes:
  val red = "red"
  val num = 42
  object Colors:
    val Green = "green"

  def onlyRed(x: red.type): String = s"You passed $x"
  def expect42(x: num.type): String = s"Confirmed: $x is 42!"
  def describeColor(c: Colors.Green.type): String = s"Color is $c"

