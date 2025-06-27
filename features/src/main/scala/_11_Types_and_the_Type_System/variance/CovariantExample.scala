package _11_Types_and_the_Type_System.variance

// covariant box
class ImmutableBox[+A](val value: A) { // '+A' робить 'A' коваріантним
  def get: A = value
  // def set(newValue: A): Unit = { value = newValue } // ПОМИЛКА: Не можна мати метод set з коваріантним типом
  // Чому? Бо метод set приймає A, тобто споживає його.
  // А коваріантний тип може лише виробляти значення.
}

object CovariantExample extends App {
  val dog: Dog = new Dog
  val animalBox: ImmutableBox[Animal] = new ImmutableBox(new Animal)
  val dogBox: ImmutableBox[Dog] = new ImmutableBox(dog)

  // dogBox МОЖЕ бути присвоєний animalBox, оскільки ImmutableBox коваріантний
  val test: ImmutableBox[Animal] = dogBox // ЦЕ ПРАВИЛЬНО!

  println(s"Animal in animalBox: ${animalBox.get}")
  println(s"Dog in dogBox: ${dogBox.get}")
  println(s"Dog in test (from dogBox): ${test.get}")

  // test.set(new Cat) // Це неможливо, оскільки ImmutableBox не має set
}