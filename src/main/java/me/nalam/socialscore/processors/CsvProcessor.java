package me.nalam.socialscore.processors;

import me.nalam.socialscore.constants.Header;
import me.nalam.socialscore.models.SocialScoreReport;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CsvProcessor implements ReportProcessor<SocialScoreReport> {
    private static final Logger log = LoggerFactory.getLogger(CsvProcessor.class);

    public static String[] getHeaders(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(header -> header.name().toLowerCase()).toArray(String[]::new);
    }

    @Override
    public void write(List<SocialScoreReport> data, String filePath) throws Exception {
        FileWriter out = new FileWriter(filePath);
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(getHeaders(Header.class)))) {
            data.forEach(socialScore -> {
                try {
                    printer.printRecord(socialScore.getDomain(), socialScore.getUrls(), socialScore.getSocialScore());
                } catch (IOException e) {
                    log.error("Problem writing to csv file {}", e.getMessage());
                }
            });
        }
    }
}
