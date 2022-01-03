/*
// Statefulness And Variables
def cons[T](hd: T, tl: => TailLazyList[T]) = new TailLazyList[T]:
  def head = hd
  private var tlOpt: Option[TailLazyList[T]] = None
  def tail: T = tlOpt match
    case Some(x) => x
    case None => tlOpt = Some(tl); tail
*/

