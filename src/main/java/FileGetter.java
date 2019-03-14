package main.java;

import java.io.File;
import java.util.Objects;

public class FileGetter {
    private static final String FILE_PATH = "E:\\程序设计\\OOP\\Lab\\Reversi\\src\\main\\resources\\Reversi.csv";
    public File readFileFromClassPath() {
        ClassLoader classLoader = getClass().getClassLoader();
//        return new File(Objects.requireNonNull(classLoader.getResource(FILE_PATH)).getFile());
        File file = new File(FILE_PATH);
        return file;
    }
}
