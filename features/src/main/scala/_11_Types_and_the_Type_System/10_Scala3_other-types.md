# Other Types in Scala

Цей розділ охоплює потужні типові механізми в Scala: type lambdas, match types, existential types, higher-kinded types, singleton types, refinement types, kind polymorphism.

---

## Type Lambdas

Type lambda — це анонімна функція на рівні типів.

```scala
type EitherString[A] = Either[String, A]
type MyLambda = [A] =>> Either[String, A]
```

Тепер `MyLambda[Int]` дорівнює `Either[String, Int]`.

---

## Match Types

У Scala 3 можна писати патерн-матчинг на рівні типів.

```scala
type Elem[X] = X match {
  case String => Char
  case Array[t] => t
  case Iterable[t] => t
}
```

```scala
def first[X](x: X)(using ev: X match {
  case String => true
  case Iterable[_] => true
}): Elem[X] = x match {
  case s: String => s.charAt(0)
  case i: Iterable[_] => i.head
}
```

---

## Existential Types

Типи, що включають "існує тип A такий, що...".

Scala 3 заохочує використовувати `Match types` або `Type members`, але в Scala 2:

```scala
def foo(list: List[_]): Unit = list.foreach(println)
```

Або з типовим свідченням:

```scala
def bar(list: List[T] forSome { type T }): Unit = ???
```

---

## Higher-Kinded Types

Це типи, які приймають типи з параметрами як аргументи.

```scala
trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}
```

Приклад: `Option`, `List`, `Either[E, _]` можуть бути передані як `F`.

---

## Singleton Types

Це типи значень, а не лише класів:

```scala
val x = "hello"
val y: x.type = x // тільки це конкретне значення x
```

---

## Refinement Types

Дозволяють розширити існуючий тип новими членами:

```scala
type Closeable = {
  def close(): Unit
}

def closeAll(items: List[Closeable]): Unit =
  items.foreach(_.close())
```

---

## Kind Polymorphism

Це здатність писати код, який працює з типами незалежно від "арності" типу:

```scala
trait PolyKind[F[_[_]]] // працює з типами, які приймають параметризовані типи
```

Підтримується у Scala 3.

---

## Тести

```scala
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class OtherTypesSpec extends AnyFlatSpec with Matchers {

  "Type Lambda" should "produce correct type alias" in {
    type MyLambda = [A] =>> Either[String, A]
    val v: MyLambda[Int] = Right(42)
    v shouldBe Right(42)
  }

  "Match Type" should "extract element type" in {
    type Elem[X] = X match
      case String => Char
      case Array[t] => t
      case Iterable[t] => t

    def getFirst[X](x: X): Elem[X] = x match
      case s: String => s.charAt(0).asInstanceOf[Elem[X]]
      case i: Iterable[?] => i.head.asInstanceOf[Elem[X]]

    getFirst("hello") shouldBe 'h'
    getFirst(List(1,2,3)) shouldBe 1
  }

  "Singleton Type" should "refer to specific value" in {
    val x = "abc"
    val y: x.type = x
    y shouldBe "abc"
  }

  "Refinement Type" should "work with structural typing" in {
    type Closeable = { def close(): Unit }

    var closed = false
    val resource = new {
      def close(): Unit = closed = true
    }

    def closeAll(items: List[Closeable]): Unit = items.foreach(_.close())

    closeAll(List(resource))
    closed shouldBe true
  }
}
```

---

## Питання для співбесіди з відповідями

**1. Що таке type lambda?**  
Type lambda — це анонімна функція типів, записується як `[A] =>> F[A]`.

---

**2. Як працюють match types?**  
Це pattern matching на рівні типів: тип виводиться залежно від форми іншого типу.

---

**3. Чим відрізняються higher-kinded types від звичайних?**  
Звичайні типи приймають параметри, HKT — параметризовані типи (`F[_]`).

---

**4. Навіщо потрібні singleton types?**  
Щоб обмежити значення тільки конкретним об'єктом, або працювати з залежними типами.

---

**5. Що таке refinement types?**  
Це типи, які додають нові члени до існуючого типу. Використовуються в structural typing.

---

**6. Що таке kind polymorphism?**  
Можливість писати код, який працює з типами будь-якої "форми" — наприклад, з `F[_]`, `G[_[_]]`, тощо.