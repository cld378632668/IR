package J2SEReview;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ChenLD on 2018/3/30.
 *
 * @author ChenLD
 * @version 1.0
 */
public class CollectionDemos {

    public static void main(){

        /**
         * 基本接口
         *
         */
        Map map = new HashMap<String,String>();

        map.put("Key1","Repeated-Value");
        map.put("Key2","Repeated-Value");
        map.get("not_exsit_key");
        map.get("Key1");

        /**
         * KV Entry
         *
         */
        Map jsonMap = new HashMap<String,Map>();
        Map valueMap1 = new HashMap<String,String>();
        Map valueMap2 = new HashMap<String,String>();

        jsonMap.put("key1", valueMap1);
        jsonMap.put("key2", valueMap2);



//        Map.Entry entry1 = jsonMap.entrySet().toArray()[1];
    }

}
