package com.incretio.study.otus.architectureandpatterns.command;

import com.incretio.study.otus.architectureandpatterns.exceptionHandler.ExceptionHandler;

public class CommandHandler {

    private final ExceptionHandler exceptionHandler;

    public CommandHandler(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    public void handle(Command command) throws Exception {
        try {
            command.execute();
        } catch (Exception e) {
            exceptionHandler.handle(command, e).execute();
        }
    }

}
