package a_taste_of_scala3._06_Methods

object EtaExpansion:
  def add(a: Int, b: Int): Int = a + b

  val addFunction: (Int, Int) => Int = add

