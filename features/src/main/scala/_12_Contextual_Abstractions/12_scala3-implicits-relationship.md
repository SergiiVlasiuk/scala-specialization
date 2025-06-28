# Relationship with Scala 2 Implicits

## Яку проблему розвʼязує

Перехід від `implicit` у Scala 2 до `given`, `using`, `summon`, та `extension` у Scala 3 розвʼязує кілька ключових проблем:

- **Нечитабельність**: `implicit` мав перевантажену семантику — для параметрів, конверсій, тип-класів тощо.
- **Неявні конфлікти**: Колізії `implicit` могли виникати без зрозумілих помилок.
- **Складність у навчанні**: Поведінка `implicit resolution` була нетривіальною для новачків.
- **Немає прозорості намірів**: Не було зрозуміло, для чого використовується `implicit` в конкретному місці.

Scala 3 вводить **контекстні абстракції**, що розділяють ці ролі на окремі, зрозумілі конструкції.

---

## 1. Робочі приклади

### ❗ Scala 2: Implicit parameter

~~~scala
def greet(name: String)(implicit prefix: String): String =
  s"$prefix $name"

implicit val hello = "Hello"
println(greet("world")) // Hello world
~~~

### ✅ Scala 3: Using / Given

~~~scala
def greet(name: String)(using prefix: String): String =
  s"$prefix $name"

given String = "Hello"
println(greet("world")) // Hello world
~~~

---

### ❗ Scala 2: Implicit conversion

~~~scala
implicit def intToString(x: Int): String = x.toString
val s: String = 42 // Implicitly converted
~~~

### ✅ Scala 3: Given Conversion

~~~scala
import scala.language.implicitConversions
import scala.util.Conversion

given Conversion[Int, String] with
  def apply(x: Int): String = x.toString

val s: String = 42
~~~

---

### ❗ Scala 2: Type Class

~~~scala
trait Show[T] { def show(v: T): String }
implicit val intShow: Show[Int] = (v: Int) => v.toString

def printValue[T](v: T)(implicit s: Show[T]) = s.show(v)
~~~

### ✅ Scala 3: Type Class

~~~scala
trait Show[T]:
  def show(v: T): String

given Show[Int] with
  def show(v: Int): String = v.toString

def printValue[T](v: T)(using s: Show[T]) = s.show(v)
~~~

---

## 2. Тести до прикладів

~~~scala
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Scala2CompatTest extends AnyFlatSpec with Matchers:

  given String = "Hello"

  "greet" should "work with using" in {
    greet("Scala 3") shouldEqual "Hello Scala 3"
  }

  "printValue" should "use given Show[Int]" in {
    printValue(42) shouldEqual "42"
  }

  import scala.language.implicitConversions
  import scala.util.Conversion

  given Conversion[Int, String] with
    def apply(x: Int): String = s"Number($x)"

  "Conversion" should "convert Int to String" in {
    val s: String = 10
    s shouldEqual "Number(10)"
  }
~~~

---

## 3. Узагальнююча таблиця

| Концепція                | Scala 2         | Scala 3           |
|--------------------------|------------------|--------------------|
| Неявні параметри         | `implicit`       | `using` / `given`  |
| Неявне перетворення      | `implicit def`   | `given Conversion` |
| Тип-класи                | `implicit` lookup| `given` + `using`  |
| Виведення значень        | `implicitly[T]`  | `summon[T]`        |
| Розширення типів         | `implicit class` | `extension`        |

---

## 4. Потенційні питання для співбесіди

### ❓ У чому проблема з `implicit` у Scala 2?
**Відповідь:** Його семантика надто перевантажена: він відповідає за параметри, конверсії, тип-класи, DSL — і це ускладнює читабельність, відлагодження і вивчення.

### ❓ Чим `using` / `given` кращі?
**Відповідь:** Вони роблять ролі неявних залежностей явними: `given` — це надання залежності, `using` — споживання.

### ❓ Чим `summon[T]` відрізняється від `implicitly[T]`?
**Відповідь:** Семантично це те саме, але `summon` є новим стилем у Scala 3, і краще передає намір.

### ❓ Чи можна використовувати Scala 2 implicits у Scala 3?
**Відповідь:** Так, Scala 3 підтримує `implicit` для зворотної сумісності. Але рекомендовано переходити на `given`, `using`, `summon`, `extension`.

---

**🎯 Висновок:** Scala 3 контекстні абстракції дозволяють **розмежувати неявні механізми**, зробивши код більш **прозорим, модульним та безпечним**.