package com.incretio.study.otus.architectureandpatterns.exceptionHandler;

import com.incretio.study.otus.architectureandpatterns.command.Command;
import com.incretio.study.otus.architectureandpatterns.command.LogExceptionCommand;
import lombok.extern.slf4j.Slf4j;

import java.util.function.BiFunction;

@Slf4j
// Реализовать обработчик исключения, который ставит Команду, пишущую в лог в очередь Команд
public class LogExceptionHandler implements BiFunction<Command, Exception, Command> {

    private final Addable<Command> addable;

    public LogExceptionHandler(Addable<Command> addable) {
        this.addable = addable;
    }

    @Override
    public Command apply(Command command, Exception exception) {
        addable.add(new LogExceptionCommand(command, exception));
        return Command.EMPTY;
    }

}
