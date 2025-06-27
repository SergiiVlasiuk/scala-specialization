# Eta-Expansion у Scala 3

## Визначення

- **Метод** — член класу або об'єкта з ім'ям та параметрами.
- **Функція** — значення, яке можна передати і викликати, реалізує функціональний тип.
- **Eta-expansion** — автоматичне перетворення методу у функцію, коли це потрібно.

## Відмінності між методами та функціями

| Характеристика     | Метод                       | Функція (Function)               |
|--------------------|-----------------------------|---------------------------------|
| Що це?             | Член класу/обʼєкта          | Значення, що містить лямбду      |
| Як визначається?   | `def f(...) = ...`           | `val f = (...) => ...` або `FunctionN` реалізація |
| Виклик             | Викликається напряму         | Викликається через apply         |
| Передача           | Не є значенням (не можна напряму передати) | Можна передавати як значення      |
| Використання       | Виконує логіку, визначену в класі/обʼєкті | Функції як «першокласні» значення |

## Коли відбувається eta-expansion?

- При передачі методу у контекст, де очікується функція (function value).
- При присвоєнні методу змінній типу функції.
- При виклику вищих порядкових функцій (функцій, які приймають інші функції).

## Коли не відбувається eta-expansion?

- Якщо викликаємо метод (ставимо круглі дужки) — метод виконається.
- Якщо тип не очікує функцію.
- Якщо метод є безпараметровим (`def foo: Int`), то він не буде розширений у функцію.

## Як зробити eta-expansion вручну?

```scala
def add(x: Int, y: Int): Int = x + y

val f1: (Int, Int) => Int = add _      // Scala 2 - "underscore" для явної eta-expansion
val f2: (Int, Int) => Int = (x, y) => add(x, y)  // лямбда

// У Scala 3 underscore зазвичай не потрібен, але він все ще підтримується для сумісності
val f3: (Int, Int) => Int = add
```

## Production Use-case'и
### Приклад 1: Передача методу як функції в API
```scala 3
object MathUtils {
  def square(x: Int): Int = x * x
}

val numbers = List(1, 2, 3, 4, 5)
val squares = numbers.map(MathUtils.square)  // eta-expansion: метод -> функція
```

### Приклад 2: Callback
```scala 3
class Button {
  private var onClickHandler: () => Unit = () => ()

  def setOnClick(handler: () => Unit): Unit = onClickHandler = handler

  def click(): Unit = onClickHandler()
}

object MyApp {
  def handleClick(): Unit = println("Clicked!")

  val button = new Button

  button.setOnClick(handleClick)  // eta-expansion
  button.click()                  // "Clicked!"
}
```

### Приклад 3: Використання з Futures
```scala 3
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

def computeLength(s: String): Int = s.length

val futureResult: Future[Int] = Future("hello").map(computeLength)
```

## Тести (ScalaTest)
```scala 3
import org.scalatest.funsuite.AnyFunSuite

object EtaExpansionExample:
  def multiply(x: Int, y: Int): Int = x * y
  def greet(name: String): String = s"Hello, $name"
  def noParams(): String = "No params here"

class EtaExpansionExampleSpec extends AnyFunSuite:

  import EtaExpansionExample._

  test("multiply method can be assigned to a function") {
    val f: (Int, Int) => Int = multiply
    assert(f(3, 4) == 12)
  }

  test("greet method can be passed as a function") {
    val names = List("Alice", "Bob")
    val greetings = names.map(greet)
    assert(greetings == List("Hello, Alice", "Hello, Bob"))
  }

  test("noParams method is not eta-expanded automatically") {
    val f: () => String = () => noParams()
    assert(f() == "No params here")
  }
```

## Потенційні питання на співбесіді:

| Питання                                                   | Відповідь                                                                                                  |
| --------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------- |
| Що таке eta-expansion?                                    | Автоматичне перетворення методу у функцію при необхідності.                                                |
| Чим відрізняється метод від функції у Scala?              | Метод — член класу/обʼєкта, функція — значення, що є обʼєктом функціонального типу.                        |
| Коли відбувається автоматична eta-expansion?              | Коли метод передається у контекст, де очікується функція (value).                                          |
| Чи можна вручну викликати eta-expansion?                  | Так, через underscore `_` (в Scala 2) або лямбду `(x => method(x))`. В Scala 3 `_` використовується рідше. |
| Чи відбувається eta-expansion для методів без параметрів? | Ні, методи без параметрів не конвертуються автоматично у функції.                                          |

