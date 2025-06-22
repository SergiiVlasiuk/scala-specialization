# Creating Modules from Traits у Scala 3

## Що таке модулі на базі trait?

- Trait — абстрактний тип із методами.
- Модулі у вигляді traits дають контракт для функціоналу.
- Легко комбінуються і замінюються.

## Чому це корисно?

- Слабке зв’язування компонентів.
- Заміна реалізацій (тестування, розвиток).
- Зрозуміла структура коду.
- Підтримка множинного наслідування.

## Приклад
```scala
trait Logger:
  def log(msg: String): Unit

class ConsoleLogger extends Logger:
  def log(msg: String): Unit = println(s"[Console] $msg")
```
## Stackable traits
```scala
trait TimestampLogger extends Logger:
  abstract override def log(msg: String): Unit =
    super.log(s"${java.time.Instant.now()} - $msg")

trait ShortLogger extends Logger:
  val maxLength = 15
  abstract override def log(msg: String): Unit =
    super.log(
      if msg.length <= maxLength then msg else msg.take(maxLength - 3) + "..."
    )
```
## Переваги

- Гнучкість і розширюваність.
- Чисті контракти.
- Динамічне комбінування поведінки.
- Відмінний патерн для масштабних проєктів.
