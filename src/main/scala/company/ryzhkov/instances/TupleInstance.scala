package company.ryzhkov.instances

import company.ryzhkov.typeclasses.Monoid
import company.ryzhkov.typeclasses.Monoid.MonoidOps

class TupleInstance[A: Monoid] extends Monoid[(A, A)] {
  override def mempty: (A, A) = (Monoid[A].mempty, Monoid[A].mempty)

  override def mappend(a1: (A, A), a2: (A, A)): (A, A) =
    (a1._1.mappend(a2._1), a1._2.mappend(a2._2))
}
