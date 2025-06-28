# Conditional Given Instances у Scala 3

## Проблема

У реальних проектах часто потрібна **умовна логіка для підбору `given`-екземпляра**.  
Приклад:
- У вас є `given Show[T]`, який повинен працювати **тільки якщо** для `T` вже існує `Show[String]`, або якщо `T` є певного виду.

У Scala 2 таку поведінку реалізовували через імпліцити з `context bounds` + `implicitly`, або через `LowPriorityImplicits`.

**Scala 3** вводить підтримку **умовних `given` (Conditional Given Instances)** — ви можете описати залежності прямо в сигнатурі `given`.

---

## Приклади

### 🔧 Логіка: типклас `Show`, умовне виведення `Show[List[T]]`

~~~scala
package conditionalgiventest

trait Show[T]:
  def show(value: T): String

object Show:
  given Show[Int] with
    def show(value: Int): String = s"Int($value)"

  given [T](using s: Show[T]): Show[List[T]] with
    def show(value: List[T]): String =
      value.map(s.show).mkString("[", ", ", "]")

def printValue[T](value: T)(using sh: Show[T]): String =
  sh.show(value)
~~~

---

## Тести

~~~scala
package conditionalgiventest

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import Show.given

class ConditionalGivenTest extends AnyFlatSpec with Matchers {

  "printValue" should "work for Int" in {
    printValue(42) shouldEqual "Int(42)"
  }

  it should "work for List[Int] via conditional given" in {
    printValue(List(1, 2)) shouldEqual "[Int(1), Int(2)]"
  }

  it should "fail to compile for unsupported type" in {
    """printValue(List("hello"))""" shouldNot compile
  }
}
~~~

---

## Порівняння: Scala 2 vs Scala 3

| Особливість                         | Scala 2                                 | Scala 3                                      |
|------------------------------------|------------------------------------------|-----------------------------------------------|
| Умовне виведення типкласу          | Неявно через пріоритет імпліцитів        | Прямо через `given [T](using ...)`            |
| Потреба в LowPriorityImplicits     | Часто                                     | Не потрібно                                   |
| Простота читання                   | Низька                                    | Висока                                       |
| Явне визначення залежностей        | Немає                                     | Так — через `using` в сигнатурі               |

---

## Питання та відповіді для співбесіди

### ❓ Що таке conditional given?

**Відповідь:**  
Це `given`, який може бути надано **тільки якщо** існує інший `given`-екземпляр. Залежність описується явно через `using` у сигнатурі.

---

### ❓ Навіщо це потрібно?

**Відповідь:**  
Щоб забезпечити автоматичну генерацію `given` лише тоді, коли задовольняються певні умови. Наприклад, наявність `Show[T]` для побудови `Show[List[T]]`.

---

### ❓ Як виглядає синтаксис conditional given?

**Відповідь:**
~~~scala
given [T](using Show[T]): Show[List[T]] with { ... }
~~~

---

### ❓ Які є обмеження conditional given?

**Відповідь:**
- Не можна викликати `summon` у сигнатурі (але можна в тілі)
- Компілятор виводить залежності лише через `using`

---

### ❓ Чи можна використовувати `match types` з conditional given?

**Відповідь:**  
Так, можна комбінувати conditional given та match types для розгалужень залежно від типів.

---

## Висновок

**Conditional given** дозволяють:
- декларувати залежності між типкласами
- уникати дублювання
- писати гнучкий, типобезпечний код, що масштабовано розширюється

Це фундаментальна частина Scala 3 для побудови складних типових абстракцій.
