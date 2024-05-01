package com.incretio.study.otus.architectureandpatterns.command;

import java.util.Queue;
import java.util.function.Predicate;

public class QueueCommandHandler {

    private final CommandHandler commandHandler;
    private final Queue<Command> queue;
    private final Predicate<QueueCommandHandler> exitCommandHandlePredicate;

    public QueueCommandHandler(CommandHandler commandHandler, Queue<Command> queue, Predicate<QueueCommandHandler> exitCommandHandlePredicate) {
        this.commandHandler = commandHandler;
        this.queue = queue;
        this.exitCommandHandlePredicate = exitCommandHandlePredicate;
    }

    public void handleCommands() throws Exception {
        while (!exitCommandHandlePredicate.test(this)) {
            Command command = queue.poll();
            if (command == null) {
                return;
            }
            commandHandler.handle(command);
        }
    }

}
