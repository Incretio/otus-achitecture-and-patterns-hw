package com.incretio.study.otus.architectureandpatterns.command;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RetryTwiceCommand implements Command {

    private final Command command;

    public RetryTwiceCommand(Command command) {
        this.command = command;
    }

    @Override
    public void execute() throws Exception {
        command.execute();
    }

}
