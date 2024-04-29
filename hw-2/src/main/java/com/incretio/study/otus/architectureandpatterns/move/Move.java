package com.incretio.study.otus.architectureandpatterns.move;

public class Move {

    private final Movable movable;

    public Move(Movable movable) {
        this.movable = movable;
    }

    public void move() {
        movable.setPosition(
                Vector.plus(movable.getPosition(), movable.getSpeed()));
    }

}
