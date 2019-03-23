package main.java.util;

import main.java.constant.FileConstant;
import com.csvreader.CsvWriter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileUtil {
 //   private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
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
