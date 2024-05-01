package com.incretio.study.otus.architectureandpatterns.command;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// Реализовать Команду, которая записывает информацию о выброшенном исключении в лог
public class LogExceptionCommand implements Command {

    private final Command command;
    private final Exception exception;

    public LogExceptionCommand(Command command, Exception exception) {
        this.command = command;
        this.exception = exception;
    }

    @Override
    public void execute() {
        String message = String.format("Exception in command = [ %s ]: %s", command.getClass().getName(), exception.getMessage());
        log.error(message, exception);
    }

}
