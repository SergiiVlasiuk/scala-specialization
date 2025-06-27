# Intersection Types в Scala 3

## Що таке Intersection Type?

**Intersection Type** (`A & B`) складає тип, що об'єднує вимоги до об'єкта з обох типів `A` і `B`. Це означає, що змінна типу `A & B` повинна мати всі члени типів `A` та `B`.

---

## Приклад 1: Поєднання двох трейтів

```scala
trait HasName:
  def name: String

trait HasAge:
  def age: Int

def printPersonInfo(person: HasName & HasAge): String =
  s"${person.name} is ${person.age} years old"

class User(val name: String, val age: Int) extends HasName, HasAge

val user = new User("Olena", 30)
assert(printPersonInfo(user) == "Olena is 30 years old")
```

---

## Приклад 2: Intersection у параметрах

```scala
def doSomething(entity: Cloneable & Serializable): Unit =
  println("Can be cloned and serialized")

val data = "Hello".asInstanceOf[Cloneable & Serializable]
doSomething(data) // OK
```

---

## Тестування Intersection Types

```scala
test("intersection types allow combining traits"):
  class Entity(val name: String, val age: Int) extends HasName, HasAge
  val e = new Entity("Max", 42)
  assert(printPersonInfo(e) == "Max is 42 years old")
```

---

## Використання alias для Intersection Type

У Scala можна надати ім'я intersection типу за допомогою `type`:

```scala
type Person = HasName & HasAge

def printInfo(p: Person): String =
  s"${p.name} is ${p.age} years old"
```

### 📌 Коли варто використовувати alias для Intersection Type

#### ✅ Рекомендується:

- Коли intersection type використовується кілька разів.
- Коли потрібна краща читабельність коду.
- Коли тип є логічним доменним поняттям (наприклад, `Person`, `HttpRequest`, тощо).

#### 🔻 Менш доцільно:

- Якщо використання одноразове.
- Якщо alias приховує важливі деталі типу.

### Переваги

| Переваги                       | Коли не варто                       |
| ------------------------------ | ----------------------------------- |
| ✅ Зручне повторне використання | ❌ Лише одноразове використання      |
| ✅ Краще читабельність          | ❌ Alias приховує важливу інформацію |
| ✅ Відображає доменну модель    | ❌ Надмірна абстракція               |

---

## Потенційні питання для співбесіди

**Q1: Що таке Intersection Type?**\
A1: Це тип, який вимагає, щоб об'єкт мав властивості (методи, поля) з обох типів, які поєднуються оператором `&`.

**Q2: Для чого використовуються Intersection Types?**\
A2: Щоб об'єднати вимоги кількох типів до одного об'єкта без потреби створювати новий тип.

**Q3: У чому різниця між Union та Intersection типами?**\
A3: Union (`A | B`) означає "або A, або B"; Intersection (`A & B`) означає "одночасно A і B".

**Q4: Чи можна передати об'єкт типу A & B туди, де очікується просто A?**\
A4: Так. Оскільки `A & B` має всі члени A, це дозволено.

---

## Короткий висновок

Intersection Types у Scala 3 дозволяють:

- Використовувати об'єкти, що реалізують кілька інтерфейсів без створення нових типів.
- Спрощувати сигнатури функцій із багатьма вимогами до типу.
- Покращувати типобезпеку при роботі з mix-in'ами та DSL-ами.

> Використовуйте Intersection Types, коли потрібно **гарантувати наявність членів кількох типів** в одному об'єкті.

