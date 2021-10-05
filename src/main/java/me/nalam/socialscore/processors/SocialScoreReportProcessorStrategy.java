package me.nalam.socialscore.processors;

import me.nalam.socialscore.entities.SocialScore;
import me.nalam.socialscore.models.SocialScoreReport;
import me.nalam.socialscore.services.SocialScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SocialScoreReportProcessorStrategy {
    private static final Logger log = LoggerFactory.getLogger(SocialScoreReportProcessorStrategy.class);
    private final HashMap<String, ReportProcessor> processors = new HashMap<>();
    private SocialScoreService socialScoreService;

    public SocialScoreReportProcessorStrategy() {
        this.socialScoreService = new SocialScoreService();
    }

    public SocialScoreReportProcessorStrategy(SocialScoreService socialScoreService) {
        this.socialScoreService = socialScoreService;
    }

    public void addProcessor(String extension, ReportProcessor processor) {
        processors.put(extension, processor);
    }

    public void generateReport(String filePath) {
        ReportProcessor processor = getValidProcessor(filePath);
        try {
            List<SocialScore> socialScores = socialScoreService.findAll();
            List<SocialScoreReport> aggregatedSocialScores = new ArrayList<>(
                    socialScores.stream()
                            .collect(Collectors.groupingBy(SocialScore::getTld,
                                    Collectors.collectingAndThen(
                                            Collectors.toList(), score -> new SocialScoreReport(
                                                    score.get(0).getTld(),
                                                    score.size(),
                                                    score.stream().mapToLong(SocialScore::getSocialScore).sum()
                                            )
                                    )))
                            .values()
            );
            if (!aggregatedSocialScores.isEmpty()) {
                log.info("Writing to file {} ", filePath);
                processor.write(aggregatedSocialScores, filePath);
                log.info("Successfully written to the file.");
            } else
                log.warn("There was no data to be written into a file");
        } catch (ClassCastException e) {
            log.error("Invalid processor {}", filePath);
            throw new RuntimeException(e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private int containsExtension(String filePath) {
        final int lastIndexOf = filePath.lastIndexOf('.');
        int INVALID_EXTENSION = -1;
        if (lastIndexOf == INVALID_EXTENSION) {
            log.error("Invalid file extension {} ", filePath);
            throw new RuntimeException("File does not contain extension delimiter");
        }
        log.debug("Contains valid file extension {} ", filePath);
        return lastIndexOf;
    }

    private ReportProcessor getValidProcessor(String filePath) {
        int indexContainingExtensionDelimiter = containsExtension(filePath);
        String extension = filePath.substring(indexContainingExtensionDelimiter + 1);
        ReportProcessor processor = processors.get(extension);
        if (processor == null) {
            log.error("No processor was found for the extension .{} files", extension);
            throw new RuntimeException("No processor was found for the extension ." + extension);
        }
        log.debug("Using {} to process the logs", processor.getClass().getSimpleName());
        return processor;
    }

}
