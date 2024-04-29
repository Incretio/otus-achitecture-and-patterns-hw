package com.incretio.study.otus.architectureandpatterns;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class QuadraticEquationTest {

    @Test
    // Проверяет, что для уравнения x^2+1 = 0 корней нет (возвращается пустой массив)
    public void solve_discriminantLessThenZero() {
        // when
        double[] actual = QuadraticEquation.solve(1, 0, 1);
        // then
        Assertions.assertEquals(0, actual.length);
    }

    @Test
    // Проверяет, что для уравнения x^2-1 = 0 есть два корня кратности 1 (x1=1, x2=-1)
    public void solve_rootsIsAbsOne() {
        // when
        double[] actual = QuadraticEquation.solve(1, 0, -1);
        // then
        Assertions.assertEquals(2, actual.length);
        Assertions.assertEquals(1, actual[0]);
        Assertions.assertEquals(-1, actual[1]);
    }

    @Test
    // Проверяет, что для уравнения x^2+2x+1 = 0 есть один корень кратности 2 (x1= x2 = -1)
    public void solve_discriminantIsZero() {
        // when
        double[] actual = QuadraticEquation.solve(1.0000001, 2, 1);
        // then
        Assertions.assertEquals(1, actual.length);
        Assertions.assertEquals(-1, actual[0]);
    }

    @Test
    // Проверяет, что коэффициент a не может быть равен 0. В этом случае solve выбрасывает исключение.
    public void solve_aIsZero() {
        // when/then
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            QuadraticEquation.solve(0, 2, 1);
        });
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            QuadraticEquation.solve(0.000000001, 2, 1);
        });
    }

    @Test
    // Проверяет корректную обработку если коэффициент a является не числом
    public void solve_aIsNan() {
        // when/then
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            QuadraticEquation.solve(Double.NaN, 2, 1);
        });
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            QuadraticEquation.solve(Double.NEGATIVE_INFINITY, 2, 1);
        });
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            QuadraticEquation.solve(Double.POSITIVE_INFINITY, 2, 1);
        });
    }

    @Test
    // Проверяет корректную обработку если коэффициент b является не числом
    public void solve_bIsNan() {
        // when/then
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            QuadraticEquation.solve(2, Double.NaN, 1);
        });
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            QuadraticEquation.solve(2, Double.NEGATIVE_INFINITY, 1);
        });
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            QuadraticEquation.solve(2, Double.POSITIVE_INFINITY, 1);
        });
    }

    @Test
    // Проверяет корректную обработку если коэффициент c является не числом
    public void solve_cIsNan() {
        // when/then
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            QuadraticEquation.solve(2, 1, Double.NaN);
        });
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            QuadraticEquation.solve(2, 1, Double.NEGATIVE_INFINITY);
        });
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            QuadraticEquation.solve(2, 1, Double.POSITIVE_INFINITY);
        });
    }

    @Test
    // Проверяет корректную обработку если e является не числом
    public void solve_eIsNan() {
        // when/then
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            QuadraticEquation.solve(2, 1, 2, Double.NaN);
        });
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            QuadraticEquation.solve(2, 1, 2, Double.NEGATIVE_INFINITY);
        });
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            QuadraticEquation.solve(2, 1, 2, Double.POSITIVE_INFINITY);
        });
    }

}