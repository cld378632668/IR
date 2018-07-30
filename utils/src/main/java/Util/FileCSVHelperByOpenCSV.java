package Util;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.apache.commons.io.FileUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by ChenLD on 2017/10/23.
 *
 * @author ChenLD
 * @version 1.0
 */

public class FileCSVHelperByOpenCSV {


    /**
     * CSVWriter
     * 新版本CSVWriter没有写单个单元格的接口，只有写一行和写全部
     *
     */

    public static List<String[]> addACertainColToLeft(String filePath, char seperator, String certainCol){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {

        }
        CSVReader csvReader = new CSVReader(fileReader,seperator);
        List<String[]> lines = null;
        try {
            lines = csvReader.readAll();
            /**
             * Notice! 删除头部。
             */
            lines.remove(0);
        } catch (IOException e) {

        }

        StringBuilder outContext = new StringBuilder();
        for (String[] line: lines) {
//            outContext.append()
        }



        FileUtils fu = new FileUtils();


        FileWriter outFileWriter = null;
        try {
            outFileWriter = new FileWriter("src/dataset/CSV-Sample.csv");
        }  catch (IOException e) {
            e.printStackTrace();
        }
        CSVWriter csvWriter = new CSVWriter(outFileWriter,seperator);
//        csvWriter.writeNext();


        return  lines;
    }

    public static void main(String[] args) {
<<<<<<< HEAD
=======
        List<String[]> context = FileCSVHelperByOpenCSV.readCSVToList("src/dataset/Most-Recent-Cohorts-Scorecard-Elements.csv",',');
>>>>>>> 3 commit


    }
}
