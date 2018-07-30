package dataStructure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Properties;

/**
 * Created by mac on 06/04/2018.
 */
public class HeapUtil_PriorityQueueExample {

    public  static void main(String[] args){
        int[] input = new  int[]{4,5,1,6,2,7,3,8 };
        int k = 4;

        HeapUtil_PriorityQueueExample hp = new HeapUtil_PriorityQueueExample();

        System.out.println(hp.GetLeastNumbers_Solution(input, k ));

    }

    /**
     * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
     *
     * 这道题很有意思。
     * 实际是最大堆问题，只需维护K大小的堆。
     * 用最大堆来维护K的最小的数。 为什么是最大堆，而不是最小堆， 因为用最大堆来维护最小的K个树，
     * 每次插入一个数，如果头部比它大，只要把头部去掉，插入新元素重新调整。
     * 而使用最小堆的话，每次得跟所有元素比较，你说有趣不有趣。
     * Java PriorityQueue is implementioned by heap.
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {

        ArrayList<Integer> result = new ArrayList<>();

        int length = input.length;

        if (k > length || k == 0){
            return result;
        }


        /**
         *
         * 问题：讲一讲Java compare()函数.
         * 一个可以给满分的答案结合了系统实际设计和编码的：
         *      我举个例子，实现系统时会高频率用到PriorityQueue, 优先级队列本质是用heap来实现的，需要自定义优先级计算规则。
         * 自定义优先级规则的具体方式是传入一个Comparator对象，重写其中的compare方法。
         * Compare方法依赖于Object自身的compareTo方法。
         *
         *  compareto函数 。 object1.compareTo(object2)
         *  如果指定的对象object1与参数object2相等返回0。
            如果指定的对象小于参数返回 -1。
            如果指定的对象大于参数返回 1
         *
         */
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {


            /**
             * 对于compare函数的调用者来说， 调用者只知道传参的具体信息和返回值的具体信息， 也就是调用者根本不知道里面写了 o1.compareTo(o2)
             * 正常情况下都是从下到大排， 一般这样叫作自然序， 如果返回-1的话，
             * 就是表示满足自然序,就会把  compare(Integer o1, Integer o2) 中的o1排在前面？
             *
             * @param o1
             * @param o2
             * @return
             */
            @Override
            public int compare(Integer o1, Integer o2) {
                /**
                 * 如果我希望大的数排在前面向下面一样写对吗？原则是什么？
                 */
//                return o1.compareTo(o2); // 错误的写法
                  return o2.compareTo(o1); // 正确的写法
                /**
                 *
                 * 正常情况下都是从下到大排， 一般这样叫作自然序， 如果返回1的话，就是表示满足自然序？
                 * 所以我现在想让其返回 1 的时候是升序， 应该是写成 o2.compareTo(o1) ？
                 * 99%是正确的
                 */
            }
        });


        /**
         * 有大顶堆维护最小的k个数，
         */
        for (int i= 0 ; i < length ; i++){

            if ( maxHeap.size() !=  k){ //还有可以插入的空间
                maxHeap.offer(input[i]);
            }else if (maxHeap.peek() > input[i]) {
                /**
                 * 如果新插入的数比堆顶小，就把堆顶删掉
                 */
                Integer temp = maxHeap.poll();
                temp = null;
                maxHeap.offer(input[i]);
            }
        }
        for (Integer integer : maxHeap){
            result.add(integer);
        }
        return result;
    }




}
