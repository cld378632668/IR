package Util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.*;
import java.lang.reflect.Array;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ChenLD on 2017/10/23.
 *
 * @author ChenLD
 * @version 1.0
 */
public class FileHelper {


    //
//写操作
//

    /**
     * 内存文件映射，读取大文件：https://blog.csdn.net/zhufenghao/article/details/51192043
     *
     * 多线程分块读取文件后拼接成一个字符串：https://blog.csdn.net/liuxiao723846/article/details/77007790
     */


    /**
     * CSV
     */

    public static void   changeColumn(File file,String header,int initCol, int col ){

        List<String[]> lines = FileReaderHelper.readCSVToListIncludeFirstLine(file.getAbsolutePath(), ',');
        String[] headerLine = lines.get(0);


        for (String s : headerLine
             ) {

            if (s.equals(header))
                break;
            initCol++;
        }
        Collection<String> collection = Arrays.asList(headerLine);
        LinkedList<String> headerList = new LinkedList(collection);


    }



 //文件处理

    /**
     * 过滤出一个文件夹下的满足一定条件的所有文件，按照一定规则重命名【例子】
     * @param dirPath_s
     */
    public static String batchFileRenameExample(String dirPath_s){
        File directory = new File(dirPath_s);
        if(!directory.isAbsolute()){
            try {
                throw new Exception("it is not a directory！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        FileFilter filesFilter = new FileFilter() {
            public boolean accept(File pathname) {
                //if the filename satisfies a certain condition
                return true;
            }
        };
        File[] files = directory.listFiles(filesFilter);
        for (int i = 0; i < files.length; i++) {
            files[i].renameTo(new File(files[i].getParent(),"newName"+i));
        }

        return "Success.";
    }

    public static String batchReplaceSpaceTo_(String dirPath_s){
        File directory = new File(dirPath_s);
        if(!directory.isAbsolute()){
            try {
                throw new Exception("it is not a directory！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        FileFilter filesFilter = new FileFilter() {
            public boolean accept(File pathname) {
                //if the filename satisfies a certain condition
                return true;
            }
        };
        File[] files = directory.listFiles(filesFilter);
        for (int i = 0; i < files.length; i++) {

            System.out.println("改名前"+files[i].getName());
            files[i].renameTo(new File(files[i].getParent(),files[i].getName().replaceAll("\\s","-")));
            System.out.println("改名后"+files[i].getName());

        }

        return "Success.";
    }





    /**
     * 数据去重
     */
    public void removeDuplication(){
        //利用   Set<String> set = new HashSet<String>();
    }

    /**
     * commoms io 按行处理大文件，节约90%以上的内存
     *
     * 测试成功！
     */
    public static void  dealByLineTemplate(){
        File file = new File("src/dataset/CSV-Sample.csv");
       if (file.exists()){
           System.out.println("file exsits!");
       }
        LineIterator it = null;
        try {
            it = FileUtils.lineIterator(file,"UTF-8");
            while(it.hasNext()){
                String line = it.nextLine();
                //do something with line
            }
        } catch (IOException e) {

        }
       finally {
            LineIterator.closeQuietly(it);
        }
    }



    /**
     * 文件流 + java.util.Scanner 按行处理大文件，节约90%以上的内存
     * @param
     */
    public void scanLineTemplate(){
        //取消注释继续写完就行
//        FileInputStream inputStream = null;
//        Scanner sc =null;
//        try{
//            inputStream = new FileInputStream(path);
//            sc = new Scanner(inputStream, "UTF-8");
//
//        }
    }




    /**
     * 以下为BinaryFileHelper
     */
    public static void binaryFileCopy(InputStream input, OutputStream output) throws IOException {

        byte[] buf = new byte[4096];
        int bytesRead = 0;
        while ((bytesRead = input.read(buf)) != -1){
            output.write(buf, 0, bytesRead);
        }
    }

    public static void main(String[] args){

            Instant inst1 =  Instant.now();
            FileReaderHelper.readFileToString("src/dataset/geo.triple");
            Instant inst2 =  Instant.now();
            System.out.println("readFileToString spent " + Duration.between(inst1, inst2).getSeconds() + " seconds");



    }

}
