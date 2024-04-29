package com.incretio.study.otus.architectureandpatterns;

public class QuadraticEquation {

    public static double[] solve(double a, double b, double c) {
        return solve(a, b, c, 1e-6);
    }

    public static double[] solve(double a, double b, double c, double e) {
        checkValues(a, b, c, e);

        double d = b * b - 4 * a * c;
        if (d < -e) {
            return new double[0];
        }
        double x1;
        double x2;
        if (Math.abs(d) < e) {
            x1 = -b / 2;
            return new double[]{x1};
        }
        x1 = (-b + Math.sqrt(d)) / 2;
        x2 = (-b - Math.sqrt(d)) / 2;
        return new double[]{x1, x2};
    }

    private static void checkValues(double a, double b, double c, double e) {
        if (Double.isNaN(a) || Double.isInfinite(a)) {
            throw new UnsupportedOperationException("a has invalid value.");
        }
        if (Double.isNaN(b) || Double.isInfinite(b)) {
            throw new UnsupportedOperationException("a has invalid value.");
        }
        if (Double.isNaN(c) || Double.isInfinite(c)) {
            throw new UnsupportedOperationException("a has invalid value.");
        }
        if (Double.isNaN(e) || Double.isInfinite(e)) {
            throw new UnsupportedOperationException("e has invalid value.");
        }
        if (Math.abs(a) < e) {
            throw new UnsupportedOperationException("a is zero. It is not quadratic equations.");
        }
    }

}
