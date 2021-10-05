package me.nalam.socialscore.commands;

import com.beust.jcommander.Parameters;

@Parameters(commandNames = Quit.COMMAND,
        commandDescription = "Exit from the application")
public class Quit implements Command {
    public static final String COMMAND = "quit";

    @Override
    public String name() {
        return COMMAND;
    }

    @Override
    public void execute() throws Exception {
        final int SUCCESSFUL_TERMINATION_CODE = 0;
        System.out.println("Goodbye");
        System.exit(SUCCESSFUL_TERMINATION_CODE);
    }
}
