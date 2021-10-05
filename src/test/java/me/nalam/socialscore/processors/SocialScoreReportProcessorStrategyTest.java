package me.nalam.socialscore.processors;

import me.nalam.socialscore.constants.Extension;
import me.nalam.socialscore.entities.SocialScore;
import me.nalam.socialscore.services.SocialScoreService;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

public class SocialScoreReportProcessorStrategyTest {
    private SocialScoreReportProcessorStrategy socialScoreReportProcessorStrategy;
    private List<SocialScore> socialScoreList;
    private SocialScoreService socialScoreService;

    @Before
    public void setUp() throws Exception {
        socialScoreService = PowerMockito.mock(SocialScoreService.class);
        socialScoreReportProcessorStrategy = new SocialScoreReportProcessorStrategy(socialScoreService);
        socialScoreReportProcessorStrategy.addProcessor(Extension.CSV.name().toLowerCase(Locale.ENGLISH),
                new CsvProcessor());

        socialScoreList = new ArrayList<>();
        socialScoreList.add(new SocialScore("http://www.rte.ie/news/politics/2018/1004/1001034-cso/", "rte.ie", 20));
        socialScoreList.add(new SocialScore("https://www.rte.ie/news/ulster/2018/1004/1000952-moanghan-mine/", "rte.ie", 30));
        socialScoreList.add(new SocialScore("https://www.bbc.com/news/world-europe-45746837", "bbc.com", 10));
    }

    @Test
    public void testGenerateReport() throws Exception {
        when(socialScoreService.findAll()).thenReturn(socialScoreList);
        socialScoreReportProcessorStrategy.generateReport("report.csv");
        verify(socialScoreService, times(1)).findAll();
    }

    @Test(expected = RuntimeException.class)
    public void testGenerateReportNoExtension() throws Exception {
        socialScoreReportProcessorStrategy.generateReport("report");
    }
}
