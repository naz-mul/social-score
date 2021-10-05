package me.nalam.socialscore.commands;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import me.nalam.socialscore.constants.Extension;
import me.nalam.socialscore.processors.CsvProcessor;
import me.nalam.socialscore.processors.SocialScoreReportProcessorStrategy;

import java.util.Locale;

@Parameters(commandNames = Export.COMMAND,
        commandDescription = "Export social score data to a file")
public class Export implements Command {
    public static final String COMMAND = "export";

    @Parameter(names = {"--out", "-o"}, required = true,
            description = "Specify the file name to export the report (example: report.csv)")
    private String outputFileName;

    @Override
    public String name() {
        return COMMAND;
    }

    @Override
    public void execute() throws Exception {
        SocialScoreReportProcessorStrategy processor = new SocialScoreReportProcessorStrategy();
        processor.addProcessor(Extension.CSV.name().toLowerCase(Locale.ENGLISH), new CsvProcessor());
        processor.generateReport(this.outputFileName);
    }
}
