package J2SEReview;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Scanner;

/**
 * Created by ChenLD on 2018/3/30.
 *
 * 分支和loop练习题
 *
 * 每次可以做如下选择之一：
 * 1 购买一件衣服；2，去其他家店，3 有事回家
 *
 * @author ChenLD
 * @version 1.0
 */
public class exercise2_Branch {

    public static void main(){

        Scanner in = new Scanner(System.in);
        int num = 0; //the num of clothes we buy
        int j = 0;

        boolean flag = true;//标记是否要中断最外层循环，即回家
        for (int i = 0; i < 5; i++) {
            System.out.println("欢迎来到第" + i + "家店");
            for (j = 1; j <= 3; j++) {
                System.out.println("输入 1 购买一件衣服；2，去其他家店，3 有事回家");
                int a = in.nextInt();
                while (a < 0 || a > 3) {
                    System.out.print("输入有误，请重新输入：(请输入1-3之间的数字，否则无法识别)");
                    a = in.nextInt();
                }
                if (a == 1) {
                    num++;
                } else if (a == 2) {
                    System.out.println("去其他店");
                    break;
                }else{
                    flag = false;
                    break;
                }
            }
            if (!flag){
                break;
            }
        }
        System.out.println("您一共购买了" + num + "件衣服");

    }

}
