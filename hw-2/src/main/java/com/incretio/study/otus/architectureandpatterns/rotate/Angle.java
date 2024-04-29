package com.incretio.study.otus.architectureandpatterns.rotate;

public record Angle(int direction) {

    private static final int N = 8;

    public static Angle plus(Angle angle1, Angle angle2) {
        return new Angle((angle1.direction + angle2.direction) % N);
    }

}
