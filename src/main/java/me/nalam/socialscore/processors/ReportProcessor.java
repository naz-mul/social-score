package me.nalam.socialscore.processors;

import java.util.List;

public interface ReportProcessor<T> {
    void write(List<T> data, String filePath) throws Exception;
}
