package com.incretio.study.otus.architectureandpatterns.rotate;

public class Rotate {

    private final Rotable rotable;

    public Rotate(Rotable rotable) {
        this.rotable = rotable;
    }

    public void rotate() {
        rotable.setAngle(
                Angle.plus(rotable.getAngle(), rotable.getAngularSpeed()));
    }

}
