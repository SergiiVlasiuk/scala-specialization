package _11_Types_and_the_Type_System.variance

trait ContravariantProcessor[-A] { // '-A' робить 'A' контрваріантним
  def process(value: A): Unit
  // def getResult: A // ПОМИЛКА: Не можна "виробляти" контрваріантний тип
}

class AnimalProcessor extends ContravariantProcessor[Animal] {
  override def process(value: Animal): Unit = println(s"Processing Animal: $value")
}

class DogProcessor extends ContravariantProcessor[Dog] {
  override def process(value: Dog): Unit = println(s"Processing Dog: $value")
}

object ContravariantExample extends App {
  val animalProcessor: ContravariantProcessor[Animal] = new AnimalProcessor
  val dogProcessor: ContravariantProcessor[Dog] = new DogProcessor

  // animalProcessor МОЖЕ бути присвоєний змінній типу ContravariantProcessor[Dog], оскільки ContravariantProcessor контрваріантний
  val test: ContravariantProcessor[Dog] = animalProcessor // ЦЕ ПРАВИЛЬНО!
  // Чому? Бо ContravariantProcessor[Animal] може обробити будь-якого Animal, включаючи Dog.
  // Отже, якщо нам потрібен ContravariantProcessor[Dog], ContravariantProcessor[Animal] підходить.

  test.process(new Dog) // Це нормально, animalProcessor може обробити Dog
  // test.process(new Cat) // Це неможливо, бо test є ContravariantProcessor[Dog]

  dogProcessor.process(new Dog)
  // dogProcessor.process(new Animal) // ПОМИЛКА КОМПІЛЯЦІЇ: DogProcessor очікує Dog, а не Animal
}