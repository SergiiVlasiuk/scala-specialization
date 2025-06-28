# Type Class Derivation у Scala 3

## Проблема

Коли ви використовуєте тип-класи (наприклад, `Show`, `Eq`, `Encoder` тощо), часто доводиться вручну реалізовувати екземпляри для складених типів (`case class`, `sealed trait` тощо).

Це **виснажливо, шаблонно і призводить до дублікації**.  
Scala 3 пропонує рішення: **Derives Clauses** та **Type Class Derivation** — автоматичне виведення екземплярів тип-класів через `Mirror`.

---

## Приклад: типклас `Show`

~~~scala
package typeclassderivation

trait Show[T]:
  def show(value: T): String

object Show:
  given Show[Int] with
    def show(v: Int): String = v.toString

  // Автоматичне виведення для product types (case class)
  import scala.deriving.Mirror
  import scala.compiletime.{erasedValue, summonInline}

  inline def summonAll[T <: Tuple]: List[Show[_]] =
    inline erasedValue[T] match
      case _: EmptyTuple => Nil
      case _: (h *: t) => summonInline[Show[h]] :: summonAll[t]

  given derived[T](using m: Mirror.ProductOf[T]): Show[T] with
    def show(value: T): String =
      val elems = value.asInstanceOf[Product].productIterator.toList
      s"${m.mirrorLabel}(${elems.mkString(", ")})"
~~~

### Використання з `derives`

~~~scala
package typeclassderivation

import Show.given

case class Person(name: String, age: Int) derives Show
~~~

---

## Функція використання типкласу

~~~scala
package typeclassderivation

def printValue[T](value: T)(using sh: Show[T]): String =
  sh.show(value)
~~~

---

## Тести

~~~scala
package typeclassderivation

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import Show.given

class TypeClassDerivationTest extends AnyFlatSpec with Matchers {

  "printValue" should "show an Int" in {
    printValue(42) shouldEqual "42"
  }

  it should "show a derived Person" in {
    val p = Person("Alice", 30)
    printValue(p) should include ("Person(")
  }
}
~~~

---

## Порівняння: Ручне vs Автоматичне виведення

| Підхід                  | Scala 2                          | Scala 3                          |
|------------------------|----------------------------------|----------------------------------|
| Ручне написання Show   | ✔️                              | ✔️                              |
| Автоматичне `derives`  | ❌                              | ✔️ через Mirror/derives         |
| Boilerplate            | Високий                          | Мінімальний                     |

---

## Питання та відповіді для співбесіди

### ❓ Що таке тип-класне виведення?

**Відповідь:**  
Це механізм, який дозволяє автоматично створювати екземпляри тип-класів для типів, ґрунтуючись на їх структурі (наприклад, case class, sealed trait).

---

### ❓ Як це реалізовано в Scala 3?

**Відповідь:**  
Через `Mirror` та `inline`-метапрограмування. Працює разом з `derives`-клаузою.

---

### ❓ Яка роль `Mirror`?

**Відповідь:**  
`Mirror` — тип, що описує структуру case-класу або sealed trait. Використовується для отримання інформації про поля типу під час компіляції.

---

### ❓ Чи можна комбінувати ручне і автоматичне виведення?

**Відповідь:**  
Так. Ви можете надати ручний `given` для певного типу, а всі інші — згенеруються автоматично.

---

## Висновок

Type Class Derivation у Scala 3:
- спрощує код
- прибирає boilerplate
- дозволяє масштабовано будувати гнучкі абстракції

Незамінна фіча для бібліотек типу `circe`, `tapir`, `zio-json`, `magnolia`.
