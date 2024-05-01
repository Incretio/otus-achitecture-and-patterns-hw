package com.incretio.study.otus.architectureandpatterns.exceptionHandler;

import com.incretio.study.otus.architectureandpatterns.command.Command;

public record ExceptionHandlerKey(Class<? extends Command> commandClass, Class<? extends Exception> exceptionClass) {
}
