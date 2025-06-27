package _07_Functions

//def add(x: Int, y: Int): Int = x + y

val f: (Int, Int) => Int = add // Scala зробить eta-expansion

/**
Int => String           // Function1[Int, String]
(Int, Int) => Boolean   // Function2[Int, Int, Boolean]
() => Unit              // Function0[Unit]
*/
