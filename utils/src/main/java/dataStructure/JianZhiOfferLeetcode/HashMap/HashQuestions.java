package dataStructure.JianZhiOfferLeetcode.HashMap;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ChenLD on 2018/4/8.
 *
 * @author ChenLD
 * @version 1.0
 */
public class HashQuestions  {

    public static void main(String[] args){

        int[]  array =  new int[]{2,3,1,0,2,5,3};

        HashQuestions hq = new HashQuestions();

        System.out.println(hq.findADuplicateBaseHash(array, 7, array ));

    }

    /**
     *
     不需要额外的数组或者hash table来保存，题目里写了数组里数字的范围保证在0 ~ n-1 之间，
     所以可以利用现有数组设置标志，当一个数字被访问过后，可以设置对应位上的数 + n，
     之后再遇到相同的数时，会发现对应位上的数已经大于等于n了，那么直接返回这个数即可。
     * @param numbers
     * @param length
     * @param duplication
     * @return
     */
    public boolean findADuplicateWithoutHash(int numbers[], int length, int [] duplication) {

        return true;
    }

    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false
    public boolean findADuplicateBaseHash(int numbers[], int length, int [] duplication) {

        return  duplicate(numbers,length, duplication);
    }

    public boolean duplicate(int numbers[],int length,int [] duplication) {

        Set set = new HashSet<Integer>();

        for (int i = 0 ; i < length; i++){

            if ( set.contains(numbers[i]) ){
                return true;
            }else{

                set.add(new Integer(numbers[i]));
            }

        }

        return false;

//        HashMap map = new HashMap<Integer>();
//
//        for (int i = 0 ; i < length; i++){
//
//            if ( map.get(numbers[i]) != null){
//                return true;
//            }else{
//
//                map.put(new Integer(numbers[i]));
//            }
//
//        }
//
//        return false;
    }


}
