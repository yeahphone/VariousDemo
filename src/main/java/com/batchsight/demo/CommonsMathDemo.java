package com.batchsight.demo;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.SimpsonIntegrator;
import org.apache.commons.math3.analysis.integration.UnivariateIntegrator;
import org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.fraction.Fraction;
import org.apache.commons.math3.fraction.FractionFormat;
import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.linear.*;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import org.apache.commons.math3.util.FastMath;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Yeahphone
 */
class CommonsMathDemo {

  /**
   * 测试标准差计算
   */
  @Test
  void testStatistics() {

    double[] values = new double[] {65, 51 , 16, 11 , 6519, 191 ,0 , 98, 19854, 1, 32};

    DescriptiveStatistics descriptiveStatistics1 = new DescriptiveStatistics();
    DescriptiveStatistics descriptiveStatistics2 = new DescriptiveStatistics(values);

    for (double v : values) {
      descriptiveStatistics1.addValue(v);
    }

    testResult(descriptiveStatistics1);
    testResult(descriptiveStatistics2);
  }

  private void testResult(DescriptiveStatistics descriptiveStatistics) {

    /* 平均值 2439.818 */
    double mean = descriptiveStatistics.getMean();
    /* 中值 51 */
    double median = descriptiveStatistics.getPercentile(50);
    /* 标准差 6093.055 */
    double standardDeviation = descriptiveStatistics.getStandardDeviation();

    assertEquals(2439.818, mean, 0.001);
    assertEquals(51, median);
    assertEquals(6093.055, standardDeviation, 0.001);
  }

  /**
   * 正态分布
   */
  @Test
  void testNormalDistribution() {
    NormalDistribution normalDistribution = new NormalDistribution(10, 3);
    double randomValue;

    for (int i = 0; i < 100; ++i) {
      randomValue = normalDistribution.sample();
      System.out.println(randomValue);
    }
  }

  /**
   * 求解
   */
  @Test
  void testRootFinding() {
    UnivariateFunction function = v -> Math.pow(v, 2) - 2;
    UnivariateSolver solver = new BracketingNthOrderBrentSolver(1.0e-12, 1.0e-8, 5);

    /* c = ±1.414 */
    double c = solver.solve(100, function, -10.0, 10.0, 0);

    System.out.println("c = " + c);

    assertEquals(0, function.value(c), 1e-7);
  }

  /**
   * 积分
   */
  @Test
  void testCalculatingIntegrals() {
    UnivariateFunction function = v -> v;
    UnivariateIntegrator integrator = new SimpsonIntegrator(1.0e-12, 1.0e-8, 1, 32);
    double i = integrator.integrate(100, function, 0, 10);

    System.out.println(i);
  }

  /**
   * 解线性方程
   */
  @Test
  void testLinearAlgebra() {
    RealMatrix a = new Array2DRowRealMatrix(new double[][] { { 2, 3, -2 }, { -1, 7, 6 }, { 4, -3, -5 } }, false);

    RealVector b = new ArrayRealVector(new double[] { 1, -2, 1 }, false);

    DecompositionSolver solver = new LUDecomposition(a).getSolver();

    RealVector solution = solver.solve(b);

    RealVector axsolution = a.operate(solution);

    System.out.println(a + " x " + solution + " = " + axsolution);

    assertEquals(b, axsolution);
  }

  /**
   * 直线交点
   */
  @Test
  void testGeometry() {
    Line l1 = new Line(new Vector2D(0, 0), new Vector2D(1, 1), 0);
    Line l2 = new Line(new Vector2D(0, 1), new Vector2D(1, 1.5), 0);

    /* 交点 (2,2) */
    Vector2D intersection = l1.intersection(l2);

    System.out.println(intersection);

    assertEquals(new Vector2D(2,2), intersection);
  }

  /**
   * 阶乘
   */
  @Test
  void testFactorial() {

    long factorial = CombinatoricsUtils.factorial(10);
    assertEquals(3628800, factorial);
  }

  /**
   * 分数
   */
  @Test
  void testFraction() {
    Fraction lhs = new Fraction(1, 3);
    Fraction rhs = new Fraction(2, 5);

    /* 1/3 + 2/5 = 11/15 */
    Fraction sum = lhs.add(rhs);

    System.out.println(new FractionFormat().format(sum));

    assertEquals(new Fraction(11,15), sum);
  }

  /**
   * 复数
   */
  @Test
  void testComplex() {
    Complex first = new Complex(1.0, 3.0);
    Complex second = new Complex(2.0, 5.0);

    Complex power = first.pow(second);

    System.out.println(power);
  }

  /**
   * 欧拉公式
   */
  @Test
  void testEulersFormula() {

    Complex e = new Complex(FastMath.E);

    Complex result = e.pow(Complex.I.multiply(FastMath.PI)).add(1);

    System.out.println(result);

    assertEquals(0, result.abs(), 1e-10);
  }

  @Test
  void testRound() {
    double d = Precision.round(1234.56789, 3, BigDecimal.ROUND_HALF_UP);
    System.out.println(d);
  }

  @Test
  void testMax() {
    double a = 1;
    double b = 1.001;
    double c = FastMath.max(a, b);
    assertEquals(b, c, 1e-12);
  }

  @Test
  void testBoolean() {
    System.out.println(true);
  }
}
