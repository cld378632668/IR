package dataStructure.JianZhiOfferLeetcode.Search;

/**
 * Created by ChenLD on 2018/4/4.
 *
 * @author ChenLD
 * @version 1.0
 */
public class Main {

    public static void main(String[] args){

//        int[] array = new int[]{ 1,2,3,4,5,6,9,14};
        int[] array = new int[]{ 1,2,3,4,5,6,9,14};

        BinarySearch binarySearch = new BinarySearch();

        int result;
        result = binarySearch.binarySearchBaseWhile(array,6,0,8);
        System.out.println(result);




    }
}
