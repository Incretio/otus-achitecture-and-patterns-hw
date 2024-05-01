package com.incretio.study.otus.architectureandpatterns;

import com.incretio.study.otus.architectureandpatterns.command.*;
import com.incretio.study.otus.architectureandpatterns.exceptionHandler.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@Slf4j
class CommandHandlerTest {

    @Test
    void test_logExceptionHandler() throws Exception {
        // given
        Command command = Mockito.mock(Command.class);
        Mockito.doThrow(RuntimeException.class).when(command).execute();

        Addable<Command> addable = Mockito.mock(Addable.class);
        LogExceptionHandler logExceptionHandler = Mockito.spy(new LogExceptionHandler(addable));

        ExceptionHandler exceptionHandler = Mockito.spy(new ExceptionHandler());
        exceptionHandler.registerHandler(command.getClass(), RuntimeException.class, logExceptionHandler);
        // when
        CommandHandler commandHandler = new CommandHandler(exceptionHandler);
        commandHandler.handle(command);
        // then
        Mockito.verify(command, Mockito.times(1)).execute();
        Mockito.verify(exceptionHandler, Mockito.times(1)).handle(Mockito.any(), Mockito.any());
        Mockito.verify(logExceptionHandler, Mockito.times(1)).apply(Mockito.any(), Mockito.any());
        Mockito.verify(addable, Mockito.times(1)).add(Mockito.any(LogExceptionCommand.class));
    }

    @Test
    void test_retryExceptionHandler() throws Exception {
        // given
        Command command = Mockito.mock(Command.class);
        Mockito.doThrow(RuntimeException.class).when(command).execute();

        Addable<Command> addable = Mockito.mock(Addable.class);
        RetryCommandExceptionHandler retryCommandExceptionHandler = Mockito.spy(new RetryCommandExceptionHandler(addable));

        ExceptionHandler exceptionHandler = Mockito.spy(new ExceptionHandler());
        exceptionHandler.registerHandler(command.getClass(), RuntimeException.class, retryCommandExceptionHandler);
        // when
        CommandHandler commandHandler = new CommandHandler(exceptionHandler);
        commandHandler.handle(command);
        // then
        Mockito.verify(command, Mockito.times(1)).execute();
        Mockito.verify(exceptionHandler, Mockito.times(1)).handle(Mockito.any(), Mockito.any());
        Mockito.verify(retryCommandExceptionHandler, Mockito.times(1)).apply(Mockito.any(), Mockito.any());
        Mockito.verify(addable, Mockito.times(1)).add(Mockito.any(RetryCommand.class));
    }

    // С помощью Команд из пункта 4 и пункта 6 реализовать следующую обработку исключений:
    // при первом выбросе исключения повторить команду, при повторном выбросе исключения записать информацию в лог
    @Test
    void test_logAfterRetryExceptionHandler() throws Exception {
        // given
        Command command = Mockito.mock(Command.class);
        Mockito.doThrow(RuntimeException.class).when(command).execute();

        SimpleCommandAddable addable = Mockito.spy(new SimpleCommandAddable());
        RetryCommandExceptionHandler retryCommandExceptionHandler = Mockito.spy(new RetryCommandExceptionHandler(addable));
        LogExceptionHandler logExceptionHandler = Mockito.spy(new LogExceptionHandler(addable));

        ExceptionHandler exceptionHandler = Mockito.spy(new ExceptionHandler());
        exceptionHandler.registerHandler(command.getClass(), RuntimeException.class, retryCommandExceptionHandler);
        exceptionHandler.registerHandler(RetryCommand.class, Exception.class, logExceptionHandler);
        // when
        CommandHandler commandHandler = new CommandHandler(exceptionHandler);
        commandHandler.handle(command);
        // then
        Mockito.verify(command, Mockito.times(1)).execute();
        Mockito.verify(exceptionHandler, Mockito.times(1)).handle(Mockito.any(), Mockito.any());
        Mockito.verify(retryCommandExceptionHandler, Mockito.times(1)).apply(Mockito.any(), Mockito.any());
        Mockito.verify(addable, Mockito.times(1)).add(Mockito.any(RetryCommand.class));
        // when
        Command nextCommand = addable.getCommand();
        commandHandler.handle(nextCommand);
        // then
        Mockito.verify(command, Mockito.times(2)).execute();
        Mockito.verify(exceptionHandler, Mockito.times(2)).handle(Mockito.any(), Mockito.any());
        Mockito.verify(logExceptionHandler, Mockito.times(1)).apply(Mockito.any(), Mockito.any());
        Mockito.verify(addable, Mockito.times(1)).add(Mockito.any(LogExceptionCommand.class));
    }

    // Реализовать стратегию обработки исключения - повторить два раза, потом записать в лог
    @Test
    void test_logAfterRetryTwiceExceptionHandler() throws Exception {
        // given
        Command command = Mockito.mock(Command.class);
        Mockito.doThrow(RuntimeException.class).when(command).execute();

        SimpleCommandAddable addable = Mockito.spy(new SimpleCommandAddable());
        RetryCommandExceptionHandler retryCommandExceptionHandler = Mockito.spy(new RetryCommandExceptionHandler(addable));
        RetryTwiceCommandExceptionHandler retryTwiceCommandExceptionHandler = Mockito.spy(new RetryTwiceCommandExceptionHandler(addable));
        LogExceptionHandler logExceptionHandler = Mockito.spy(new LogExceptionHandler(addable));

        ExceptionHandler exceptionHandler = Mockito.spy(new ExceptionHandler());
        exceptionHandler.registerHandler(command.getClass(), RuntimeException.class, retryCommandExceptionHandler);
        exceptionHandler.registerHandler(RetryCommand.class, Exception.class, retryTwiceCommandExceptionHandler);
        exceptionHandler.registerHandler(RetryTwiceCommand.class, Exception.class, logExceptionHandler);
        // when
        CommandHandler commandHandler = new CommandHandler(exceptionHandler);
        commandHandler.handle(command);
        // then
        Mockito.verify(command, Mockito.times(1)).execute();
        Mockito.verify(exceptionHandler, Mockito.times(1)).handle(Mockito.any(), Mockito.any());
        Mockito.verify(retryCommandExceptionHandler, Mockito.times(1)).apply(Mockito.any(), Mockito.any());
        Mockito.verify(addable, Mockito.times(1)).add(Mockito.any(RetryCommand.class));
        // when
        Command nextCommand = addable.getCommand();
        commandHandler.handle(nextCommand);
        // then
        Mockito.verify(command, Mockito.times(2)).execute();
        Mockito.verify(exceptionHandler, Mockito.times(2)).handle(Mockito.any(), Mockito.any());
        Mockito.verify(retryTwiceCommandExceptionHandler, Mockito.times(1)).apply(Mockito.any(), Mockito.any());
        Mockito.verify(addable, Mockito.times(1)).add(Mockito.any(RetryTwiceCommand.class));

        // when
        nextCommand = addable.getCommand();
        commandHandler.handle(nextCommand);
        // then
        Mockito.verify(command, Mockito.times(3)).execute();
        Mockito.verify(exceptionHandler, Mockito.times(3)).handle(Mockito.any(), Mockito.any());
        Mockito.verify(logExceptionHandler, Mockito.times(1)).apply(Mockito.any(), Mockito.any());
        Mockito.verify(addable, Mockito.times(1)).add(Mockito.any(LogExceptionCommand.class));
    }

}