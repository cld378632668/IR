package J2SEReview;

import java.util.Scanner;

/**
 * Created by 不要做太多分享浪费自己时间 on 2018/3/29.
 * 求重复度最大的地方的方块数目。
 *
 * 考察：
 * 1、 基本的输入输出：
 * Scannar sc = new Scanner（System.in）;
 * sout
 *
 * 2、 数组的创建和声明：
 * int n；
 * int[] array = new int[n];
 *
 * @author 不要做太多分享浪费自己时间
 * @version 1.0
 */
public class exercise1_In_Out_Array_wangyi_2019_intern {

    public void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int n, x11 = 0, x22 = 0, y11 = 0, y22 = 0;

        /**
         *    全局範圍
         *    .......        (x22,y22)
         *   （x11,y11）      ......
         *
         */

        n= sc.nextInt();

        int[] x1  = new int[n];
        int[] x2 = new int[n];
        int[] y1 = new int[n];
        int[] y2 = new int[n];

        for (int i=0; i<n; i++){

            x1[i] = sc.nextInt();
            if( x1[i] <= x11 )
                x11 = x1[i];

        }

        for (int i=0; i<n; i++){

            y1[i] = sc.nextInt();
            if( y1[i] <= y11 )
                x11 = x1[i];

        }

        for (int i=0; i<n; i++){

            x2[i] = sc.nextInt();
            if( x2[i] >= x22 )
                x22 = x2[i];

        }

        for (int i=0; i<n; i++){

            y2[i] = sc.nextInt();
            if( y2[i] >= y22 )
                x22 = x2[i];

        }

        /**
         *    遍历全局範圍的所有店，看这个点属于多少方块
         *
         *    .......        (x22,y22)
         *   （x11,y11）      ......
         *
         *   点（x,y）属于某个方块 [（x1[i],y1[i]）, (x2[i],y2[i])]的条件：
         *       x >= x1[i] && x <= x2[i] && y >= y1[i] && y <= y2[i]
         *
         *
         */

        int max = 0 ,  current = 0;

        //遍历所有x  [x11,x22]
        //遍历所有y  [y11,y22]
        //遍历所有i ,即方块
        for(int x = x11; x <= x22; x++){
            for(int y = y11; y <= y22; y++){
                current = 0;
                for (int i =0 ; i < n; i++){
                    if(x >= x1[i] && x <= x2[i] && y >= y1[i] && y <= y2[i])
                        current ++;
                }
                if( current > max ) max = current;
            }
        }

        System.out.println(max);

    }




}
