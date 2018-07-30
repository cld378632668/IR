package dataStructure.JianZhiOfferLeetcode.Stack的题目比较难做;

/**
 * Created by ChenLD on 2018/4/6.
 *
 * @author ChenLD
 * @version 1.0
 */
public class Stack {

    public static void main(String[] args) {

//        int[] a = new int[]{4,5,3,2,1};
//        int[] b = new int[]{1,2,3,4,5};
        int[] a = new int[]{1};
        int[] b = new int[]{2};
        Solution s = new Solution();
        System.out.println(s.IsPopOrder(a,b));

    }


}

class Solution {
    //**

    /**
     * OJ地址：
     * https://www.nowcoder.com/practice/d77d11405cc7470d82554cb392585106?tpId=13&tqId=11174&tPage=2&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
     * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
     * 序列4，5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
     * @param pushA
     * @param popA
     * @return
     */
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        /**
         * p 是 pushA的遍历指针，只能向后移动
         * head是栈的顶部元素在数组pushA中国的位置，可以向左向右移动。  如果pushA中的元素已出栈，赋值为 Integer.MIN
         *
         * p每向右移动一次，就看q处是否与p处相等，如相等 则q++ ，继续判断 .. 此部分详看代码，
         *
         * return true 的条件是 q = length
         */

        int len = pushA.length;
        if (len == 0) return false;
        int p = 0, peak = 0, q = 0;
        int[] stack = new int[len]; //模拟栈
        stack[0] = pushA[0];

        while ( p < len ){

            stack[peak] = pushA[p];

            while (q < len && peak >= 0 && stack[peak] == popA[q]  ){
                q++;
                peak--;
            }

            /**
             * 注意这里是大于等于len。 不是大于等于 len - 1
             */
            if ( q >= len  ) return true;

            //入栈
            p++;
            peak++;

        }

        return q >= len ? true : false;
    }
}