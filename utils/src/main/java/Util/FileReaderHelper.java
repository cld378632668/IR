package Util;

import com.opencsv.CSVReader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.*;
import java.util.*;

public class FileReaderHelper {
    /**
     * 读取CSV全部内容
     * @param filePath
     * @param seperator 分隔符
     *                  注意删除头部是否要删除
     * @return
     *
     * 微软机器配置，读入1G文件，用时19s
     */
    public static List<String[]> readCSVToListWithoutFirstLine(String filePath, char seperator){
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
        return  lines;
    }

    /**
     * 读取CSV全部内容
     * @param filePath
     * @param seperator 分隔符
     *                  注意删除头部是否要删除
     * @return
     *
     * 微软机器配置，读入1G文件，用时19s
     */
    public static List<String[]> readCSVToListIncludeFirstLine(String filePath, char seperator){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {

        }
        CSVReader csvReader = new CSVReader(fileReader,seperator);
        List<String[]> lines = null;
        try {
            lines = csvReader.readAll();
        } catch (IOException e) {

        }
        return  lines;
    }

    /**
     * ！！！可以完全用CSVReader替代，且效率更高！！！
     * 文本文件按行和行中分割符读取存入一个List<String[]>
     *     读取4G文件，用了10G堆内存，100% CPU （E5-2673 v4 @ 2.30GHz 2.29GHz）
     * @param pathName
     * @return
     */
    public static List<String[]> readFileToList1(String pathName, String seperator){
        File file = new File(pathName);
        ArrayList<String[]> outputList = new ArrayList<String[]>();
        try {
            FileReader fileReader = new FileReader(pathName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String tmp_s = null;
            while((tmp_s = bufferedReader.readLine()) != null){
                outputList.add(tmp_s.split(seperator));
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        return outputList;
    }

    /**
     *下面许多的操作FileUtil Just FileUtils Is  Enough !!!
     *下面许多的操作FileUtil Just FileUtils Is  Enough !!!
     *下面许多的操作FileUtil Just FileUtils Is  Enough !!!
     *下面许多的操作FileUtil Just FileUtils Is  Enough !!!
     *下面许多的操作FileUtil Just FileUtils Is  Enough !!!
     *下面许多的操作FileUtil Just FileUtils Is  Enough !!!

     */

    /**
     * 文本文件按行读取存入一个List
     * @param pathName
     * @return
     */
    public static List<String> readFileToList2(String pathName){
        List<String> ret = new LinkedList<>();
        try {
            FileReader fileReader = new FileReader(pathName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String tmp_s ;
            while((tmp_s = bufferedReader.readLine()) != null){
                ret.add(tmp_s);
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        return ret;
    }

    /**
     * 文本文件按行读取后添加\n 拼装成一个完整字符串
     * @param pathName
     * @return
     *
     * 微软机器配置，1G文件，耗时12s
     */
    public static String readFileToString(String pathName){
        File file = new File(pathName);
        StringBuffer outputStringBuffer = new StringBuffer();
        try {
            FileReader fileReader = new FileReader(pathName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String tmp_s = null;
            while((tmp_s = bufferedReader.readLine()) != null){
                outputStringBuffer.append(tmp_s+"\n");
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        return outputStringBuffer.toString();
    }

    public static void readByLinegenerateTrinityFile(String pathName) throws IOException {

        File file = new File(pathName);
        if(file.exists()){
            System.out.println("infile exists!");
        }

        FileUtils.deleteQuietly(new File("src/dataset/janus-edge.csv"));
        FileUtils.deleteQuietly(new File("src/dataset/janus-node.csv"));
        FileUtils.deleteQuietly(new File("src/dataset/neo4j-nodes.csv"));
        FileUtils.deleteQuietly(new File("src/dataset/neo4j-edge.csv"));
        FileUtils.deleteQuietly(new File("src/dataset/trinity-data.csv"));
        FileUtils.write(new File("src/dataset/janus-node.csv"),"category, id, entityContent\nlabel, numeric, string\n",true);

        LineIterator it = null;
        try {
            it = FileUtils.lineIterator(file,"UTF-8");
//            Set<Integer> nodeIDSet = ConcurrentHashMap.<Integer> newKeySet();
            HashSet<Integer> nodeIDSet = new HashSet<Integer>();
            while(it.hasNext()){
                String line = it.nextLine();
                String[] elems = line.split(" ");

                if(!elems[2].contains("wikidata.dbpedia.org"))
                    continue;
                 /*
                NodeID
                 */
                int nodeID1 = Integer.parseInt(elems[0].substring(39,elems[0].length() - 1));
//                String col1 =
//                String col1 = elems[0].substring(38,elems[0].length() - 1).replace("Q","E");
//                String col2 = elems[1].substring(28,elems[1].length() - 1).replace("P","r");
                int nodeID2 = Integer.parseInt(elems[2].substring(39,elems[2].length() - 1));
//                String col3 = elems[2].substring(38,elems[2].length() - 1).replace("Q","E");
                nodeIDSet.add(nodeID1);
                nodeIDSet.add(nodeID2);


                /**Trinity*/
                StringBuilder trinityOutLine = new StringBuilder();
                trinityOutLine.append ("E"+nodeID1+" ");
                trinityOutLine.append("relatedTo " );
                trinityOutLine.append("E"+nodeID2+" ." );
                FileWriterHelper.appendStringNoNewLine(trinityOutLine.toString()+"\r\n","E:\\Code\\utils\\src\\dataset\\trinity-data.ttl");

                /** Janus Edges*/
                FileWriterHelper.appendStringNoNewLine("id,numeric,"+nodeID1+"\n"+"id,numeric,"+nodeID2+"\n"+"out,relation,content,string,"+"relatedTo"+"\n","E:\\Code\\utils\\src\\dataset\\janus-edge.csv");


                /**Neo4j Edges*/
                FileWriterHelper.appendStringNoNewLine(nodeID1+","+nodeID2+","+"relatedTo"+"\r\n","E:\\Code\\utils\\src\\dataset\\neo4j-edge.csv");
            }//End While

            /**Neo4j AND janus NodeID File*/
            File janusNodeCsv = new File("E:\\Code\\utils\\src\\dataset\\janus-node.csv");
            for (Integer i: nodeIDSet) {
                FileWriterHelper.appendStringNoNewLine(i+",E"+i+"\r\n","E:\\Code\\utils\\src\\dataset\\neo4j-nodes.csv");
                FileUtils.write(janusNodeCsv,"nodeLabel,"+i+",E"+i+"\n",true);
            }

        }catch (Exception e){
                e.printStackTrace();
        }
    }

    /**
     *问题模型：
     * 4GB文本文件in.triple每行包含两个顶点，一个边 ： node1String, edgeString，node2String。输出node.csv文件：node1Id，edgeString，node2Id。

     * 算法流程
     * 随机按行读in.triple，对每个nodeString去hash表查，获取或生成ID后把相关数据写入（随机写）node.csv和edge.csv.
     */
    public static void readByLineAddId(){//按行读写内存耗用很低
        //35732007 lines in total, head and tail should be removed
//        String inFileName = "src/dataset/wikidatawiki-20150330-description-rest-notschema.ttl";
        File file = new File("src/dataset/wikidatawiki-20150330-description-rest-notschema.ttl");
        if(file.exists()){
            System.out.println("infile exists!");
        }
        //OutputFile
        File nodeCSV = new File("src/dataset/nodes.csv");
        File edgeCSV = new File("src/dataset/edges.csv");
//        if(nodeCSV.exists()){
//            System.out.println("nodeCSV exists!");
//        }
        Hashtable<String,Integer> nodes = new Hashtable<String,Integer>();
//        int nodesCount = 1;
        int lineCount = 0;

        LineIterator it = null;
        LineIterator nodeCSVIt = null;
        LineIterator edgeCSVIt = null;
        try {
            it = FileUtils.lineIterator(file,"UTF-8");
            it.nextLine();

            while(it.hasNext()){
                lineCount++;
                if(lineCount == 1 ||lineCount ==  35732007)
                    continue;
                String line = it.nextLine();
                String[] elems = line.split(" ");
//                String node = elems[0];

                if(nodes.get(elems[0])== null){
                    nodes.put(elems[0],nodes.size()+1);
                    FileWriterHelper.appendStringNewLine(nodes.size()+","+elems[0],"src/dataset/nodes.csv");
                }
                if(nodes.get(elems[1])== null){
                    nodes.put(elems[1],nodes.size()+1);
                    FileWriterHelper.appendStringNewLine(nodes.size()+","+elems[1],"src/dataset/nodes.csv");
                }

                int node1ID = nodes.get(elems[0]);
                int node2ID = nodes.get(elems[1]);
                FileWriterHelper.appendStringNewLine(node1ID+","+node2ID+","+elems[2],"src/dataset/edges.csv");
            }
        } catch (IOException e) {

        }
        finally {
            LineIterator.closeQuietly(it);
        }
    }

    public static byte[] readFileToByteArray(String fileName)
            throws IOException {
        InputStream input = new FileInputStream(fileName);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            FileHelper.binaryFileCopy(input, output);
            return output.toByteArray();
        } finally {
            input.close();
        }
    }
}
