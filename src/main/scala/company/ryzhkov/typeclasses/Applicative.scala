package company.ryzhkov.typeclasses

trait Applicative[F[_]] extends Functor[F] {
  def pure[A](a: A): F[A]

  def ap[A, B](fa: F[A])(fab: F[A => B]): F[B]

  override def fmap[A, B](fa: F[A])(f: A => B): F[B] =
    ap(fa)(pure(f))

  def fmap2[A, B, C](fa: F[A])(f: A => B => C)(fb: F[B]): F[C] =
    ap(fb)(fmap(fa)(f))
}

object Applicative {

  def apply[F[_]](implicit ev: Applicative[F]): Applicative[F] = ev

  implicit class ApplicativeOps[F[_]: Applicative, A](fa: F[A]) {
    val param: Applicative[F] = implicitly[Applicative[F]]

    def ap[B](fab: F[A => B]): F[B] = param.ap(fa)(fab)

    def fmap[B](f: A => B): F[B] = param.fmap(fa)(f)

    def fmap2[B, C](f: A => B => C)(fb: F[B]): F[C] = param.fmap2(fa)(f)(fb)
  }
}
