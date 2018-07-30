package dataStructure.Tree;

/**
 * Created by mac on 06/04/2018.
 */


import sun.reflect.generics.tree.Tree;

import java.util.Stack;

/**
 *
 * 树的问题优先思考用递归解。
 * 树的问题优先思考用递归解。
 * 树的问题优先思考用递归解。
 * 树的问题优先思考用递归解。
 */
public class BinaryTreeUtil {




    public static TreeNode generateTreeForTest1(){

        /**
         8
         /  \
         6   10
         / \  / \
         5  7 9 11
         */
        TreeNode root = new TreeNode(8);

        TreeNode node1_1 = new TreeNode(6);
        TreeNode node1_2 = new TreeNode(10);
        root.left = node1_1;
        root.right = node1_2;

        TreeNode node1_1_1 = new TreeNode(5);
        TreeNode node1_1_2 = new TreeNode(7);
        node1_1.left = node1_1_1;
        node1_1.right = node1_1_2;

        TreeNode node1_2_1 = new TreeNode(9);
        TreeNode node1_2_2 = new TreeNode(11);
        node1_2.left = node1_2_1;
        node1_2.right = node1_2_2;



        return root;
    }



    /**
     *
     * 题目描述
     操作给定的二叉树，将其变换为源二叉树的镜像。
     输入描述:
     二叉树的镜像定义：源二叉树
     8
     /  \
     6   10
     / \  / \
     5  7 9 11
     镜像二叉树
     8
     /  \
     10   6
     / \  / \
     11 9 7  5


     * OJ测评地址：
     * https://www.nowcoder.com/practice/564f4c26aa584921bc75623e48ca3011?tpId=13&tqId=11171&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     *
     *
     * 用递归，还是那句话，树的问题优先用递归解。
     *
     * @param root
     */


    private static Stack stack = new Stack();

    public void ConvertToMirrorBinTree(TreeNode root) {

        if(root == null) return;

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        ConvertToMirrorBinTree(root.left);

        ConvertToMirrorBinTree(root.right);


    }


    /**
     * 假设深搜只有唯一解。
     * 程序找到第一个解则终止。
     *
     * 将深度搜索的路径存储在Stack中(可行)
     * 使返回值为Stack的解法我没想到，暂时用int.   stack作为引用传入
     *
     * 在典型用例上测试通过。
     */
    /**
     *
     * @param root current node
     * @param target
     * @param stack
     * @return
     */
    public static int depthTravel(TreeNode root, TreeNode target, Stack stack){//由于Java是引用传递，在整个递归过程中stack都是同一个，应该没错

        if (root == null) return 0;

        if(root == target) //(2)
        {
            stack.push(root);
            return 1;
        }

        stack.push(root);
       if(depthTravel(root.left,target,stack) == 1) return 1;
       if(depthTravel(root.right,target,stack) == 1) return 1;
        stack.pop();

        return 0;

    }




    /**
     * Constraint: 一定有解
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){

        return root;
    }

    /**
     * 利用先找到的那个节点的已遍历路径，避免第二节点重复遍历这些路径
     * @param root
     * @param p
     * @param q
     * @param stackP
     * @param stackQ
     */
    public void depthTravelTogether(TreeNode root, TreeNode p, TreeNode q, Stack stackP, Stack stackQ, boolean ifCopy, boolean ifTravelP, boolean ifTravelQ ){
        if (stackP.peek().equals(p) && stackQ.peek().equals(q))
            return;

        if (stackP.peek().equals(p)){

        }


    }

    public static void main(String[] args) {
       TreeNode root =  generateTreeForTest1();

        /**
         * Test depthTravel
         */
        TreeNode target = root.right.left;
       Stack<TreeNode> stack = new Stack<TreeNode>();
       depthTravel(root,target,stack);
        for (TreeNode t: stack
             ) {
            System.out.println(t.val);
        }
        /**
         * End of  Test depthTravel
         */
    }
}

