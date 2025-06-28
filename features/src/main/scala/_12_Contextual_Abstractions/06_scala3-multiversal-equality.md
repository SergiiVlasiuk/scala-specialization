# Multiversal Equality у Scala 3

## Проблема

У Scala 2 можна порівнювати будь-які два значення за допомогою `==`, навіть якщо ці значення абсолютно несумісні за типом:

~~~scala
1 == "hello" // false, але типово дозволено
~~~

Це може призвести до **помилок, які виявляються лише під час виконання**, а не під час компіляції.  
Scala 3 вводить **Multiversal Equality** — механізм, який дозволяє **типобезпечно** контролювати, які типи можна порівнювати.

---

## 📘 Що таке `derives`?

У Scala 3 ключове слово `derives` автоматизує створення `given`-екземплярів для певних type classes. Наприклад:

~~~scala
case class User(id: Int, name: String) derives CanEqual
~~~

Це автоматично створює `given CanEqual[User, User] = CanEqual.derived`, якщо `CanEqual` має механізм автоматичного виводу (`CanEqual.derived`).

---

## Приклади

### ✅ Включення strict equality

~~~scala
import scala.language.strictEquality

case class User(id: Int, name: String)
case class Order(id: Int)

val u = User(1, "Alice")
val o = Order(1)

// println(u == o) // ❌ компілятор скаже: Values of types User and Order cannot be compared with == or !=
~~~

---

### 📦 Як дозволити порівняння явно

~~~scala
import scala.language.strictEquality

case class User(id: Int, name: String) derives CanEqual
case class Order(id: Int) derives CanEqual

given CanEqual[User, User] = CanEqual.derived
given CanEqual[Order, Order] = CanEqual.derived

val u1 = User(1, "Alice")
val u2 = User(2, "Bob")
val o = Order(1)

val cmp1 = u1 == u2 // ✅ дозволено
// val cmp2 = u1 == o  // ❌ компілятор не дозволить
~~~

---

## Тести

~~~scala
package multiversalequality

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import scala.language.strictEquality

case class User(id: Int, name: String) derives CanEqual
case class Order(id: Int) derives CanEqual

given CanEqual[User, User] = CanEqual.derived
given CanEqual[Order, Order] = CanEqual.derived

class EqualityTest extends AnyFlatSpec with Matchers {
  "Equality" should "allow same-type comparisons" in {
    val u1 = User(1, "A")
    val u2 = User(1, "A")
    (u1 == u2) shouldEqual true
  }

  it should "not compile for different types if no CanEqual given" in {
    // This test is compile-time only: uncommenting the next line should fail compilation
    // val o = Order(1)
    // u1 == o
    succeed
  }
}
~~~

---

## Переваги

- 💥 **Типобезпека при порівнянні** — жодних `==` між яблуками і апельсинами
- 👮 **Строгий контроль через CanEqual**
- 🔍 **Покращена виявлюваність помилок на етапі компіляції**

---

## Порівняння Scala 2 vs Scala 3

| Особливість                     | Scala 2                      | Scala 3 з strictEquality     |
|--------------------------------|------------------------------|------------------------------|
| Порівняння різних типів        | Дозволено                    | Заборонено (без CanEqual)    |
| Контроль через типову систему  | Немає                        | Є (`CanEqual`)               |
| Підтримка derive               | Немає                        | Є (`derives CanEqual`)       |

---

## Питання та відповіді для співбесіди

### ❓ Що таке Multiversal Equality?

**Відповідь:**  
Механізм Scala 3, який забороняє порівнювати об'єкти несумісних типів без явного `CanEqual`, щоб уникнути помилок часу виконання.

---

### ❓ Як включити Multiversal Equality?

**Відповідь:**  
Через `import scala.language.strictEquality`.

---

### ❓ Як дозволити порівняння двох типів?

**Відповідь:**  
Оголосити `given CanEqual[A, B]`.

~~~scala
given CanEqual[User, User] = CanEqual.derived
~~~

---

### ❓ Чи можна зробити це автоматично?

**Відповідь:**  
Так, якщо клас оголошено з `derives CanEqual`.

---

### ❓ Яка альтернатива CanEqual в Scala 2?

**Відповідь:**  
Немає строгої альтернативи. У Scala 2 всі типи можна було порівнювати без обмежень.

---

## Висновок

Multiversal Equality забезпечує безпечне порівняння об'єктів у Scala 3, допомагає уникнути помилок і сприяє надійному дизайну типів.