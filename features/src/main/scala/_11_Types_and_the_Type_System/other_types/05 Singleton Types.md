# Singleton Types у Scala 3

## Що таке Singleton Types?

Singleton type — це тип, який представляє **одне єдине значення**. Наприклад, `val red = "red"` має тип `String`, але `red.type` — це **тип саме цього значення**, не будь-якого іншого рядка.

## Навіщо вони потрібні?

- Щоб виразити **типи залежні від значень**
- Для **строгих гарантій типобезпеки**
- У DSL, конфігураціях, серіалізації, безпечних API

---

## Приклади

### Приклад 1: Тип залежить від значення

```scala
val red = "red"
val blue = "blue"

def onlyRed(x: red.type): String = s"You passed $x"

@main def singletonExample1() =
  println(onlyRed(red))       // OK
  // println(onlyRed(blue))   // Compile error
```

---

### Приклад 2: Тип значення як тип

```scala
val num = 42

def expect42(x: num.type): String = s"Confirmed: $x is 42!"

@main def singletonExample2() =
  println(expect42(num))      // OK
```

---

### Приклад 3: Singleton тип обʼєкта

```scala
object Colors:
  val Green = "green"

def describeColor(c: Colors.Green.type): String = s"Color is $c"

@main def singletonExample3() =
  println(describeColor(Colors.Green)) // OK
```

---

## Тести

```scala
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SingletonTypesTest extends AnyFlatSpec with Matchers {

  val red = "red"
  val num = 42
  object Colors:
    val Green = "green"

  def onlyRed(x: red.type): String = s"You passed $x"
  def expect42(x: num.type): String = s"Confirmed: $x is 42!"
  def describeColor(c: Colors.Green.type): String = s"Color is $c"

  "onlyRed" should "accept only the singleton red" in {
    onlyRed(red) shouldEqual "You passed red"
  }

  "expect42" should "accept only value 42 tied to num" in {
    expect42(num) shouldEqual "Confirmed: 42 is 42!"
  }

  "describeColor" should "work only with Colors.Green" in {
    describeColor(Colors.Green) shouldEqual "Color is green"
  }
}
```

---

## Питання для співбесіди

### ❓ Що таке singleton type?

🔹 **Відповідь:** Тип, що представляє єдине можливе значення. Наприклад, `x.type`.

---

### ❓ Навіщо вони потрібні?

🔹 Для строгого контролю: наприклад, прийняти **тільки певне значення**, не просто тип.

---

### ❓ Різниця між `"red"` і `red.type`?

🔹 `"red"` — це `String`, `red.type` — це тип **саме значення `red`**, не будь-якого рядка.

---

### ❓ Як використовуються на практиці?

🔹 Типобезпечні API, залежні типи, гарантована робота з фіксованими значеннями.

---

### ❓ Чи можна створити singleton type для обʼєкта?

🔹 Так. Наприклад, `Colors.Green.type`.

