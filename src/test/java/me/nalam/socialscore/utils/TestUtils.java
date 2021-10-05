package me.nalam.socialscore.utils;

import java.io.File;

public class TestUtils {
    private TestUtils() {
    }

    public static String getAbsPath(String fileName) {
        ClassLoader classLoader = TestUtils.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file.getAbsolutePath();
    }
}
