package _11_Types_and_the_Type_System.variance

class InvariantBox[A](var value: A): // 'A' є інваріантним за замовчуванням
  def get: A = value

  def set(newValue: A): Unit = {
    value = newValue
  }

object InvariantExample {
  def main(args: Array[String]): Unit = {
    val animalBox: InvariantBox[Animal] = new InvariantBox(new Animal())
    val dogBox: InvariantBox[Dog] = new InvariantBox(new Dog())

    // val incompatibleBox: InvariantBox[Animal] = dogBox // ПОМИЛКА: Не можна присвоїти InvariantBox[Dog] до InvariantBox[Animal]
    // val incompatibleBox2: InvariantBox[Dog] = animalBox // ПОМИЛКА: Не можна присвоїти InvariantBox[Animal] до InvariantBox[Dog]

    println("Інваріантність: Box[Dog] не є підтипом Box[Animal]")

    println(s"Animal in animalBox: ${animalBox.get}")
    animalBox.set(new Cat) // Можемо змінити на іншого Animal
    println(s"New animal in animalBox: ${animalBox.get}")
    animalBox.set(new Dog) // Можемо змінити на іншого Animal знов
    println(s"Another new animal in animalBox: ${animalBox.get}")

    // Можемо присвоїти InvariantBox[Animal] до InvariantBox[Animal]
    val anotherAnimalBox: InvariantBox[Animal] = animalBox
    println(s"Значення animalBox: ${anotherAnimalBox.value}")

    // Можемо присвоїти InvariantBox[Dog] до InvariantBox[Dog]
    val anotherDogBox: InvariantBox[Dog] = dogBox
    println(s"Значення dogBox: ${anotherDogBox.value}")
  }
}