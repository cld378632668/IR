package Algorithm.RecurrenceRecuresion;

/**
 * Created by ChenLD on 2018/4/5.
 *
 * @author ChenLD
 * @version 1.0
 */
public class 跳台阶或斐波那契 {

    public static void main(String[] args) {

        Solution solution = new Solution();
        System.out.println(solution.JumpFloorBaseRecurrence(39));
    }


}

/**
 *  只求的那个目标值， 递归的效率 比 递推高， 因为递归直接计算目标值
 *
 *  OJ地址：https://www.nowcoder.com/practice/8c82a5b80378478f9484d87d1c5f12a4?tpId=13&tqId=11161&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 *  递归可以通过
 *  递推超时
 */

class Solution {
    public int JumpFloorBaseRecurrence(int target) {
        //  F[n] = F[n-1] + F[n-2];

        int f1 = 1;
        int f2 = 2;
        int f3 = 3;

        // need  target - 3次迭代计算
        int n = 3;
        while(n!=target + 1){
            int temp = f3;
            f3 = f3 + f2;
            f2 = temp;

            n++;
        }

        return f3;

    }

//    public int JumpFloorBaseRecursion(int target) {
//        if (target <= 0) {
//            return -1;
//        } else if (target == 1) {
//            return 1;
//        } else if (target ==2) {
//            return 2;
//        } else {
//            return JumpFloor(target-1)+JumpFloor(target-2);
//        }
//    }
//





}