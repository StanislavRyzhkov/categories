package company.ryzhkov.instances

import company.ryzhkov.typeclasses.Monoid

class ListInstance[A] extends Monoid[List[A]] {
  override def mempty: List[A] = Nil

  override def mappend(a1: List[A], a2: List[A]): List[A] = a1 ::: a2
}
