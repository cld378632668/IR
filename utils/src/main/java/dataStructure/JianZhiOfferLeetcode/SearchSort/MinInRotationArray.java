package dataStructure.JianZhiOfferLeetcode.SearchSort;

/**
 * Created by ChenLD on 2018/4/5.
 *
 * @author ChenLD
 * @version 1.0
 */
public class MinInRotationArray {


}

class Solution {

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     * @param array
     * @return
     */
    public int minNumberInRotateArray(int [] array) {

        int len = array.length;
        if (len == 0) return 0;
        if (len == 1) return array[0];
        if (len == 2) return Math.min(array[0],array[1]);

        int mid = len / 2;

        if(array[mid] <= array[ (mid - 1) % len] && array[mid] <= array[(mid + 1) % len]);

        return  -1;

    }


//    public  int binarySearchDerive(int []array)
}