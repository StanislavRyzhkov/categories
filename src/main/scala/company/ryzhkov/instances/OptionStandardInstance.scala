package company.ryzhkov.instances

import company.ryzhkov.typeclasses.{Applicative, Functor, Monoid}
import company.ryzhkov.typeclasses.Monoid.MonoidOps

class OptionStandardInstance[A: Monoid] extends Monoid[Option[A]] {
  override def mempty: Option[A] = None

  override def mappend(a1: Option[A], a2: Option[A]): Option[A] =
    (a1, a2) match {
      case (None, x)                    => x
      case (x, None)                    => x
      case (Some(value1), Some(value2)) => Some(value1 mappend value2)
    }
}

class OptionFirstInstance[A] extends Monoid[Option[A]] {
  override def mempty: Option[A] = None

  override def mappend(a1: Option[A], a2: Option[A]): Option[A] =
    (a1, a2) match {
      case (None, x) => x
      case (x, _)    => x
    }
}

class OptionFunctorInstance extends Functor[Option] {
  override def fmap[A, B](fa: Option[A])(f: A => B): Option[B] =
    fa match {
      case Some(value) => Some(f(value))
      case None        => None
    }
}

class OptionApplicativeInstance extends Applicative[Option] {

  override def pure[A](a: A): Option[A] = Some(a)

  override def ap[A, B](fa: Option[A])(fab: Option[A => B]): Option[B] =
    (fa, fab) match {
      case (Some(value), Some(f)) => Some(f(value))
      case _                      => None
    }
}
