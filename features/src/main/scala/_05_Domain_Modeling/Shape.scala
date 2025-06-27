package _05_Domain_Modeling

sealed trait Shape
case class Circle(radius: Double) extends Shape
case class Rectangle(w: Double, h: Double) extends Shape

/**
 ✅ Переваги OOP-моделі:
 Структура зрозуміла новачкам (Java-подібна)

 Інкапсуляція через методи в класах

 Pattern matching дає FP-можливості
 */
