# Context Functions in Scala 3

## Проблема, яку розв’язує Context Functions

Context functions розширюють можливості передачі **контексту** в функції. Вони дозволяють описувати залежність функції від неявного (contextual) аргументу **в сигнатурі типу**, що робить функціональні програми виразнішими та зручнішими для композиції.

### Приклад проблеми:

У Scala 2/3 з `given/using` можна писати:

~~~scala
def greet(name: String)(using prefix: String): String = s"$prefix $name"
~~~

Але якщо ми хочемо передавати `greet` як функцію, то її тип **не відображає**, що вона залежить від контекстного параметра.

Context functions дозволяють це зробити явно:
~~~scala
val greetFunc: String ?=> String => String = greet
~~~

## Синтаксис

Тип `A ?=> B` — це **функція, яка приймає неявний аргумент типу A та повертає значення типу B**.

---

## Робочі приклади

~~~scala
package _12_Contextual_Abstractions

object ContextFunctions:

  case class Config(appName: String)

  def logger(msg: String): Config ?=> String =
    s"[${summon[Config].appName}] $msg"

  def process(msg: String)(log: String => Unit)(using Config): Unit =
    log(logger(msg))

  def getLogger: Config ?=> String => Unit =
    msg => println(logger(msg))
~~~

---

## Робочі тести

~~~scala
package _12_Contextual_Abstractions

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import _12_Contextual_Abstractions.ContextFunctions.*

class ContextFunctionsTest extends AnyFlatSpec with Matchers {

  given Config = Config("ContextApp")

  "logger" should "log with context config" in {
    logger("Start") shouldEqual "[ContextApp] Start"
  }

  "process" should "invoke logger and pass to function" in {
    var output = ""
    process("Run") { msg => output = msg }
    output shouldEqual "[ContextApp] Run"
  }

  "getLogger" should "return context function" in {
    val logFn = getLogger
    noException should be thrownBy logFn("Hello")
  }
}
~~~

---

## Потенційні питання та відповіді для співбесіди

### ❓ Що таке Context Function у Scala 3?
**Відповідь:** Це функція, яка приймає неявні (contextual) аргументи, описані в її типі за допомогою `?=>`.

---

### ❓ Як типізувати функцію, яка приймає неявний параметр?
~~~scala
val log: Config ?=> String => Unit = msg => println(...)
~~~

---

### ❓ У чому користь від Context Functions?
- Дає змогу явно вказати залежність від контексту в типі.
- Покращує композицію функцій у FP.
- Дозволяє уникнути зайвих обгорток або передачі параметрів вручну.

---

### ❓ Чим Context Function відрізняється від звичайної функції з `using`?
**Відповідь:** Context function має тип `A ?=> B`, який **вказує на залежність** від контексту, тоді як `using` параметр прихований у реалізації.

---

## Порівняльна таблиця

| Особливість                        | Context Function         | Звичайна функція з `using` |
|-----------------------------------|--------------------------|-----------------------------|
| Тип включає контекст              | ✅                       | ❌                         |
| Можна передавати як значення      | ✅                       | ✅                         |
| Явне позначення залежностей       | ✅                       | ❌                         |
| Композиція в FP                   | ✅                       | Частково                   |

---