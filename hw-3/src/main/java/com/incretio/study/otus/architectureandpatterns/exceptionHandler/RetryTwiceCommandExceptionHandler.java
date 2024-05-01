package com.incretio.study.otus.architectureandpatterns.exceptionHandler;

import com.incretio.study.otus.architectureandpatterns.command.Command;
import com.incretio.study.otus.architectureandpatterns.command.RetryTwiceCommand;
import lombok.extern.slf4j.Slf4j;

import java.util.function.BiFunction;

@Slf4j
public class RetryTwiceCommandExceptionHandler implements BiFunction<Command, Exception, Command> {

    private final Addable<Command> addable;

    public RetryTwiceCommandExceptionHandler(Addable<Command> addable) {
        this.addable = addable;
    }

    @Override
    public Command apply(Command command, Exception exception) {
        addable.add(new RetryTwiceCommand(new RetryTwiceCommand(command)));
        return Command.EMPTY;
    }

}
