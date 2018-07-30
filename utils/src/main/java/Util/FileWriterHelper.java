package Util;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileWriterHelper {

    /**
     * 使用的BufferedWriter的效率要比FileWriter高很多
     * 使用的BufferedWriter的效率要比FileWriter高很多
     * 使用的BufferedWriter的效率要比FileWriter高很多
     * 使用的BufferedWriter的效率要比FileWriter高很多
     * 使用的BufferedWriter的效率要比FileWriter高很多
     */

    /**
     * write  String Or  Line Just FileUtils Is  Enough !!!
     * write  String Or  Line Just FileUtils Is  Enough !!!
     * write  String Or  Line Just FileUtils Is  Enough !!!
     * write  String Or  Line Just FileUtils Is  Enough !!!
     * write  String Or  Line Just FileUtils Is  Enough !!!
     * write  String Or  Line Just FileUtils Is  Enough !!!

     */



    public static void writeListListToFile(List<LinkedList<String>> lines, String outFilePath, String delimer) throws IOException {
        File file = new File(outFilePath);
        if (!file.exists())
            file.createNewFile();
        StringBuilder content = new StringBuilder();
        for (List<String> line : lines
             ) {

            for (String cell: line
                 ) {
                if (cell.equals(line.get(0)))
                    content.append(cell);
                else
                    content.append(delimer+cell);
            }
            content.append("\r\n");
        }
        FileUtils.write(file,content.toString());
    }



    /**
     * write  String Or  Line Just FileUtils Is  Enough !!!
     * write  String Or  Line Just FileUtils Is  Enough !!!
     * write  String Or  Line Just FileUtils Is  Enough !!!
     * write  String Or  Line Just FileUtils Is  Enough !!!
     * write  String Or  Line Just FileUtils Is  Enough !!!
     * write  String Or  Line Just FileUtils Is  Enough !!!

     */


    /**
     *写字符串到一个文件（覆盖式）
     * should be removed if all its referrences are deleted
     * @param content  例如 "FirstLine\r\nFiled1\tFile2\tFile3\r\n"
     * @param fileName
     * @return
     */
    public static String coverStringToFileNoNewLine(String content, String fileName) throws IOException {

        BufferedWriter fileWriter = null;
        try {
            fileWriter =  new BufferedWriter (new OutputStreamWriter(new FileOutputStream(fileName),"UTF-8"));;
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "OK.";
    }

    /**
     * write  String Or  Line Just FileUtils Is  Enough !!!
     * write  String Or  Line Just FileUtils Is  Enough !!!
     * write  String Or  Line Just FileUtils Is  Enough !!!
     * write  String Or  Line Just FileUtils Is  Enough !!!
     * write  String Or  Line Just FileUtils Is  Enough !!!
     * write  String Or  Line Just FileUtils Is  Enough !!!

     */

    public static String coverStringToFileNewLine(String content, String fileName) throws IOException {

        BufferedWriter fileWriter = null;
        try {
            fileWriter =  new BufferedWriter (new OutputStreamWriter (new FileOutputStream (fileName),"UTF-8"));;
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "OK.";
    }


    /**
     * write  String Or  Line Just FileUtils Is  Enough !!!
     * write  String Or  Line Just FileUtils Is  Enough !!!
     * write  String Or  Line Just FileUtils Is  Enough !!!
     * write  String Or  Line Just FileUtils Is  Enough !!!
     * write  String Or  Line Just FileUtils Is  Enough !!!
     * write  String Or  Line Just FileUtils Is  Enough !!!

     */

    /**
     * 追加字符串到一个文件末尾并换行。
     * 网上另外还能查到3种写法。
     * @param content  例如 "FirstLine\r\nFiled1\tFile2\tFile3\r\n"
     * @param fileName
     * @return
     */
    public static String appendStringNewLine(String content, String fileName) throws IOException {


        BufferedWriter fileWriter = null;
        try {
            fileWriter =  new BufferedWriter (new OutputStreamWriter (new FileOutputStream (fileName,true),"UTF-8"));;//true表示append
            fileWriter.write(content+"\n"); //"\n"做换行处理
            /**
             * 光有write()还不够，如果不close，内容不会flush到文件中。
             */
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "OK.";
    }

    public static String appendStringNewLine(String content, String fileName, String encode) throws IOException {


        BufferedWriter fileWriter = null;
        try {
            fileWriter =  new BufferedWriter (new OutputStreamWriter (new FileOutputStream (fileName,true),encode));;//true表示append
            fileWriter.write(content+"\n"); //"\n"做换行处理
            /**
             * 光有write()还不够，如果不close，内容不会flush到文件中。
             */
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "OK.";
    }

    /**
     * 追加字符串到一个文件末尾并换行。
     * 网上另外还能查到3种写法。
     * @param content  例如 "FirstLine\r\nFiled1\tFile2\tFile3\r\n"
     * @param fileName
     * @return
     */
    public static String appendStringNoNewLine(String content,String fileName) throws IOException {

        BufferedWriter fileWriter = null;
        try {
            fileWriter =  new BufferedWriter (new OutputStreamWriter (new FileOutputStream (fileName,true),"UTF-8"));;//true表示append
            fileWriter.write(content); //"\n"做换行处理
            /**
             * 光有write()还不够，如果不close，内容不会flush到文件中。
             */
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "OK.";
    }

    public static String appendStringNoNewLine(String content, String fileName, String encode) throws IOException {

        BufferedWriter fileWriter = null;
        try {
            fileWriter =  new BufferedWriter (new OutputStreamWriter (new FileOutputStream (fileName,true),encode));;//true表示append
            fileWriter.write(content); //"\n"做换行处理
            /**
             * 光有write()还不够，如果不close，内容不会flush到文件中。
             */
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "OK.";
    }

    /**
     * 为文本的每一行添加一个固定前缀或后缀，输出一个新的文件。
     * @param pathName
     * @param certainCol
     */
    public static void appendASuffixToEachLine(String pathName,String certainCol) throws IOException {
        List<String> ret = new LinkedList<>();
        try {
            FileReader fileReader = new FileReader(pathName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String tmp_s ;
            while((tmp_s = bufferedReader.readLine()) != null){
                ret.add(tmp_s+'"');
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

        File outFile = new File(pathName+"2");
        FileUtils.writeLines(outFile,ret);


//        return ret;/**/
    }

    public static void writeByteArrayToFile(String fileName, byte[] data)
            throws IOException {
        OutputStream output = new FileOutputStream(fileName);
        try {
            output.write(data);
        } finally {
            output.close();
        }
    }
}
