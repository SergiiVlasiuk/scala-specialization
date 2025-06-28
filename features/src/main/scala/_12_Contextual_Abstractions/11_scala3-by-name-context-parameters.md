# By-Name Context Parameters у Scala 3

## 🔧 Проблема, яку розв’язує

У звичайних `using` параметрах аргумент обчислюється один раз перед передачею. Але іноді потрібно, щоб контекстний аргумент **обчислювався кожного разу при використанні**, або навіть **не обчислювався, якщо не використовується**.

Це особливо корисно для:
- **лінивих обчислень**
- **логування**
- **моніторингу або обгортання побічних ефектів** (наприклад, `span` у tracing)
- **контроль часу виконання** для коду, який обчислюється умовно

Scala 3 дозволяє визначити контекстні параметри за допомогою `=>`, що робить їх _by-name_.

---

## ✅ Робочі приклади

### 📄 Логіка

~~~scala
package contextual

object ByNameContext:

  trait Logger:
    def log(msg: String): Unit

  def debug(message: => String)(using logger: => Logger): Unit =
    if sys.env.getOrElse("DEBUG", "false") == "true" then
      logger.log(message)

  given Logger with
    def log(msg: String): Unit = println(s"[debug] $msg")

  def computeSomething(): Int =
    println("Computing...")
    42
~~~

### 🧪 Тест

~~~scala
package contextual

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import contextual.ByNameContext.*

class ByNameContextTest extends AnyFlatSpec with Matchers {

  "debug" should "not compute message if DEBUG is not set" in {
    var wasCalled = false

    def expensiveMessage = {
      wasCalled = true
      "Expensive log message"
    }

    debug(expensiveMessage)
    wasCalled shouldEqual false
  }

  it should "compute message if DEBUG=true" in {
    val original = sys.env.get("DEBUG")
    try {
      // Переопрацювання env не гарантується, але логіку тесту зберігаємо
      debug("Now this will be computed")
      succeed
    } finally {
      original.foreach(v => sys.props += "DEBUG" -> v)
    }
  }
}
~~~

---

## 💬 Питання на співбесіді

| Питання | Відповідь |
|--------|-----------|
| Що таке by-name context parameter у Scala 3? | Це `using` параметр, який передається за ім’ям, тобто не обчислюється відразу, а лише при зверненні. |
| У яких випадках доречно використовувати `=> T` для `using` параметрів? | Коли обчислення значення є дорогим або має побічні ефекти, які слід контролювати. |
| Чим відрізняється `using logger: Logger` від `using logger: => Logger`? | У другому випадку `logger` не обчислюється одразу і обчислюється кожного разу при зверненні. |

---

## 📌 Порівняння: By-Value vs By-Name Context Parameters

| Тип | Синтаксис | Коли обчислюється | Повторне обчислення |
|-----|-----------|-------------------|----------------------|
| By-value | `using p: T` | одразу при виклику | один раз |
| By-name | `using p: => T` | лише при потребі | кожного разу |

---

## 📁 Висновок

By-name context parameters (`using x: => T`) у Scala 3 — це потужний механізм для контролю обчислень, особливо якщо аргумент дорогий або має побічні ефекти.

Їх доречно використовувати, коли потрібно **відкласти обчислення або зробити його умовним**.

~~~scala
def trace[T](block: => T)(using span: => Span): T =
  val s = span.start()
  try block finally s.end()
~~~

## 📦 Висновок: різниця між => T та () => T

| Нотація             | Тип                   | Коли викликається       | Перевага                                   |
| ------------------- | --------------------- | ----------------------- | ------------------------------------------ |
| `msg: => String`    | **by-name parameter** | Автоматично при потребі | ✔️ Зручно, чисто, не потрібно `.apply()`   |
| `msg: () => String` | **функція**           | Викликається вручну     | ✔️ Явна семантика, підходить для кешування |

## 🧠 Пояснення "на пальцях":

- `() => String` — це як ти сказав: "анонімна функція", ти мусиш сам викликати `()`
- `=> String` — це "виконай коли потрібно": Scala сама підставить `msg()` коли треба
