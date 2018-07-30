package dataStructure.JianZhiOfferLeetcode.Array;

/**
 * Created by ChenLD on 2018/4/4.
 *
 * @author ChenLD
 * @version 1.0
 */
public class 行列内部递增的二维数组中找目标值 {

    public static void main(String[] args){

        int[][] array = new int[][]{{1,2,3,6},{9,10,12,13},{12,17,23,25},{13,21,31,32}};

        Solution s = new Solution();

        System.out.println(s.Find( 21, array));

    }

}

 class Solution {



     // binary search , 严格递增数组
     public int binarySearchBaseRecursion(int target, int[] array, int l, int r) {

         if (array.length == 0) return -1;

         if (l >= array.length) return -1;

         if (r <= 0) return -1;

         int len = r - l;

         if (len == 0 && target == array[l]) return l;

         int pivot = len / 2;

         if (l <= r) { // notice

             if (array[pivot] == target) return pivot;

             if (array[pivot] < target)

                 return binarySearchBaseWhile(target, array, pivot + 1, r);

             else

                 return binarySearchBaseWhile(target, array, l, pivot - 1);

         }

         return -1;

     }

     public int binarySearchBaseWhile(int[] array, int target, int l, int r) {

         //
         int len = array.length;


         //judge array is not empty

         // compare target ot the smallest and the biggest
         l = 0;
         r = len - 1;
         int mid = (l + r) / 2;
         while (l <= r) {

             mid = (l + r) / 2;

             if (array[mid] == target) return mid;
             else if (array[mid] < target) {
                 l = mid + 1;
             } else {
                 r = mid - 1;
             }
         }


         return -1;

     }

     /**
      * 相比另一个binarySearchBaseWhile()调换了参数顺序，是多态。
      *
      * @param target
      * @param array
      * @param l
      * @param r
      * @return
      */
     public int binarySearchBaseWhile(int target, int[] array, int l, int r) {

         //
         int len = array.length;


         //judge array is not empty

         // compare target ot the smallest and the biggest
         l = 0;
         r = len - 1;
         int mid = (l + r) / 2;
         while (l <= r) {

             mid = (l + r) / 2;

             if (array[mid] == target) return mid;
             else if (array[mid] < target) {
                 l = mid + 1;
             } else {
                 r = mid - 1;
             }
         }

         return -1; //not find

     }
     /**
      * 0   1     2     3
      * <p>
      * 0 1   2     3     6
      * <p>
      * 1 9   10   12    13
      * <p>
      * 2 12  17   23    25
      * <p>
      * 3 13  21   31    32
      * <p>
      * target 是31
      **/
     public boolean Find(int target, int [][] array) {

        int width = array.length;

        if( width <= 0) return false;

        int height = array[0].length;

        if( height <= 0) return false;

        for(int i = 0; i < width; i++ ){

            int tmp = binarySearchBaseWhile( target, array[i], 0, array[i].length - 1);

            if( tmp != -1 ) return true;

        }

        return false;

    }

 }