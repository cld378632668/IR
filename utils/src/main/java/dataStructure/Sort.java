package dataStructure;

import Util.FileCSVHelperByOpenCSV;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenLD on 2018/5/31.
 *
 * @author ChenLD
 * @version 1.0
 *
 *
 */
public class Sort {

    public void quickSort(ArrayList<Integer> array, int begin, int end){

        if (begin >= end || array.size() < end)
            return;

        int pivot = array.get(begin);
        int left = begin;
        int right = end;

        while(left < right){

            //从右边开始找到比pivot大的数
            while(left < right && array.get(right) >= pivot)
                    right--;
            if ( left < right )
                array.set(left, array.get(right));

            while (left < right && array.get(left) <= pivot)
                 left--;
            if (left < right)
                array.set(right, array.get(left));
        }

        array.set(left, pivot);

        quickSort(array, begin,left - 1);

        quickSort(array, left + 1, end);

    }






    public static void main(String[] args) {

        List<String[]> context = FileCSVHelperByOpenCSV.readCSVToList("src/dataset/small-Most-Recent-Cohorts-Scorecard-Elements.csv",',');

        ArrayList<Integer> array = new ArrayList<Integer>();

        for (String[] s: context) {
            array.add(Integer.valueOf(s[1]));
        }

        for (Integer i: array){
            System.out.println(i);
        }

        MultiThreadQuickSort t = new MultiThreadQuickSort(array,0,array.size() - 1);
        /**
         * 不知道如何从start()函数进入run()函数从而进行调试
         */
        t.start();

        t.getResult();


    }
}

/**x`x`x`
 * 关键思路将quickSort(ArrayList<Integer> array, int begin, int end) 的三个参数作为
 * MultiThreadQuickSort extends Thread  类的成员变量， 将快排函数的主体作为类的run()函数的主体
 */
class MultiThreadQuickSort extends Thread{

    ArrayList<Integer> array;
    int begin;
    int end;

    MultiThreadQuickSort(ArrayList<Integer> array, int begin, int end){
        this.array = array;
        this.begin = begin;
        this.end = end;
    }

    MultiThreadQuickSort(){

    }

    @Override
    public void run(){

        if (begin >= end || array.size() < end)
            return;

        int pivot = array.get(begin);
        int left = begin;
        int right = end;

        while(left < right){

            //从右边开始找到比pivot大的数
            while(left < right && array.get(right) >= pivot)
                right--;
            if ( left < right )
                array.set(left, array.get(right));

            while (left < right && array.get(left) <= pivot)
                left++;
            if (left < right)
                array.set(right, array.get(left));
        }

        array.set(left, pivot);

        //quickSort(array, begin,left - 1);
        // quickSort(array, left + 1, end);

        MultiThreadQuickSort thread1 = new MultiThreadQuickSort(array, begin, left - 1);
        MultiThreadQuickSort thread2 = new MultiThreadQuickSort(array,  left + 1, end);

        thread1.start();
        thread2.start();


    }


    public void getResult(){
        for (Integer i : array){
            System.out.print(i+" ");
        }
    }
}
