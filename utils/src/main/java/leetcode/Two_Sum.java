package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ChenLD on 2018/3/21.
 *
 * @author ChenLD
 * @version 1.0
 *          1
 */
public class Two_Sum {

    public static void main(String[] args){
            Solution.twoSum(new int[]{1,2,3},4);
    }
}

class Solution {
    public static int[] twoSum(int[] nums, int target) {

        Map<Integer,Integer> map = new HashMap<Integer,Integer>();

        int i;

        for( i=0; i<nums.length; i++){


            if(map.get(target - nums[i])!=null)

                break;
//              return new int[]{(int)map.get(target - nums[i]), i};

            else
               map.put(nums[i],i);

        }

        return new int[]{map.get(target - nums[i]).intValue() , i};

    }
}





