package com.incretio.study.otus.architectureandpatterns;

import com.incretio.study.otus.architectureandpatterns.command.Command;
import com.incretio.study.otus.architectureandpatterns.exceptionHandler.Addable;
import lombok.Getter;

@Getter
public class SimpleCommandAddable implements Addable<Command> {

    private Command command;

    @Override
    public void add(Command command) {
        this.command = command;
    }

}
