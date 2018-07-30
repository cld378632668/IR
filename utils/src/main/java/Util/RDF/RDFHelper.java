package Util.RDF;

import Util.*;
import org.apache.commons.io.FileUtils;

import java.io.File;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class RDFHelper {

    class BaseClass{
        String type;
        String subject;
        TreeMap<String,String> properties = new TreeMap<String,String>();

        public TreeMap<String, String> getProperties() {
            return properties;
        }

        public void setProperties(TreeMap<String, String> properties) {
            this.properties = properties;
        }

        BaseClass(String type,String subject){
            this.subject = subject;
            this.type = type;
            properties = new TreeMap<String,String>();
        }
        @Override
        public String toString() {
            StringBuilder s = new StringBuilder();
            return super.toString();
        }
        
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
    }

    public void  nTurplesToNeo4jCSV2(String ttlpath) throws IOException {
        Instant inst1 = Instant.now();
        String parentDirPath = "D:\\GeoData";
        FileUtils.cleanDirectory(new File(parentDirPath));

        /**
         * 读取.tll
         */

//        ttlpath ="D:\\Code\\utils\\src\\dataset\\yum-without-english-comma.triple";
        List<String[]> triples = FileReaderHelper.readCSVToListIncludeFirstLine(ttlpath,' ');



        TreeMap<String,BaseClass> baseClassMap = new TreeMap<>();
        HashMap<String,Integer> subToHashMap = new HashMap<>();
//        List<String[]> edges = new LinkedList<>();
        StringBuilder edgesString = new StringBuilder();
        /**
         * 将所有nodes信息写入HashMap<String,BaseClass> baseClassMap = new HashMap<>();
         */
        inst1 = Instant.now();
        for (String[] triple: triples
                ) {
            if (triple[1].matches(".*rdf.*syntax-ns.*type.*")){
                String type = RegexHelper.getMatchList(triple[2],"(?<=/)[^/]*?(?=>)").get(0);
                BaseClass baseClass = new BaseClass(type,triple[0]);
                if (baseClassMap.get(triple[0]) == null)
                    baseClassMap.put(triple[0],baseClass);
                if (subToHashMap.get(triple[0]) == null)
                    subToHashMap.put(triple[0],subToHashMap.size()+1);
            }else if (triple[1].matches(".*type.*object.*type.*")){
                BaseClass baseClass = baseClassMap.get(triple[0]);
                TreeMap proMap = baseClass.getProperties();
                String type = RegexHelper.getMatchList(triple[2],"(?<=/)[^/]*?(?=>)").get(0);
                if (proMap.get("type_object_type") != null){
                    proMap.put("type_object_type",proMap.get("type_object_type")+";"+type);
                }else{
                    proMap.put("type_object_type",type);
                }
            }else if (subToHashMap.keySet().contains(triple[0])&&subToHashMap.keySet().contains(triple[2])){//describle relation
                String relation = RegexHelper.getMatchList(triple[1],"(?<=/)[^/]*?(?=>)").get(0);
                edgesString.append(subToHashMap.get(triple[0])).append(",").append(relation).append(",").append(subToHashMap.get(triple[2])).append("\r\n");
            }
            else{

                String proName = RegexHelper.getMatchList(triple[1],"(?<=/)[^/]*?(?=>)").get(0);
                String proValue = triple[2];
                if(triple[2].contains("<http://www.w3.org/2001/XMLSchema#")){
                    String[] s = triple[2].split("\"");
                    proValue = s[0];
//                            RegexHelper.getMatchList(triple[2],"(?<=\").*?(?=\")").sp
//                    proValue =  RegexHelper.getMatchList(triple[2],"(?<=\").*?(?=\")").get(0); //"25"^^
                    proName = proName+":double";
                }

                BaseClass baseClass = baseClassMap.get(triple[0]);
                TreeMap proMap = baseClass.properties;
                if (proMap.get(proName) != null){
                    proMap.put(proName,proMap.get(proName)+";"+proValue);
                }else{
                    proMap.put(proName,proValue);
                }
            }
        }


        System.out.println("subToHashMap.keySet().size() = " + subToHashMap.keySet().size());
        Collection<Integer> hashes = subToHashMap.values();
//        List<Integer> hashList = new ArrayList<Integer>(hashes);
        Set<Integer> hashesSet = new HashSet<Integer>(hashes);
        System.out.println("subToHashMap.distinctValues.size() = " + hashesSet.size());


        /**
         * 写edge.csv
         */
        String edgeFilePath = parentDirPath+"\\edge.csv";
        File edgeFile = new File(edgeFilePath);
        if (!edgeFile.exists())
            edgeFile.createNewFile();
        FileWriterHelper.coverStringToFileNoNewLine(":START_ID,:TYPE,:END_ID\r\n",edgeFilePath);
        FileWriterHelper.appendStringNoNewLine(edgesString.toString(),edgeFilePath);
        /**
         * 排序
         */
        inst1 = Instant.now();
        Collection<BaseClass> valueCollection = baseClassMap.values();
        List<BaseClass> nodesList = new ArrayList<BaseClass>(valueCollection);
        Collections.sort(nodesList, new Comparator<BaseClass>() {
            @Override
            public int compare(BaseClass o1, BaseClass o2) {
                return o2.getType().compareTo(o1.getType());
            }
        });

        /**
         * 写所有顶点信息写入二维表后写文件
         */
        inst1 = Instant.now();
        String typeMark = "";
        StringBuilder nodeLines = new StringBuilder();
        File nodeFile;
        String nodeFilePath = "wrong.csv";
        StringBuilder linesString = new StringBuilder();

        List<LinkedList<String>> lines = new LinkedList<LinkedList<String>>();
        TreeSet<String> keyUnionSet = new TreeSet<>();
        List<BaseClass> nodesInOneFile =  new LinkedList<BaseClass>();

        nodesList.add(new BaseClass("-1","-1"));
        for (BaseClass baseClass : nodesList
                ) {
            String subject = baseClass.subject;
            TreeMap<String,String> proMap = baseClass.getProperties();


            if (!baseClass.getType().equals(typeMark)){
                //写前一个文件的数据
                if (!typeMark.equals(""))//不是第一次
                {

                    lines.clear();
                    LinkedList<String> headerline = new LinkedList<String>();
                    //写头部进List<List>
                    headerline.add(typeMark+"Id:ID");
                    headerline.add("name");
                    for (String s : keyUnionSet
                         ) {

                        if (s.contains(":double"))
                            headerline.add(s+"[]");
                        else
                            headerline.add(s+":string[]");
                    }
                    headerline.add(":LABEL");
                    lines.add(headerline);



                    //写所有顶点信息进List<List>
                    for (BaseClass nodeData: nodesInOneFile
                         ) {
                        LinkedList<String> line = new LinkedList<String>();

                        line.add(subToHashMap.get(nodeData.subject).toString());
                        line.add(nodeData.subject);
                        for (String key: keyUnionSet
                             ) {
                            String value = nodeData.properties.get(key);
                            if (value == null)
                                line.add("-1");
                            else{
                                if(!key.contains(":"))
//                                    line.add("\""+value+"\"");
                                    line.add(value.replaceAll("\"",""));
                                else
                                    line.add(value);
                            }

                        }
                        line.add(typeMark);

                        if (line.size() == (keyUnionSet.size() + 3))
                            lines.add(line);
                    }
                    //写List<List>进File
                    FileWriterHelper.writeListListToFile(lines,parentDirPath+"\\node_"+typeMark+".csv",",");

                }

                typeMark = baseClass.getType();
            }

            Set<String> keySet = proMap.keySet();
            keyUnionSet.addAll(keySet);
            nodesInOneFile.add(baseClass);
        }

        Instant inst2 = Instant.now();
        System.out.println("This program spent " + Duration.between(inst1, inst2).getSeconds() + " seconds");

    }


//    public void  nTurplesToNeo4jCSV2Wrong(String ttlpath) throws IOException {
//
//        String parentDirPath = "D:\\GeoData";
//        FileUtils.cleanDirectory(new File(parentDirPath));
//
//        /**
//         * 读取.tll
//         */
//        Instant inst1 = Instant.now();
//        ttlpath ="D:\\WeiyunTongbuPan\\Micro\\DataSet\\geo\\data\\geo@@23f75c2c-f6fb-4741-9cf8-1a79e829ee52@@geo_1@@carina-noEnglishComma.ttl";
//        List<String[]> triples = FileHelper.readCSVToListIncludeFirstLine(ttlpath,' ');
//        Instant inst2 = Instant.now();
//        System.out.println("读取.tll spent " + Duration.between(inst1, inst2).getSeconds() + " seconds");
//
//
//
//        TreeMap<String,BaseClass> baseClassMap = new TreeMap<>();
//        HashMap<String,Integer> subToHashMap = new HashMap<>();
////        List<String[]> edges = new LinkedList<>();
//        StringBuilder edgesString = new StringBuilder();
//        /**
//         * 将所有nodes信息写入HashMap<String,BaseClass> baseClassMap = new HashMap<>();
//         */
//        inst1 = Instant.now();
//        for (String[] triple: triples
//             ) {
//            if (triple[1].matches(".*rdf.*syntax-ns.*type.*")){
//                String type = RegexHelper.getMatchList(triple[2],"(?<=/)[^/]*?(?=>)").get(0);
//                BaseClass baseClass = new BaseClass(type,triple[0]);
//                baseClassMap.put(triple[0],baseClass);
//                subToHashMap.put(triple[0],HashHelper.SDBMHash(triple[0]));
//            }else if (triple[1].matches(".*type.*object.*type.*")){
//                BaseClass baseClass = baseClassMap.get(triple[0]);
//                TreeMap proMap = baseClass.getProperties();
//                String type = RegexHelper.getMatchList(triple[2],"(?<=/)[^/]*?(?=>)").get(0);
//                if (proMap.get("type_object_type") != null){
//                    proMap.put("type_object_type",proMap.get("type_object_type")+";"+type);
//                }else{
//                    proMap.put("type_object_type",type);
//                }
//            }else if (subToHashMap.keySet().contains(triple[0])&&subToHashMap.keySet().contains(triple[2])){
//                String relation = RegexHelper.getMatchList(triple[1],"(?<=/)[^/]*?(?=>)").get(0);
////                String[] edge = new String[]{subToHashMap.get(triple[0]).toString(),subToHashMap.get(triple[2]).toString(),relation,};
////                edges.add(edge);
//                edgesString.append(subToHashMap.get(triple[0]).toString()).append(",").append(relation).append(",").append(subToHashMap.get(triple[2]).toString()).append("\r\n");
//            }
//            else{
//
//                String proName = RegexHelper.getMatchList(triple[1],"(?<=/)[^/]*?(?=>)").get(0);
//                String proValue = triple[2];
//                if(triple[2].contains("<http://www.w3.org/2001/XMLSchema#")){
//                    String[] s = triple[2].split("\"");
//                    proValue = s[0];
////                            RegexHelper.getMatchList(triple[2],"(?<=\").*?(?=\")").sp
////                    proValue =  RegexHelper.getMatchList(triple[2],"(?<=\").*?(?=\")").get(0); //"25"^^
//                    proName = proName+":double";
//                }
//
//                BaseClass baseClass = baseClassMap.get(triple[0]);
//                TreeMap proMap = baseClass.properties;
//                if (proMap.get(proName) != null){
//                    proMap.put(proName,proMap.get(proName)+";"+proValue);
//                }else{
//                    proMap.put(proName,proValue);
//                }
//            }
//        }
//        inst2 = Instant.now();
//        System.out.println("将所有nodes信息写入HashMap<String,BaseClass> baseClassMap = new HashMap<>() spent " + Duration.between(inst1, inst2).getSeconds() + " seconds");
//
//        /**
//         * 写edge.csv
//         */
//        inst1 = Instant.now();
//        String edgeFilePath = parentDirPath+"\\edge.csv";
//        File edgeFile = new File(edgeFilePath);
//        if (!edgeFile.exists())
//            edgeFile.createNewFile();
//        FileWriterHelper.coverStringToFileNoNewLine(":START_ID,relation,:END_ID\r\n",edgeFilePath);
//        FileWriterHelper.appendStringNoNewLine(edgesString.toString(),edgeFilePath);
//        inst2 = Instant.now();
//        System.out.println("写edge.csv spent " + Duration.between(inst1, inst2).getSeconds() + " seconds");
//
//
//
//
//        /**
//         * 排序
//         */
//        inst1 = Instant.now();
//
//        Collection<BaseClass> valueCollection = baseClassMap.values();
//        List<BaseClass> nodesList = new ArrayList<BaseClass>(valueCollection);
//
////        BaseClass[] entities = (BaseClass[]) baseClassMap.values().toArray();
////        List<BaseClass> nodes = Arrays.asList(entities);
//
//        Collections.sort(nodesList, new Comparator<BaseClass>() {
//            @Override
//            public int compare(BaseClass o1, BaseClass o2) {
//                return o2.getType().compareTo(o1.getType());
//            }
//        });
//
//        inst2 = Instant.now();
//        System.out.println("对所有的顶点信息(BaseClass)排序 spent " + Duration.between(inst1, inst2).getSeconds() + " seconds");
//
//        /**
//         * 写所有顶点文件
//         */
//         inst1 = Instant.now();
//         String typeMark = "";
//         StringBuilder nodeLines = new StringBuilder();
//         File nodeFile;
//         String nodeFilePath = "wrong.csv";
//         StringBuilder linesString = new StringBuilder();
//         for (BaseClass baseClass : nodesList
//             ) {
//             String subject = baseClass.subject;
//             TreeMap<String,String> proMap = baseClass.getProperties();
//
//            if (!baseClass.getType().equals(typeMark)){
//                //写前一个文件的数据
//                if (!typeMark.equals(""))
//                {
//                    FileWriterHelper.appendStringNoNewLine(linesString.toString(),nodeFilePath,"utf-8");
//                }
//
//                typeMark = baseClass.getType();
//
//                //写头部
//                nodeFilePath = parentDirPath+"\\"+typeMark+".csv";
//                nodeFile = new File(nodeFilePath);
//                nodeFile.createNewFile();
//
//                linesString = new StringBuilder(typeMark+"Id:ID,name");
//                for (Map.Entry<String,String> entry:proMap.entrySet()
//                     ) {
//                    linesString.append(","+entry.getKey());
//                }
//                linesString.append(",:LABEL\r\n");
//            }
//
//             //写一行node数据
//             linesString.append(subToHashMap.get(subject)+","+subject);
//             for (Map.Entry<String,String> entry:proMap.entrySet()
//                     ) {
//                 linesString.append(","+entry.getValue());
//             }
//             linesString.append(","+typeMark+"\r\n");
//        }
//        FileWriterHelper.appendStringNoNewLine(linesString.toString(),nodeFilePath,"utf-8");//补写最后一次的数据
//        inst2 = Instant.now();
//        System.out.println("写所有顶点文件 spent " + Duration.between(inst1, inst2).getSeconds() + " seconds");
//
//    }

    /**
     *  具体算法：https://blog.csdn.net/wzwdcld/article/details/81117894
     * @param ttlpath
     */
//    public static void nTurplesToNeo4jCSV(String ttlpath){
//
//        ttlpath ="src/dataset/yum-without-english-comma.triple";
//        List<String[]> triples = FileHelper.readCSVToListWithoutFirstLine(ttlpath,' ');
//
//
//
//        /**
//         * 第一遍文件扫描
//         * scan .tll 的 22-synax-rdf#部分得到 ：
//         * map0 =  Map<Subject(String), subjectHash(long)> ,
//         * sToLmap = Map<Subject(String),Label(String)>,
//         * map1 = Map<Label（String）, Map<PropertyName（String）, ColNumber>>
//         * 此时PropertyName只有Lable+"Id":ID 和 subjectName两部分
//         *  map2 = Map<Label（String），int>。
//         * int部分记录Label已知的PropertyName的个数，初始化为1.
//         */
//        HashMap subToHashMap = new HashMap<String,Integer>();
//        HashMap subToLabelMap = new HashMap<String,String>();
//        HashSet<String> labels = new HashSet<String>();
//        HashMap labelToProNum = new HashMap<String,HashMap<String,Integer>>();
//        HashMap labelToProCount = new HashMap<String,Integer>();
//        for (String[] triple:triples
//             ) {
//            if (!triple[1].contains("rdf-syntax-ns#type"))
//                break;
//            subToHashMap.put(triple[0],HashHelper.SDBMHash(triple[0]));
//            String label = RegexHelper.getMatchList(triple[2],"(?<=/)[^/]*?(?=>)").get(0);
//            subToLabelMap.put(triple[0],label);
//            labels.add(label);
//        }
//        for (String label: labels
//             ) {
//            HashMap map = new HashMap<String,Integer>();
//            map.put(label+"Id:ID,",0);
//            labelToProNum.put(label,map);
//
//            labelToProCount.put(label,1);
//        }
//
//    }

    /**
     * 输出文件里形如"35.4"^^<http://www.w3.org/2001/XMLSchema#decimal>里的类型,且不重复
     * @param ttlpath
     */
    public static void  findAllPropertyType(String ttlpath) {

        /**
         * 读取.tll
         */
        ttlpath = "D:\\WeiyunTongbuPan\\Micro\\DataSet\\geo\\data\\geo@@23f75c2c-f6fb-4741-9cf8-1a79e829ee52@@geo_1@@carina.ttl";
        List<String[]> triples = FileReaderHelper.readCSVToListWithoutFirstLine(ttlpath, ' ');
        HashSet<String> types = new HashSet<String>();
        for (String[] triple: triples
             ) {
            if (triple[2].contains("<http://www.w3.org/2001/XMLSchema#")){
                String type = RegexHelper.getMatchList(triple[2],"(?<=#).*?(?=>)").get(0);


            }
        }
        for (String type: types
             ) {
            System.out.println(type);
        }

    }

    /**
     * 将ttl中relation和nodeTpye里的.替换为_
     * @param ttlpath
     */
    public static void  replaceDotWith_InTtl(String ttlpath) {

        /**
         * 读取.tll
         */
        ttlpath = "D:\\WeiyunTongbuPan\\Micro\\DataSet\\geo\\data\\geo@@23f75c2c-f6fb-4741-9cf8-1a79e829ee52@@geo_1@@carina.ttl";
        List<String[]> triples = FileReaderHelper.readCSVToListWithoutFirstLine(ttlpath, ' ');
//        for (String[] triple: triples
//                ) {
//
//                String init = RegexHelper.getMatchList(triple[2],"(?<=\\/)[^\\/]*?(?=>)").get(0);
//                types.add(type);
//            }
//        }
//        for (String type: types
//                ) {
//            System.out.println(type);
//        }

    }

    public static void main(String[] args) throws IOException {
        RDFHelper rdfHelper = new RDFHelper();



        rdfHelper.nTurplesToNeo4jCSV2("D:\\GeoInputData\\geo_replace_dot_with_undersco.ttl");
    }
}

