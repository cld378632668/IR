package dataStructure.JianZhiOfferLeetcode.Tree;

/**
 * Created by ChenLD on 2018/4/5.
 *
 * @author ChenLD
 * @version 1.0
 */

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
         *     TreeNode right;
         *     TreeNode(int x) { val = x; }
         * }
         */


/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 */

/**
 *   程序有循环，暂时先不调bug了。
 *   跑一变可以看到循环的具体循环情况。
 */

public class 根据前序和中序遍历重建二叉树 {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] pre = new int[]{1,2,4,7,3,5,6,8};
        int[] in = new int[]{4,7,2,1,5,3,8,6};

TreeNode node = null;

       node =  solution.reConstructBinaryTree(pre, in);

        System.out.println();
    }

}

class Solution {

    public int findTargetInArray(int[] array, int target){
        for(int i =0 ;i < array.length ; i++)
            if(array[i] == target) return i;

        return -1;
    }

    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {

        if( pre.length != in.length || pre.length <= 0 || in.length <= 0  )
            return null;

        TreeNode node = new TreeNode(pre[0]);

        return reConstructBinaryTree(pre,in, 0, in.length, 0, pre.length, node);


    }

    public TreeNode reConstructBinaryTree(int [] pre,int [] in, int pS, int pE, int iS, int iE, TreeNode node) {
        int rI = findTargetInArray(in, pre[pS]);
        // rI = rootIndex = the Index of root in inOrder


        if( rI > iS){ //左子树存在
            //计算左子树在InOrder中的范围
            int leftChildBeginOfInOrder = iS;
            int leftChildEndOfInOrder = rI - 1;

            //插入左孩子
            node.left = new TreeNode(pre[pS + 1]);
            System.out.println( "插入左孩子"+pre[pS + 1]);

            //递归处理左子树

            reConstructBinaryTree(pre,in, pS+1, pS + 1 + rI - 1 - iS, iS, rI - 1, node.left);


        }

        if(rI < iE){
            int rightChildBeginOfOrder = rI + 1;
            int rightChildEndOfOrder = iE;

            //插入preOrder中右子树的第一个节点
            node.right = new TreeNode(pre[pS + (rI - 1 -iS) + 1]);  //pS + 左子树长度 + 1
            // 这里用pre 还是用in ？
            System.out.println( "插入右孩子"+pre[pS + (rI - 1 -iS) + 1]);

            reConstructBinaryTree(pre, in, pS + rI -iS, pE, rI+1, iE,  node.right);
        }

        return node;
    }
}

