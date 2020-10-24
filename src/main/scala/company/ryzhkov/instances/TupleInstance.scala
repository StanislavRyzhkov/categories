package company.ryzhkov.instances

import company.ryzhkov.typeclasses.Monoid
import company.ryzhkov.typeclasses.Monoid.MonoidOps

class TupleInstance[A: Monoid, B: Monoid] extends Monoid[(A, B)] {
  override def mempty: (A, B) = (Monoid[A].mempty, Monoid[B].mempty)

  override def mappend(a1: (A, B), a2: (A, B)): (A, B) =
    (a1._1.mappend(a2._1), a1._2.mappend(a2._2))
}
