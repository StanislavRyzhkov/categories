package company.ryzhkov.instances

import company.ryzhkov.typeclasses.Monoid

class EndoInstance[A] extends Monoid[A => A] {
  override def mempty: A => A = identity[A]

  override def mappend(a1: A => A, a2: A => A): A => A = a1 andThen a2
}
