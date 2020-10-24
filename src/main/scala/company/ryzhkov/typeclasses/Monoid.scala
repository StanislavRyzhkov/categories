package company.ryzhkov.typeclasses

trait Monoid[A] {
  def mempty: A

  def mappend(a1: A, a2: A): A

  def mconcat(list: List[A]): A =
    list.foldRight(mempty)((x, y) => mappend(x, y))

  def |+|(a1: A, a2: A): A = mappend(a1, a2)
}

object Monoid {
  def apply[A](implicit ev: Monoid[A]): Monoid[A] = ev

  implicit class MonoidOps[A: Monoid](a1: A) {
    val param: Monoid[A] = implicitly[Monoid[A]]

    def mappend(a2: A): A = param.mappend(a1, a2)

    def |+|(a2: A): A = param.|+|(a1, a2)
  }
}
