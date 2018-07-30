package dataStructure.JianZhiOfferLeetcode.Search;

/**
 * Created by ChenLD on 2018/4/4.
 *
 * @author ChenLD
 * @version 1.0
 */
public class BinarySearch {

    public int binarySearchBaseWhile(int[] array, int target, int l, int r){

        //
        int len = array.length;


        //judge array is not empty

        // compare target ot the smallest and the biggest
        l = 0 ;
        r = len - 1;
        int mid = ( l + r ) / 2;
        while(l <= r){

            mid = ( l + r ) / 2;

            if ( array[mid] == target) return mid;
            else if( array[mid] < target){
                l = mid + 1;
            }
            else {
                r = mid -1;
            }
        }
        return -1;

    }

    public int binarySearchBaseWhile( int target, int[] array, int l, int r){

        //
        int len = array.length;


        //judge array is not empty

        // compare target ot the smallest and the biggest
        l = 0 ;
        r = len - 1;
        int mid = ( l + r ) / 2;
        while(l <= r){

            mid = ( l + r ) / 2;

            if ( array[mid] == target) return mid;
            else if( array[mid] < target){
                l = mid + 1;
            }
            else {
                r = mid -1;
            }
        }
        return -1;

    }
}
