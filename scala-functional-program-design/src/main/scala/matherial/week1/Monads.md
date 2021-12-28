## Monads
An observation is that data Structures with map and flatMap seem to be quite common. We have seen quite a few of them. 
In fact, there's a name that describes this class of data structures together with  some algebraic laws that they 
should  have and that's called a monad.

```
trait M[T]:
  def flatMap[U](f: T => M[U]): M[U]
  def unit[T](x: T): M[T]
```
###monad examples
- List is a monad with unit(x) = List(x)
- Set is a monad with unit(x) = Set(x)
- Option is a monad with unit(x) = Some(x)
- Generator is a monad with unit(x) = single(x)


#### flatMap is an operation on each of these types
#### map can be defined for every monad as a combination of flatMap and unit
```
m map f ~ m flatMap (x => unit(f(x)))
m map f ~ m flatMap (f andThen unit)
```
#### Note:
```
extension [A, B, C](f: A => B)
  infix def andThen(g: B => C): A => C =
    x => g(f(x))
```

### Monad laws
To qualify as a monad, a type has to satisfy three laws:

- Associativity of `flatMap`
```
m.flatmap(f).flatMap(g) == m.flatMap(f(_).flatMap(g))
```
- Left unit
```
unit(x).flatMap(f) == f(x)`
```
- Right unit
```
m.flatMap(unit) == m`
```

### Checking monad laws on Option
```
//extension [T](xo: Option[+T])
extension [T](xo: Option[T])
def flatMap[U](f: T => Option[U]): Option[U] = xo match
case Some(x) => f(x)
case None => None
```
Check left unit law
```
Some(x).flatMap(f) == f(x)
=> 
Some(x).flatMap(f)
  == Some(x) match
    case Some(x) => f(x) // the law holds
    case None => None
  == f(x)
```
Check right unit law  `opt.flatMap(Some) == opt`
```
opt.flatMap(Some)
== opt match
  case Some(x) => Some(x)
  case None => None
== opt
```
Check associativity
```
m.flatmap(f).flatMap(g) == m.flatMap(f(_).flatMap(g))
```
```
== (opt match { case Some(x) => f(x) case None => None })
        match { case Some(y) => g(y) case None => None }
== opt match
  case Some(x) =>
    f(x) match { case Some(y) => g(y) case None = None }
  case None =>
    None match { case Some(y) => g(y) case None = None }
== opt match
  case Some(x) =>
    f(x) match { case Some(y) => g(y) case None = None }
  case None => None
== opt match
  case Some(x) => f(x).flatMap(g)
  case None => None
== opt.flatMap(x => f(x).flatMap(g))
== opt.flatMap(f(_).flatMap(g))
```
### Checking monad laws on for expressions
Check associativity
```
    for
      y <- for x <- m; y <- f(x) yield y
      z <- g(y)
    yield z

==  for x <- m; y <- f(x); z <- g(y)
    yield z
```
Check left unit law - `Doesn't have analogy`

Check right unit law
```
    for x <- m yield x

==  m
```
### Checking monad laws on for expressions

Check left unit law - `fails` (left side never throws exception but right side can)
```
  Try(expr).flatMap(f) != f(expr)
```
