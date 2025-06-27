# Dependent Function Types

## Вступ

Dependent Function Types — це типи функцій, у яких тип результату **залежить від значення аргументу**. Це один із проявів більш загального поняття залежних типів. У Scala 3 з'явився синтаксис для опису таких функцій.

---

## Dependent Method Types

Залежні типи методів були вже доступні в Scala 2:

```scala
trait Context {
  type Out
  def value: Out
}

def getValue(ctx: Context): ctx.Out = ctx.value
```

Тут результат функції залежить від конкретного значення `ctx`.

---

## Dependent Function Types

У Scala 3 з'явився новий синтаксис для залежних функцій:

```scala
val f: (c: Context) => c.Out = _.value
```

Це означає: "функція приймає `c: Context`, і результат має тип `c.Out`".

До Scala 3 таке було можливо лише через методи. Тепер можна використовувати і як **first-class values**.

---

## Case Study: Numerical Expressions

Реалізуємо типову залежну функцію, яка працює з виразами, що повертають значення залежно від їх типу:

```scala
sealed trait Expr {
  type Out
  def eval: Out
}

case class IntExpr(value: Int) extends Expr {
  type Out = Int
  def eval: Int = value
}

case class BoolExpr(value: Boolean) extends Expr {
  type Out = Boolean
  def eval: Boolean = value
}

val evaluate: (e: Expr) => e.Out = _.eval
```

Тут `evaluate` — залежна функція: тип результату залежить від `e.Out`.

---

## Тести

```scala
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DependentFunctionTypesSpec extends AnyFlatSpec with Matchers {

  "evaluate" should "evaluate IntExpr to Int" in {
    val expr = IntExpr(42)
    val result: expr.Out = evaluate(expr)
    result shouldBe 42
  }

  it should "evaluate BoolExpr to Boolean" in {
    val expr = BoolExpr(true)
    val result: expr.Out = evaluate(expr)
    result shouldBe true
  }

}
```

---

## Питання для співбесіди з відповідями

**1. Що таке dependent function type?**  
Це тип функції, де тип результату залежить від значення аргументу. У Scala 3 підтримується через синтаксис `(x: A) => x.B`.

---

**2. Чим dependent function відрізняється від dependent method?**  
- **Dependent method** — це метод, де тип результату залежить від значення параметру, але не є об'єктом першого класу.
- **Dependent function** — це значення-функція з залежним типом (може передаватися як параметр, зберігатися тощо).

---

**3. Яка користь від dependent function types у Scala 3?**  
- Дають змогу точно описувати API.
- Підвищують типобезпечність.
- Полегшують створення DSL та бібліотек типу Shapeless або відображення типів у значення.

---

**4. Чи можуть dependent functions бути викликані динамічно?**  
Так, але варто памʼятати, що типовий результат відомий лише після надання аргументу.

---

**5. Чи є обмеження на використання dependent function types?**  
Так, вони працюють тільки з повністю конкретизованими аргументами. Тип результату не може бути виведений без них.