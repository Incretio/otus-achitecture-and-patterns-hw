package com.incretio.study.otus.architectureandpatterns.move;

import com.incretio.study.otus.architectureandpatterns.ChangePositionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MoveTest {

    @Test
    @DisplayName("Для объекта, находящегося в точке (12, 5) и движущегося со скоростью (-7, 3) движение меняет положение объекта на (5, 8)")
    public void move_success() {
        // given
        Movable movable = Mockito.mock(Movable.class);
        Mockito.when(movable.getPosition()).thenReturn(new Vector(12, 5));
        Mockito.when(movable.getSpeed()).thenReturn(new Vector(-7, 3));
        Mockito.doNothing().when(movable).setPosition(Mockito.any());
        // when
        new Move(movable).move();
        // then
        Mockito.verify(movable).setPosition(new Vector(5, 8));
    }

    @Test
    @DisplayName("Попытка сдвинуть объект, у которого невозможно прочитать положение в пространстве, приводит к ошибке")
    public void move_getPositionThrowException() {
        // given
        Movable movable = Mockito.mock(Movable.class);
        Mockito.when(movable.getPosition()).thenThrow(ChangePositionException.class);
        Mockito.when(movable.getSpeed()).thenReturn(new Vector(-7, 3));
        Mockito.doNothing().when(movable).setPosition(Mockito.any());
        // when/then
        Assertions.assertThrows(ChangePositionException.class, () -> {
            new Move(movable).move();
        });
    }

    @Test
    @DisplayName("Попытка сдвинуть объект, у которого невозможно прочитать значение мгновенной скорости, приводит к ошибке")
    public void move_getSpeedThrowException() {
        // given
        Movable movable = Mockito.mock(Movable.class);
        Mockito.when(movable.getPosition()).thenReturn(new Vector(12, 5));
        Mockito.when(movable.getSpeed()).thenThrow(ChangePositionException.class);
        Mockito.doNothing().when(movable).setPosition(Mockito.any());
        // when/then
        Assertions.assertThrows(ChangePositionException.class, () -> {
            new Move(movable).move();
        });
    }

    @Test
    @DisplayName("Попытка сдвинуть объект, у которого невозможно изменить положение в пространстве, приводит к ошибке")
    public void move_setPositionThrowException() {
        // given
        Movable movable = Mockito.mock(Movable.class);
        Mockito.when(movable.getPosition()).thenReturn(new Vector(12, 5));
        Mockito.when(movable.getSpeed()).thenReturn(new Vector(-7, 3));
        Mockito.doThrow(ChangePositionException.class).when(movable).setPosition(Mockito.any());
        // when/then
        Assertions.assertThrows(ChangePositionException.class, () -> {
            new Move(movable).move();
        });
    }

}
