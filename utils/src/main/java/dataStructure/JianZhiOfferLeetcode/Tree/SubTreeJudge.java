package dataStructure.JianZhiOfferLeetcode.Tree;

/**
 * Created by mac on 06/04/2018.
 */



public class SubTreeJudge {


    public static void main(String[] args){
        Solution s = new Solution();





    }

}



 class Solution1 {


    /**
     * Main Function
     * Judge if B is subTree of A
     *
     * 限定条件：【null不是任何树的子树】
     */
    public boolean isSubTree(TreeNode A, TreeNode B){

        if(B == null || A == null) return false;



        if( A.val == B.val){
            return isSubTreeAllowNull(A.left,B.left) && isSubTreeAllowNull(A.right,B.right);
            //因为有null不是任何树子树这个限定条件在。无法仅依靠isSubTree自身完成所有递归检测,需要重新定义一个hasSubTreeAllowNull（）。

        }


        if (A.val != B.val){
            return isSubTree(A.left, B) || isSubTree(A.right, B);
        }


        return false;
    }


    /**
     *  因为有null不是任何树子树这个限定条件在。无法仅依靠isSubTree自身完成所有递归检测。
     *
     *  * Judge if B is subTree of A
     *
     *  约束条件： B.val == A.val
     *
     *  【null是任何树的子树。】
     *
     *
     *
     * @param A
     * @param B
     * @return
     */
    public boolean isSubTreeAllowNull(TreeNode A, TreeNode B){

        if(B == null) return true;
        //在B！=null的基础上
        if(A == null) return false;

        if(A.val != B.val) return false; // 有一个对应点的值不对应就返回。

        return isSubTreeAllowNull(A.left,B.left) && isSubTreeAllowNull(A.right,B.right);

    }

}
