# Extension Methods in Scala 3

---

## 1. Що таке Extension Methods?

**Extension Methods** дозволяють **додавати нові методи до існуючих типів** без модифікації їхнього коду чи створення підкласів. Це альтернатива імпліцитним класам з Scala 2, реалізована на рівні мови.

---

## 2. Проблема, яку розвʼязують Extension Methods

У Scala до появи extension methods часто доводилось:
- використовувати **implicit клас** або **implicit conversion**, що знижувало зрозумілість і безпеку
- не можна було додавати методи безпосередньо до типів, які ти не контролюєш (наприклад, з Java)

Extension Methods надають **типобезпечний, декларативний та зрозумілий** спосіб розширення API сторонніх або базових типів.

---

## 3. Робочі приклади (Scala 3)

### 📌 Приклад 1: Просте розширення Int

```scala
extension (x: Int)
  def squared: Int = x * x

@main def demo1() =
  println(5.squared) // 25
```

---

### 📌 Приклад 2: Розширення для String

```scala
extension (s: String)
  def isUpperCase: Boolean = s == s.toUpperCase

@main def demo2() =
  println("HELLO".isUpperCase) // true
```

---

### 📌 Приклад 3: Генерик з контекстом (type class style)

```scala
trait Show[T]:
  def show(value: T): String

given Show[Int] with
  def show(value: Int) = s"Int($value)"

extension [T](value: T)(using s: Show[T])
  def show: String = s.show(value)

@main def demo3() =
  println(42.show) // Int(42)
```

---

## 4. Тести (ScalaTest)

```scala
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ExtensionMethodsTest extends AnyFlatSpec with Matchers {

  extension (x: Int)
    def squared: Int = x * x

  extension (s: String)
    def isUpperCase: Boolean = s == s.toUpperCase

  trait Show[T]:
    def show(value: T): String

  given Show[Int] with
    def show(value: Int): String = s"Int($value)"

  extension [T](value: T)(using s: Show[T])
    def show: String = s.show(value)

  "squared" should "square an integer" in {
    5.squared shouldEqual 25
  }

  "isUpperCase" should "return true if string is upper case" in {
    "HELLO".isUpperCase shouldBe true
    "Hello".isUpperCase shouldBe false
  }

  "show" should "work with Show typeclass" in {
    42.show shouldEqual "Int(42)"
  }
}
```

---

## 5. Питання та відповіді для співбесіди

### ❓ Що таке extension method у Scala?

🔹 Це метод, який додається до існуючого типу без змін у самому типі. Використовується ключове слово `extension`.

---

### ❓ У чому різниця між implicit class і extension method?

🔹 Extension methods є **рідною конструкцією мови у Scala 3**, тоді як implicit class — обхідний шлях у Scala 2. Extension methods **краще підтримуються компілятором** та **легше читаються**.

---

### ❓ Чи можна оголошувати extension з контекстом (using)?

🔹 Так. Це дозволяє створювати методи, які працюють лише коли існує певний `given`.

---

### ❓ Чи extension methods підтримують параметри типу?

🔹 Так, можна використовувати `[T]` для узагальнення методу.

---

### ❓ Чи extension methods можуть бути визначені в обʼєкті?

🔹 Так. Це найкраща практика для організації таких методів (інкапсуляція).

---

## 6. Переваги Extension Methods

- Безпечніші за implicit conversions
- Краще читаються та інтегруються з IDE
- Можна групувати у `object`
- Працюють із типовими класами (`Show`, `Eq` тощо)
- Добре підходять для DSL-подібного синтаксису

