package J2SEReview;

/**

 剑指Offer、 输入一个链表，从尾到头打印链表每个节点的值。
 翻转链表
**/


import java.util.ArrayList;
import java.util.List;

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}


class Solution {

    /**
     * 链表翻转函数，reverse 自定义 listNode 的链表
     * @param listNode
     * @return
     */
    public  ListNode reverseList(ListNode listNode){

        if (listNode == null)
            return null;

        ListNode p = listNode, q = p.next, r = p.next;
        p.next = null;
        //reverse
        while (q != null){
            r = r.next;
            q.next = p; //reverse
            p = q;
            q = r;
        }
        return p;
    }

    /**
     * dataStructure.JianZhiOfferLeetcode   链表	从尾到头打印链表
     * OJ地址：https://www.nowcoder.com/practice/d0267f7f55b3412ba93bd35cfa8e8035?tpId=13&tqId=11156&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     */


    /**
     * 输入一个链表，从尾到头打印链表每个节点的值。
     * 基于翻转链表解题。
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHeadBasedReversedList(ListNode listNode) {

        //方法一、先翻转链表后再打印

//        ListNode reversedListHead = reverseList(listNode);
//
//        ArrayList<Integer> arrayList = new ArrayList<Integer>();
//        if (reversedListHead == null)
//            return arrayList;
//
//        while(reversedListHead != null){
//            arrayList.add(reversedListHead.val);
//            reversedListHead = reversedListHead.next;
//        }
//
//        return arrayList;


/**
        //方法二、正向遍历以后把结果存到数组ArrayList<Integer>中,然后翻转ArrayList<Integer>
 **/
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        if (listNode == null)
            return arrayList;


        int i = 0;
        while(listNode != null ){
            arrayList.add(listNode.val);
            listNode = listNode.next;
            i++;
        }
        int size = i;
        ArrayList<Integer> resultArrayList = new ArrayList<Integer>();
        for (i = size - 1; i >= 0 ; i--) {
            resultArrayList.add(size - 1 - i, arrayList.get(i));
        }

        return resultArrayList;

    }


    /**
     * 输入一个链表，从尾到头打印链表每个节点的值。
     * 通过回溯去做，在递归返回时进行输出，这样就保证了是尾部节点先输出。
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHeadBasedBacktrack(ListNode listNode) {

        //待完成

        return null;

    }




}
public class ListAndArrayhUtil {

        /**
         * 输入一个数组构建自定义node的类型的List，此处Node类型为自定义的NodeList
         * @param array
         * @return
         */
        public ListNode arrayToCustomeLsit(int[] array){

            /**
             * test case :{67,0,24,58}
             */
            ListNode head = new ListNode(67);
            ListNode node1 =  new ListNode(0);
            ListNode node2 = new ListNode(24);

            head.next = node1;
            node1.next = node2;

            /**
             * 该部分程序第一种书写方法~~！
             * 下面尝试只使用head ,node 1 ,node 2 三个变量去产生n个节点的列表.
             * 引用指向时十分容易错误！！！
             */
              node1 = node2;
              node2 = new ListNode(58);
              node1.next = node2; //注意一定要有这句话
            /**
             * 运行上面两行代码时发生了什么，导致输出结果只有 67.0.24 ？
             */

            /**
             * 第二种方法
             * 写法是一直创建noden
             */
//             ListNode node3 = new ListNode(58);
//             node2.next = node3;

            /**
             * 第三种方法
             * 一种优化是创建 ListNode[] node 连避免一直创建noden ,还有其他优化方法吗？
             * 能不能只使用 head,node1,node2 来完成任务？
             */
             return head;
        }

        /**
         * 链表翻转函数，reverse 自定义 listNode 的链表
         * @param listNode
         * @return
         */
        public  ListNode reverseList(ListNode listNode){

            if (listNode == null)
                return null;

            ListNode p = listNode, q = p.next, r = p.next;
            p.next = null;
            //reverse
            while (q != null){
                r = r.next;
                q.next = p; //reverse
                p = q;
                q = r;
            }
            return p;
        }

        public static void main(String[] args){

            // input case
            int[]  array = new  int[]{ 67, 0 , 24, 58 };

            ListAndArrayhUtil laau = new ListAndArrayhUtil();

            ListNode head = laau.arrayToCustomeLsit(array);

            while (head!=null){
                System.out.println(head.val);
                head = head.next;
            }


        }
}
