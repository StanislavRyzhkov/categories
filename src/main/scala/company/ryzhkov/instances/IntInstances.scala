package company.ryzhkov.instances

import company.ryzhkov.typeclasses.Monoid

class IntAdditionInstance extends Monoid[Int] {
  override def mempty: Int = 0
  override def mappend(a1: Int, a2: Int): Int = a1 + a2
}

class IntProductInstance extends Monoid[Int] {
  override def mempty: Int = 1
  override def mappend(a1: Int, a2: Int): Int = a1 * a2
}
