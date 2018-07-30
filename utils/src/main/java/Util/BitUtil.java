package Util;

/**
 * Created by ChenLD on 2018/4/18.
 *
 * @author ChenLD
 * @version 1.0
 */
public class BitUtil {

    public static void main(String[] args) {
        int[] nums = new int[]{1};
        System.out.println( BitUtil.singleNumber1(nums, 3));
        return;
    }

    public static int 求一个数的相反数即负数(int a) {

        if (a > 0) {
            return ~a + 1;
        } else if (a < 0) {
            return ~(a - 1);
        } else {
            return a;
        }

    }

    /**
     *  数组nums中除了ret外全都重复出现repeatTime次。求ret
     * @param nums   至少必须都是整数
     * @param repeatTime
     * @return
     */
    public static int singleNumber1(int[] nums ,int repeatTime ) {

//        int repeatTime =3;
        int[] count = new int[32];
        int i;

        for(int num : nums){
            i = 0;
            while( i < 32){
                count[i] += num%2;
                num =  num>>1;
                i++;
            }
        }

        int ret = 0;
        i = 31;
        while(i >= 0){
            ret = (ret<<1) + count[i] % repeatTime;
            i--;
        }

        return ret;
    }

    /**
     *  数组nums中除了ret外全都重复出现repeatTime次。求ret
     * @param nums
     * @param repeatTime
     * @return
     */
    public static int singleNumber2(int[] nums ,int repeatTime ) {

        // int repeatTime = 3;
       int ret = 0;

        for (int i = 0; i < 32; i++) { //对应从右往左数第i+1位
            int count = 0; //记录所有数字在当前位上1的总个数
            for (int num : nums){
                count += (num>>i) & 1; //取num从右往左数第i+1位
            }
            //
            ret = ret | ( count % repeatTime) <<i ; //已有的右边i位 并（加） 上第i位
        }

        return ret;
    }

    /**
     *  数组nums中除了ret[0]、ret[1]外全都重复出现2次。求ret
     * @param nums
     * @param repeatTime
     * @return
     */
    public static int[] singleNumber3(int[] nums ,int repeatTime ) {
        //xor all elements to a result : pivot
        int pivot = 0;
        for(int num : nums){
            pivot ^= num;
        }

        //计算pivot从右向左第一个1的index
        int index = 0;
        while(index < 32){
            if( ((pivot>>index)&1) != 0){  //为什么这段代码报错了
                index++;
            }
        }

        //定义ret
        int[] ret = new int[2];
        for(int num : nums){
            if( (num&pivot) != 0 )
                ret[0] ^= num;
            else
                ret[1] ^= num;
        }

        return ret;
    }
}
