package J2SEReview;

import java.security.Key;
import java.util.*;
import java.util.Map.Entry;

/**
 * Created by ChenLD on 2018/3/30.
 *
 * @author ChenLD
 * @version 1.0
 *          Java中关于Map的9大问题，能把这个说清楚就说明你真的有编程经验了。
 *          得先把泛型基础看一看
 */
public class Map9Questions {

    public static void main(){ //
        /**
         * 0、
         * Map 的keySet()\valueSet()\entrySet() 转 List ， 本质是Set转List
         */
        Map map = new HashMap<String,String>();
        List keyList = new ArrayList( map.keySet());
        //analogize

        /**
         * 1\
         * Iterate Map by Entry
         */
//        for (Entry entry :  map.entrySet()) {
//
//
//        }
        /**
         * 2-1\  通过Key来对Map排序
         * 排序需要对Map的ke进行频繁的操作，一种方式就是通过比较器(comparator )来实现：
         */
//        List list = new ArrayList(map.entrySet());
//        Collections.sort(list, new Comparator<Entry>() {   //!!!!!!Comparator<Ａ>里面对应compare<Ａ, Ａ>
//            @Override
//            public int compare(Entry e1, Entry e2) {
////                return e1.getKey().compareTo(e2.getKey());
//            }
//        });

        /**
         * 　另外一种方法就是通过SortedMap，但必须要实现Comparable接口。
         */

//        SortedMap  sortedMap = new TreeMap(new Comparator<Key,Key>() {
//            @Override
//            public int compare(Key k1, Key k2) {
////                return  k1.compareTo(k2);
////            }
//        });

        /**
         *基于value的值对Map进行排序 **  这与上一点有些类似 ，还是借助Comparator接口
         */


    }
}

//