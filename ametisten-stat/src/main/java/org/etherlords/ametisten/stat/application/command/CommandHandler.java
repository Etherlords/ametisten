package org.etherlords.ametisten.stat.application.command;

public interface CommandHandler<T extends Command> {
    
    void handleCommand(T command);
    
}
