# 📘 Scala 3 — Opaque Types

---

## 🔍 Що таке "Opaque Type"?

**Opaque types** — це спосіб створити абстракцію над існуючим типом, приховуючи його реалізацію **ззовні** модуля, але дозволяючи працювати з ним **всередині**.

---

## 🚧 Проблема: Абстракція з оверхедом

```scala
case class UserId(value: String)  // створює обʼєкт на heap, має runtime overhead
```

---

## ✅ Рішення: Opaque Type

```scala
object User:
  opaque type UserId = String

  def fromString(s: String): UserId = s
  def value(id: UserId): String = id

import User._

val id: UserId = fromString("abc-123")
val original: String = value(id)
```

### ❗ Ззовні `UserId` не є `String`:

```scala
val s: String = id  // ❌ Error: no implicit conversion
```

---

## 📦 Module Abstractions

Opaque types реалізуються **всередині обʼєктів або пакетів** і не є взаємозамінними з базовим типом зовні.

---

## 📋 Summary of Opaque Types

| Особливість                   | Значення                                               |
|------------------------------|---------------------------------------------------------|
| ✅ Продуктивність            | 0 runtime overhead — це просто `String` на JVM         |
| 🔐 Інкапсуляція              | Тип прихований зовні                                   |
| 🔄 Використання              | Тільки через API обʼєкта                               |
| 🔁 Inline at runtime         | JVM бачить просто `String`, але компілятор — `UserId` |

---

## 🔁 Приклад із типобезпекою

```scala
object Finance:
  opaque type Usd = BigDecimal
  opaque type Eur = BigDecimal

  object Usd:
    def apply(d: BigDecimal): Usd = d
  object Eur:
    def apply(d: BigDecimal): Eur = d

  def convert(usd: Usd): Eur = Eur(usd.asInstanceOf[BigDecimal] * 0.91)

import Finance.*

val usd: Usd = Usd(100)
val eur: Eur = convert(usd)

// val eur2: Eur = usd // ❌ Compilation error
```

---

## ✅ Тести (ScalaTest)

```scala
import org.scalatest.funsuite.AnyFunSuite
import User._
import Finance._

class OpaqueTypeTests extends AnyFunSuite:

  test("UserId from string and back") {
    val id = fromString("abc-123")
    assert(value(id) == "abc-123")
  }

  test("Opaque types prevent mixing types") {
    val usd: Usd = Usd(100)
    val eur: Eur = convert(usd)
    assert(eur.isInstanceOf[Eur])
  }
```

---

## 🧠 Питання для співбесіди

**1. Що таке opaque type у Scala 3?**  
> Це тип, який компілюється як інший тип (наприклад, `String`), але приховує свою природу зовні модуля.

---

**2. У чому різниця між `opaque type` і `type alias`?**  
> `type alias` — це просто псевдонім, повністю взаємозамінний. `opaque type` — ізольований ззовні, не взаємозамінний.

---

**3. Які переваги opaque type?**  
> Без оверхеду в runtime, типобезпека, інкапсуляція.

---

**4. Як забезпечується доступ до значення в opaque type?**  
> Через API в тому ж обʼєкті, де оголошено opaque type.

---

## 📌 Підсумок

- Opaque types — це спосіб створити "новий тип", який працює як існуючий тип на JVM, але захищений з боку API.
- Вони дозволяють інкапсулювати типи, не створюючи runtime-обʼєктів.
- Корисні в безпечному доменному програмуванні та проєктуванні API.