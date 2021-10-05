package me.nalam.socialscore;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import me.nalam.socialscore.commands.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class SocialScoreApplication {
    private static final Logger log = LoggerFactory.getLogger(SocialScoreApplication.class);

    public static void main(String... args) {
        Scanner in = new Scanner(System.in);
        do {
            System.out.print("SC>: ");
            String strArgs = in.nextLine();
            String[] arguments = strArgs.split(" ", -1);
            cliRun(arguments);
        } while (true);
    }

    public static void cliRun(String... args) {
        JCommander.Builder builder = JCommander.newBuilder().programName("Social Scoring");
        List<Command> commands = new ArrayList<>();
        commands.add(new Add());
        commands.add(new Export());
        commands.add(new Remove());
        commands.add(new Quit());
        commands.forEach(builder::addCommand);

        JCommander jc = builder.build();
        try {
            jc.parse(args);
            Optional<Command> command = commands.stream()
                    .filter(cmnd -> cmnd.name().equals(jc.getParsedCommand()))
                    .findFirst();

            if (command.isPresent()) {
                command.get().execute();
            } else {
                throw new ParameterException("Command not supported");
            }
        } catch (ParameterException ex) {
            log.warn("Invalid command specified, please retry -> {}", ex.getMessage());
            jc.usage();
        } catch (Exception ex) {
            log.error("There was a problem running your command -> {}", ex.getMessage());
            jc.usage();
        }

    }
}
