package dataStructure;

/**
 * Created by mac on 05/04/2018.
 */
public class MergeTwoSortedLsits {
}


 class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

 class Solution {
    public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1 == null && list2 == null )
            return null;

        if(list1 == null ){
            return list2;
        }
        if(list2 == null ) return list1;

        ListNode list =null;
        ListNode head = list;
        if(list1.val <= list2.val){
            list = new ListNode(list1.val);
            list = list.next;
            list1 = list1.next;
        }
        else{
            list = new ListNode(list2.val);
            list = list.next;
            list2 = list2.next;
        }


        while(list1 != null || list2!= null){

            if(list1 == null){
                list = list2;
                return head;
            }

            if(list2 == null){
                list = list1;
                return head;
            }

            if( list1.val <= list2.val ){
                list = new ListNode(list1.val);
                list = list.next;
                list1 = list1.next;
            }
            else{
                list = new ListNode(list2.val);
                list = list.next;
                list2 = list2.next;
            }
        }

        return head;
    }
}