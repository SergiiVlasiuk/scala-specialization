trait M[T]:
  def flatMap[U](f: T => M[U]): M[U]
  def unit[T](x: T): M[T]


//extension [T](xo: Option[+T])
extension [T](xo: Option[T])
  def flatMap[U](f: T => Option[U]): Option[U] = xo match
    case Some(x) => f(x)
    case None => None

// show left unit law

Some(x).flatMap(f) == f(x)
Some(x) match
  case Some(x) => f(x) // the law holds
  case None => None

// show right unit law
opt flatMap Some == opt
opt match {
  case Some(x) => Some(x)
    ...
} == opt

