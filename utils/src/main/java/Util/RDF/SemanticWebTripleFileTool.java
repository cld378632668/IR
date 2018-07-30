package Util.RDF;

import Util.RegexHelper;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;

public class SemanticWebTripleFileTool {


    public static HashSet<String> TripleFileAnalysor(String filePath) throws IOException {
        System.out.println("We will list the mesures of "+RegexHelper.getString("(?<=/)[^/]*?$",filePath) + ":");

        Instant inst1;
        Instant inst2;

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {

        }
        CSVReader csvReader = new CSVReader(fileReader,' ');

        /**
         * 头部处理
         */
        String[] firstLine = csvReader.readNext(); //不知道对csvReader.readAll()有没有影响
        System.out.println("The first line is "+firstLine[0]+" "+firstLine[1] +" "+firstLine[2] +" "+firstLine[3] +" ");
        boolean flag = false;//标记头部是否需要剔除

        /**
         *读取全部数据
         */
        List<String[]> lines = null;
        try {
             inst1 = Instant.now();
            lines = csvReader.readAll();
            /**
             * Notice! 头部处理
             */
            if(flag)
                lines.remove(0); //删除头部。
             inst2 = Instant.now();
            System.out.println("Reading all triples through CSVReader spent " + Duration.between(inst1, inst2).getSeconds() + " seconds");

        } catch (IOException e) {
            e.printStackTrace();
        }

        int tripleNum= lines.size();
        int edgeNum = 0;
        int entityNum;
        double edgeDensity;
        HashSet<String> sSet = new HashSet<>(); //store subjects
        HashSet<String> oSet = new HashSet<>(); //store objects
        HashSet<String> entityTypeSet = new HashSet<>();
        HashSet<String> propertyRelationSet = new HashSet<>();
        HashSet<String> betweenNodesRelationSet = new HashSet<>();

        for (String[] line:lines) {

            sSet.add(line[0]);

            if(line[1].contains("rdf-syntax-ns#type")){
                String type = RegexHelper.getMatchList(line[2],"(?<=/)[^/]*?(?=>)").get(0);
                entityTypeSet.add(type);
            }
            else if(line[2].startsWith("<") && line[2].endsWith(">")){ //line[1] doesn't contain "rdf-syntax-ns#type"
                oSet.add(line[2]);
                betweenNodesRelationSet.add(RegexHelper.getString("(?<=/)[^/]*?(?=>)",line[1]));
                edgeNum++;
            }
            else{
                propertyRelationSet.add(RegexHelper.getString("(?<=/)[^/]*?(?=>)",line[1]));
            }

        }

        inst1 = Instant.now();
//        lines.clear();
        inst2 = Instant.now();
        System.out.println("Clearing the list spent " + Duration.between(inst1, inst2).getSeconds() + " seconds");
        inst1 = Instant.now();
//        Runtime.getRuntime().gc();
        inst2 = Instant.now();
        System.out.println("The GC spent " + Duration.between(inst1, inst2).getSeconds() + " seconds");

        System.out.println("The number of triples is " + tripleNum);
        System.out.println("The number of edges is " + edgeNum);
        inst1 = Instant.now();
        HashSet<String> entitySet = new HashSet<>();
        entitySet.addAll(sSet);
        entitySet.addAll(oSet);
        entityNum = entitySet.size();
        inst2 = Instant.now();
        System.out.println("Union the two sets spent " + Duration.between(inst1, inst2).getSeconds() + " seconds");

        System.out.println("The number of entities is " + entitySet.size());
        System.out.println("The number of type is " + entityTypeSet.size());
        edgeDensity = edgeNum/(0.5*(entityNum -1 )*entityNum);
        System.out.println("EdgeDensity is " + edgeDensity);






        HashSet<String> repeatSet = new HashSet<>();
        repeatSet.addAll(sSet);
        repeatSet.retainAll(oSet);
        double repeatition = (double)repeatSet.size()/(double)sSet.size();
        System.out.println("'Repeatition > 0' guarantees multihop-2 can be supported. Repeatition of subjects and objects is " + repeatition);
        System.out.println("List  the intersection of objects and subjects : ");
        for (String type: repeatSet
                ) {
            System.out.println(type);
        }

        System.out.println("\n\nThere are " + entityTypeSet.size() + " types of entities.");
        System.out.println("List all the nodeTypes : ");
        for (String type: entityTypeSet
                ) {
                System.out.println(type);
        }




        System.out.println("The number of betweenNodesRelations is " + betweenNodesRelationSet.size());
        System.out.println("The number of propertyRelations is " + propertyRelationSet.size());

        System.out.println("\n\nList all the betweenNodesRelations : \n");
        for (String type: betweenNodesRelationSet
                ) {
            System.out.println(type);
        }

        System.out.println("\n\nList all the propertyRelations : \n");
        for (String type: propertyRelationSet
                ) {
            System.out.println(type);
        }

        return entityTypeSet;
    }

    public static void main(String[] args) {
        HashSet<String> typeSet = new HashSet<>();
        try {
            typeSet= SemanticWebTripleFileTool.TripleFileAnalysor("src/dataset/geo.triple");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("There are " + typeSet.size() + " types of entities.");
        System.out.println("List all the nodeTypes : ");
        for (String type: typeSet
             ) {
            System.out.println(type);
        }
    }
}
