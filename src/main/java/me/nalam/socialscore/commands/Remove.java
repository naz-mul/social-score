package me.nalam.socialscore.commands;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import me.nalam.socialscore.services.SocialScoreService;

@Parameters(commandNames = Remove.COMMAND,
        commandDescription = "Remove a URL from the record")
public class Remove implements Command {
    public static final String COMMAND = "remove";
    private final SocialScoreService socialScoreService = new SocialScoreService();
    @Parameter(names = {"--url", "-u"}, required = true,
            description = "Specify the full URL")
    private String url;

    @Override
    public String name() {
        return COMMAND;
    }

    @Override
    public void execute() throws Exception {
        socialScoreService.deleteByUrl(this.url);
    }
}
