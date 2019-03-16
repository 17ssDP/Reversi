package main.java.util;

import main.java.constant.FileConstant;
import com.csvreader.CsvWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public class FileUtil {
//    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private static final String FILE_PATH = "D:\\ProfessionalCourse\\OOP\\Lab\\Reversi\\src\\main\\resources\\Reversi.csv";
    public File readFileFromClassPath() {
        ClassLoader classLoader = getClass().getClassLoader();
//        return new File(Objects.requireNonNull(classLoader.getResource(FILE_PATH)).getFile());
        File file = new File(FILE_PATH);
        return file;
    }
    public static boolean createFile(String destFileName) {
        File file = new File(destFileName);
        if(file.exists())
            return true;
        else {
            return false;
        }
    }

    public static void write(String[] array, String dataFilePath) {
        if (createFile(dataFilePath)) {
            BufferedWriter bw;
            try {
                bw = new BufferedWriter(new FileWriter(dataFilePath, true));
                CsvWriter out = new CsvWriter(bw, FileConstant.CSV_SEPARATOR);
                out.writeRecord(array);
                out.flush();
                bw.flush();
                out.close();
                bw.close();
            } catch (IOException e) {
//                logger.info(e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
