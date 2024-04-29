package com.incretio.study.otus.architectureandpatterns.rotate;

import com.incretio.study.otus.architectureandpatterns.ChangePositionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class RotateTest {

    @Test
    @DisplayName("Для объекта, с текущих углом 5 единиц и скоростью 6 единиц меняет угол на 3")
    public void move_positiveSpeed_success() {
        // given
        Rotable rotable = Mockito.mock(Rotable.class);
        Mockito.when(rotable.getAngle()).thenReturn(new Angle(5));
        Mockito.when(rotable.getAngularSpeed()).thenReturn(new Angle(6));
        Mockito.doNothing().when(rotable).setAngle(Mockito.any());
        // when
        new Rotate(rotable).rotate();
        // then
        Mockito.verify(rotable).setAngle(new Angle(3));
    }

    @Test
    @DisplayName("Для объекта, с текущих углом в 5 единиц и скоростью -6 единиц меняет угол на -1")
    public void move_negativeSpeed_success() {
        // given
        Rotable rotable = Mockito.mock(Rotable.class);
        Mockito.when(rotable.getAngle()).thenReturn(new Angle(5));
        Mockito.when(rotable.getAngularSpeed()).thenReturn(new Angle(-6));
        Mockito.doNothing().when(rotable).setAngle(Mockito.any());
        // when
        new Rotate(rotable).rotate();
        // then
        Mockito.verify(rotable).setAngle(new Angle(-1));
    }

    @Test
    @DisplayName("Попытка повернуть объект, у которого невозможно прочитать текущий угол поворота, приводит к ошибке")
    public void move_getAngleThrowException() {
        // given
        Rotable rotable = Mockito.mock(Rotable.class);
        Mockito.when(rotable.getAngle()).thenThrow(ChangePositionException.class);
        Mockito.when(rotable.getAngularSpeed()).thenReturn(new Angle(6));
        Mockito.doNothing().when(rotable).setAngle(Mockito.any());
        // when/then
        Assertions.assertThrows(ChangePositionException.class, () -> {
            new Rotate(rotable).rotate();
        });
    }

    @Test
    @DisplayName("Попытка повернуть объект, у которого невозможно прочитать значение скорости поворота, приводит к ошибке")
    public void move_getAngularSpeedThrowException() {
        // given
        Rotable rotable = Mockito.mock(Rotable.class);
        Mockito.when(rotable.getAngle()).thenReturn(new Angle(5));
        Mockito.when(rotable.getAngularSpeed()).thenThrow(ChangePositionException.class);
        Mockito.doNothing().when(rotable).setAngle(Mockito.any());
        // when/then
        Assertions.assertThrows(ChangePositionException.class, () -> {
            new Rotate(rotable).rotate();
        });
    }

    @Test
    @DisplayName("Попытка повернуть объект, у которого невозможно изменить угол, приводит к ошибке")
    public void move_setAngleThrowException() {
        // given
        Rotable rotable = Mockito.mock(Rotable.class);
        Mockito.when(rotable.getAngle()).thenReturn(new Angle(5));
        Mockito.when(rotable.getAngularSpeed()).thenReturn(new Angle(6));
        Mockito.doThrow(ChangePositionException.class).when(rotable).setAngle(Mockito.any());
        // when/then
        Assertions.assertThrows(ChangePositionException.class, () -> {
            new Rotate(rotable).rotate();
        });
    }

}
