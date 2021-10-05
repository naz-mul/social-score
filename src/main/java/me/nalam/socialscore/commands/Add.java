package me.nalam.socialscore.commands;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import me.nalam.socialscore.entities.SocialScore;
import me.nalam.socialscore.services.SocialScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

@Parameters(commandNames = Add.COMMAND,
        commandDescription = "Add a URL with an associated social score")
public class Add implements Command {
    public static final String COMMAND = "add";
    private static final Logger log = LoggerFactory.getLogger(Add.class);
    private final SocialScoreService socialScoreService = new SocialScoreService();
    @Parameter(names = {"--url", "-u"}, required = true,
            description = "Specify the full URL")
    private String url;
    @Parameter(names = {"--score", "-s"}, required = true,
            description = "The social score value for the specified URL")
    private long socialScore;

    @Override
    public String name() {
        return COMMAND;
    }

    @Override
    public void execute() throws Exception {
        log.info("Adding a new social score data...");
        SocialScore socialScore = new SocialScore(this.url, getTldName(this.url), this.socialScore);
        socialScoreService.persist(socialScore);
        log.info("A new social score was saved");
    }

    private String getTldName(String argUrl) throws Exception {
        URL url = null;
        String tldString = null;
        try {
            url = new URL(argUrl);
            String[] domainNameParts = url.getHost().split("\\.");
            tldString = domainNameParts[domainNameParts.length - 2] + "." + domainNameParts[domainNameParts.length - 1];
        } catch (MalformedURLException ex) {
            throw new Exception("Invalid url specified -> ".concat(ex.getMessage()));
        }
        return tldString;
    }
}
