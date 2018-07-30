package Util.SimpleKVDB;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ChenLD on 2018/6/4.
 *
 * @author ChenLD
 * @version 1.0
 */
public class ObjectToStoreToKVDB {

    String name;
    int age;
    double score;

    public ObjectToStoreToKVDB() {
        super();
    }

    public ObjectToStoreToKVDB(String name, int age, double score) {
        super();
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ObjectToStoreToKVDB [name=" + name + ", age=" + age + ", score=" + score
                + "]";
    }



    public static void writeStudents(List<ObjectToStoreToKVDB> students) throws IOException {
        DataOutputStream output = new DataOutputStream(new FileOutputStream(
                "students.dat"));
        try {
            output.writeInt(students.size());
            for (ObjectToStoreToKVDB s : students) {
                output.writeUTF(s.getName());
                output.writeInt(s.getAge());
                output.writeDouble(s.getScore());
            }
        } finally {
            output.close();
        }
    }

    public static List<ObjectToStoreToKVDB> readStudents() throws IOException {
        DataInputStream input = new DataInputStream(new FileInputStream(
                "students.dat"));
        try {
            int size = input.readInt();
            List<ObjectToStoreToKVDB> students = new ArrayList<ObjectToStoreToKVDB>(size);
            for (int i = 0; i < size; i++) {
                ObjectToStoreToKVDB s = new ObjectToStoreToKVDB();
                s.setName(input.readUTF());
                s.setAge(input.readInt());
                s.setScore(input.readDouble());
                students.add(s);
            }
            return students;
        } finally {
            input.close();
        }
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        List<ObjectToStoreToKVDB> students = Arrays.asList(new ObjectToStoreToKVDB[] {
                new ObjectToStoreToKVDB("张三", 18, 80.9d), new ObjectToStoreToKVDB("李四", 17, 67.5d) });

        writeStudents(students);

        List<ObjectToStoreToKVDB> list = readStudents();
        System.out.println(list);

    }



}
