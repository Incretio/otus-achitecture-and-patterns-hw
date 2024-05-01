package com.incretio.study.otus.architectureandpatterns.exceptionHandler;

import com.incretio.study.otus.architectureandpatterns.command.Command;
import com.incretio.study.otus.architectureandpatterns.HasNoHandlerException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

public class ExceptionHandler {

    private final Map<ExceptionHandlerKey, BiFunction<Command, Exception, Command>> handlerMap = new HashMap<>();

    public Command handle(Command command, Exception exception) {
        Class<? extends Command> commandClass = command.getClass();
        Class<? extends Exception> exceptionClass = exception.getClass();

        ExceptionHandlerKey exceptionHandlerKey = new ExceptionHandlerKey(commandClass, exceptionClass);
        // Использование обработчика для конкретных типов
        BiFunction<Command, Exception, Command> handler = handlerMap.get(exceptionHandlerKey);
        if (handler != null) {
            return handler.apply(command, exception);
        }

        // Использование ближайшего обработка
        for (Map.Entry<ExceptionHandlerKey, BiFunction<Command, Exception, Command>> entry : handlerMap.entrySet()) {
            Class<? extends Command> aClass = entry.getKey().commandClass();
            Class<? extends Exception> eClass = entry.getKey().exceptionClass();
            if (Objects.equals(commandClass.getName(), aClass.getName()) && eClass.isInstance(exception)) {
                return entry.getValue().apply(command, exception);
            }
        }
        String message = String.format("Has no handler for commandClass = [ %s ] and exceptionClass = [ %s ]", commandClass.getName(), exceptionClass.getName());
        throw new HasNoHandlerException(message);
    }

    public void registerHandler(Class<? extends Command> commandClass, Class<? extends Exception> exceptionClass, BiFunction<Command, Exception, Command> handler) {
        ExceptionHandlerKey exceptionHandlerKey = new ExceptionHandlerKey(commandClass, exceptionClass);
        handlerMap.put(exceptionHandlerKey, handler);
    }

}
