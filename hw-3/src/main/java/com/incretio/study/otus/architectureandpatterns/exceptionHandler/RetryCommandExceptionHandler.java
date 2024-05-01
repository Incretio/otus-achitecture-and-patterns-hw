package com.incretio.study.otus.architectureandpatterns.exceptionHandler;

import com.incretio.study.otus.architectureandpatterns.command.Command;
import com.incretio.study.otus.architectureandpatterns.command.RetryCommand;
import lombok.extern.slf4j.Slf4j;

import java.util.function.BiFunction;

@Slf4j
// Реализовать обработчик исключения, который ставит в очередь Команду - повторитель команды, выбросившей исключение
public class RetryCommandExceptionHandler implements BiFunction<Command, Exception, Command> {

    private final Addable<Command> addable;

    public RetryCommandExceptionHandler(Addable<Command> addable) {
        this.addable = addable;
    }

    @Override
    public Command apply(Command command, Exception exception) {
        addable.add(new RetryCommand(command));
        return Command.EMPTY;
    }

}
