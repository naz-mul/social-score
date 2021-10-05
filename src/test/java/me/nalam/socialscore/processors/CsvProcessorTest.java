package me.nalam.socialscore.processors;

import me.nalam.socialscore.models.SocialScoreReport;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static me.nalam.socialscore.utils.TestUtils.getAbsPath;

public class CsvProcessorTest {

    private List<SocialScoreReport> socialScoreReportList;
    private ReportProcessor<SocialScoreReport> processor;

    @Before
    public void setUp() throws Exception {
        socialScoreReportList = new ArrayList<>();
        socialScoreReportList.add(new SocialScoreReport("rte.ie", 2, 50));
        socialScoreReportList.add(new SocialScoreReport("bbc.com", 1, 10));

        processor = new CsvProcessor();
    }

    @Test
    public void testWrite() throws Exception {
        processor.write(socialScoreReportList, getAbsPath("csvprocessortest1.csv"));
    }

    @Test
    public void testWriteNoData() throws Exception {
        processor.write(new ArrayList<>(), getAbsPath("csvprocessortest2.csv"));
    }

    @Test(expected = FileNotFoundException.class)
    public void testWriteInvalidFileName() throws Exception {
        processor.write(socialScoreReportList, "test/data.csv");
    }
}