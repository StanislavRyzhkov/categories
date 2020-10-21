package company.ryzhkov

import company.ryzhkov.instances._
import company.ryzhkov.typeclasses.Monoid

package object implicits {
  implicit val booleanMonoidAll: Monoid[Boolean] = new BooleanAll

  implicit val intAdditionalMonoid: Monoid[Int] = new IntAdditionInstance

  implicit def optionMonoid[A: Monoid]: Monoid[Option[A]] = new OptionStandardInstance[A]

  implicit def optionFirstMonoid[A]: Monoid[Option[A]] = new OptionFirstInstance[A]

  implicit def listMonoid[A]: Monoid[List[A]] = new ListInstance[A]

  implicit def tupleMonoid[A: Monoid]: Monoid[(A, A)] = new TupleInstance[A]

  implicit def endoMonoid[A]: Monoid[A => A] = new EndoInstance[A]
}
