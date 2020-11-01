package company.ryzhkov.instances

import company.ryzhkov.typeclasses.{Applicative, Functor, Monoid}
import company.ryzhkov.typeclasses.Monoid.MonoidOps

class TupleInstance[A: Monoid, B: Monoid] extends Monoid[(A, B)] {
  override def mempty: (A, B) = (Monoid[A].mempty, Monoid[B].mempty)

  override def mappend(a1: (A, B), a2: (A, B)): (A, B) =
    (a1._1.mappend(a2._1), a1._2.mappend(a2._2))
}

class TupleFunctorInstance[T] extends Functor[(T, *)] {

  override def fmap[A, B](fa: (T, A))(f: A => B): (T, B) =
    (fa._1, f(fa._2))
}

class TupleApplicativeInstance[T: Monoid] extends Applicative[(T, *)] {

  override def pure[A](a: A): (T, A) = (Monoid[T].mempty, a)

  override def ap[A, B](fa: (T, A))(fab: (T, A => B)): (T, B) =
    (fa._1.mappend(fab._1), fab._2(fa._2))
}
