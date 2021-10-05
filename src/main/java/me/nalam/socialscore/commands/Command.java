package me.nalam.socialscore.commands;

public interface Command {
    String name();

    void execute() throws Exception;
}
