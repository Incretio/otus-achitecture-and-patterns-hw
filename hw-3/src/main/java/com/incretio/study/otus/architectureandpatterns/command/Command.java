package com.incretio.study.otus.architectureandpatterns.command;

@FunctionalInterface
public interface Command {

    Command EMPTY = () -> {
    };

    void execute() throws Exception;

}
