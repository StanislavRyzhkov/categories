package company.ryzhkov

import org.scalatest.funsuite.AnyFunSuite
import company.ryzhkov.typeclasses.Applicative
import company.ryzhkov.typeclasses.Applicative.ApplicativeOps

class ApplicativeTest extends AnyFunSuite {

  test("option") {
    import company.ryzhkov.implicits.optionApplicativeInstance

    val sv: Option[Int]        = Applicative[Option].pure(5)
    val sf: Option[Int => Int] = Applicative[Option].pure(x => x * x)

    val nv: Option[Int]        = None
    val nf: Option[Int => Int] = None

    assert(sv.ap(sf).contains(25))
    assert(sv.ap(nf).isEmpty)
    assert(nv.ap(sf).isEmpty)
    assert(nv.ap(nf).isEmpty)

    val sum: (Int, Int) => Int = (x, y) => x + y
    val two: Option[Int]       = Applicative[Option].pure(2)
    val three: Option[Int]     = Applicative[Option].pure(3)

    val res = two.fmap2(sum.curried)(three)

    assert(res.contains(5))
  }

  test("listApplicative") {
    import company.ryzhkov.implicits.listApplicativeInstance

    val nonEmptyValueList: List[Int]       = List(3, 5, 7)
    val emptyValueList: List[Int]          = List.empty
    val nonEmptyFuncList: List[Int => Int] = List(_ + 1, x => x * x, _ => 0)
    val emptyFuncList: List[Int => Int]    = List.empty

    val res1 = nonEmptyValueList.ap(nonEmptyFuncList)
    assert(List(4, 9, 0, 6, 25, 0, 8, 49, 0) == res1)

    val res2 = nonEmptyValueList.ap(emptyFuncList)
    assert(res2 == List.empty[Int])

    val res3 = emptyValueList.ap(nonEmptyFuncList)
    assert(res3 == List.empty[Int])

    val res4 = emptyValueList.ap(emptyFuncList)
    assert(res4 == List.empty[Int])
  }

  test("tupleApplicative") {
    import company.ryzhkov.implicits.tupleApplicativeInstance
    import company.ryzhkov.implicits.stringMonoid

    val t1: (String, Int)        = ("Hello", 1)
    val t2: (String, Int => Int) = (", world!", _ * 10)

    val res = t1.ap(t2)

    assert(("Hello, world!", 10) == res)

  }
}
