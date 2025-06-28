# Implicit Conversions у Scala 3

## Проблема

У деяких ситуаціях потрібно автоматично перетворювати об'єкти одного типу в інший:
- Наприклад, щоб використовувати методи, яких немає у типі `A`, але є у `B`.
- Або щоб функція `def f(b: B): Unit` приймала `A`, якщо існує правило `A => B`.

У Scala 2 це було можливо завдяки `implicit def`, але такий підхід був **небезпечний і неявний**.

**Scala 3 розв'язує це через контрольовані `given Conversion[A, B]`, які чітко виражають намір перетворення.**

---

## Приклади

### ✅ Логіка: Явно задане неявне перетворення типів

~~~scala
package conversionsdemo

import scala.language.implicitConversions

case class Rational(n: Int, d: Int):
  override def toString: String = s"$n/$d"

object Conversions:
  given Conversion[Int, Rational] with
    def apply(x: Int): Rational = Rational(x, 1)

def describe(r: Rational): String =
  s"This is a rational number: $r"
~~~

---

## Тести

~~~scala
package conversionsdemo

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import conversionsdemo.Conversions.given

class RationalConversionTest extends AnyFlatSpec with Matchers {

  "describe" should "accept Int via implicit conversion" in {
    val result = describe(3) // implicitly converted to Rational(3,1)
    result shouldEqual "This is a rational number: 3/1"
  }

  it should "also accept Rational directly" in {
    val result = describe(Rational(5, 2))
    result shouldEqual "This is a rational number: 5/2"
  }
}
~~~

---

## Контрприклад (для розуміння поведінки)

Якщо не імпортувати `given`, компіляція не пройде:

~~~scala
// Без імпорту given Conversion[Int, Rational] — компіляція з помилкою
val r: Rational = 5 // ERROR: no Conversion in scope
~~~

---

## Порівняння Scala 2 vs Scala 3

| Особливість                     | Scala 2                        | Scala 3                              |
|--------------------------------|--------------------------------|---------------------------------------|
| Механізм                        | `implicit def`                 | `given Conversion[A, B]`              |
| Контроль над неявністю         | Слабкий                        | Сильний, явно через `import given`    |
| Можливість аналізу коду        | Обмежена                       | Добра — все видно в імпорті           |
| Потрібен імпорт                 | Ні (часто глобально видно)     | Так: `import ... given`               |
| Ризик неочікуваних ефектів     | Високий                        | Значно менший                         |

---

## Питання та відповіді для співбесіди

### ❓ Що таке implicit conversion?

**Відповідь:**  
Механізм, який дозволяє автоматично перетворити значення одного типу в інший, коли цього вимагає контекст.

---

### ❓ Як оголосити implicit conversion у Scala 3?

**Відповідь:**  
За допомогою `given Conversion[A, B]` з імпортом `scala.language.implicitConversions`.

---

### ❓ Чому Scala 3 зробила ці перетворення більш контрольованими?

**Відповідь:**  
Щоб уникнути магії в коді, яка ускладнює читання, дебаг і передбачуваність. Scala 3 вимагає явного імпорту `given`.

---

### ❓ У чому різниця між implicit conversion та extension methods?

**Відповідь:**  
- `given Conversion[A, B]`: перетворює об'єкт одного типу на інший.
- `extension`: додає методи до існуючого типу без фактичного перетворення.

---

## Висновок

`implicit conversion` у Scala 3 — це керований, явний механізм, який розв'язує задачу автоматичного приведення типів, зберігаючи при цьому читабельність і контроль над кодом.

---

## 💡 Коментар щодо початкового прикладу

У наведеному тобою коді:
- `given Conversion[Int, Rational]` було в **компаньйон-обʼєкті `Rational`**
- Scala **автоматично підтягує given із компаньйона** для `Rational`, коли `Rational` використовується у сигнатурах або викликах.

Це *легальна поведінка*, але вона **підриває ціль вивчення explicit `import given`**, тому краще демонструвати приклади з відокремленим об’єктом `Conversions`.