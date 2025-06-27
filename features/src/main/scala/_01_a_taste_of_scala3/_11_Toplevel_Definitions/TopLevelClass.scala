package _01_a_taste_of_scala3._11_Toplevel_Definitions

class User(val name: String):
  def greet(): String = s"Hello, $name!"

object User:
  def apply(name: String) = new User(name)
