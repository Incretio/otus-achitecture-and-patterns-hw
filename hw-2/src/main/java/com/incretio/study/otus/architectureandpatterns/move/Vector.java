package com.incretio.study.otus.architectureandpatterns.move;

public record Vector(int x, int y) {

    public static Vector plus(Vector vector1, Vector vector2) {
        return new Vector(vector1.x + vector2.x, vector1.y + vector2.y);
    }

}
