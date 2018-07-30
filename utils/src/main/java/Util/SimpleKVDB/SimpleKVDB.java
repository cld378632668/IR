package Util.SimpleKVDB;

import java.io.*;
import java.util.*;

/**
 * Created by ChenLD on 2018/6/2.
 *
 * @author ChenLD
 * @version 1.0
 *
 *
 *
 *
 * 索引文件中存 键， 值在.data文件中的位置。KV文件中只存value.length 和 value。
 * KV文件中的Value最大长度固定，如果没有存满补空白到满。
 *
 */
public class SimpleKVDB {


    //索引信息， 键， 值在.data文件中的位置. Long是在文件里的key后读取一个Long长度
    Map<String,Long> indexMap;
    //空白空间， 值为在.data文件中的位置
    Queue<Long> gaps;

    // KV数据库
    RandomAccessFile randomAccessFileDB;
    //元数据文件
    File metaFile;

    //value最大长度
    private static final int MAX_VALUE_LENGTH = 1020;
    //value补白字节
    private static final byte[] ZERO_BYTES = new byte[MAX_VALUE_LENGTH];

    //KV数据库文件后缀
    private static final String DATA_SUFFIX = ".data";
    //元数据文件后缀，第一部分是索引数据的持久化，第二部分是空白索引空间数据的持久化
    private static final String META_SUFFIX = ".meta";

    public SimpleKVDB(String path, String name) throws IOException {
        File dataFile = new File(path + name + DATA_SUFFIX);
        metaFile = new File(path + name + META_SUFFIX);

        randomAccessFileDB = new RandomAccessFile(dataFile, "rw");

        if (metaFile.exists()){
            loadMeta();
        }else{
            indexMap = new HashMap<>();
            gaps = new ArrayDeque<>();
        }
    }

    private void loadMeta() throws IOException{

        DataInputStream in = new DataInputStream(new BufferedInputStream( new FileInputStream(metaFile)));

        try{
            loadIndex(in);
            /**
             * loadIndex(in) 对loadGaps(in)中的in有何影响？
             */
            loadGaps(in); //存在元数据文件中
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void loadGaps(DataInputStream in) throws IOException {
        int size = in.readInt();
        gaps = new ArrayDeque<>(size);
        for (int i = 0; i < size; i++) {
            /**
             * 下面这两行的逻辑无法理解
             */
            long index = in.readLong();
            gaps.add(index);
        }

    }

    private void saveGaps(DataOutputStream out) throws IOException {

        out.writeInt(gaps.size());
        for (Long pos : gaps){
            out.writeLong(pos);
        }

    }

    private void saveMeta() throws IOException{
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(metaFile)));

        try{
            saveIndex(out);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void saveIndex(DataOutputStream out) throws IOException {
        out.writeInt(indexMap.size());
        for (Map.Entry<String, Long> entry : indexMap.entrySet()){
            out.writeUTF(entry.getKey());
            out.writeLong(entry.getValue());
        }
    }


    private void loadIndex(DataInputStream in) throws IOException {

        int size = in.readInt(); // size 指的是KV对的个数？
        indexMap = new HashMap<String, Long>((int)(size / 0.75f) + 1 , 0.75f );
        for (int i = 0; i < size; i++){
            String key = in.readUTF();
            long index = in.readLong();
            indexMap.put(key, index);
        }

    }

    private byte[] getData(long pos) throws IOException{
        randomAccessFileDB.seek(pos);
        int length = randomAccessFileDB.readInt();
        byte[] data = new byte[length];
        randomAccessFileDB.readFully(data);
        return data;

    }

   private void writeData(long pos, byte[] data) throws IOException {

       if (data.length > MAX_VALUE_LENGTH){
           throw new IllegalArgumentException("maximum allowed value length is "
                   + MAX_VALUE_LENGTH + ", data length is " + data.length);
       }
       randomAccessFileDB.seek(pos);
       randomAccessFileDB.writeInt(data.length);
       randomAccessFileDB.write(data);
       randomAccessFileDB.write(ZERO_BYTES, 0, MAX_VALUE_LENGTH - data.length);

   }

   private long nextAvailablePos() throws IOException {

       if (!gaps.isEmpty()){
           return gaps.poll();
       }else{
           return randomAccessFileDB.length();
       }

   }

   public void put(String key, byte[] value) throws IOException{
       Long index = indexMap.get(key);
       if (index == null){
           index = nextAvailablePos();
           indexMap.put(key, index);
       }
       writeData(index, value);
   }

  public byte[] get(String key) throws IOException {
      Long index = indexMap.get(key);
      if (index != null){
          return getData(index);
      }
      return null;
  }

  public void remove(String key){
      Long index = indexMap.remove(key);
      if (index != null){
          gaps.offer(index);
      }
  }



    public void close() throws IOException{
        flush();
        randomAccessFileDB.close();
    }

    private void flush() throws IOException {
        saveMeta();
        /*
         *   作用不懂
          */
        randomAccessFileDB.getFD().sync();

    }
}
