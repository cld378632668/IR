package dataStructure.JianZhiOfferLeetcode.Tree;

/**
 * Created by mac on 06/04/2018.
 */


import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * 树的问题优先思考用递归解。
 * 树的问题优先思考用递归解。
 * 树的问题优先思考用递归解。
 * 树的问题优先思考用递归解。
 */
public class BinaryTreeUtil {

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
    public void ConvertToMirrorBinTree(TreeNode root) {

        if(root == null) return;

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        ConvertToMirrorBinTree(root.left);

        ConvertToMirrorBinTree(root.right);


    }


    /**
     * 树的层序遍历  多练习写几次
     * 借助队列来做广度优先搜索。 ArrayList可以当作队列来用。
     * @param root
     * @return
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {

        ArrayList<Integer> resultList = new ArrayList<>();

        ArrayList<TreeNode>  queue = new ArrayList<>();

        if (root == null) return resultList;

        queue.add(root);
        while(!queue.isEmpty()){
            //拿出头部元素，放入resultList中
            TreeNode temp = queue.remove(0);
            resultList.add(temp.val);
            if (temp.left != null){
                queue.add(temp.left);
            }
            if (temp.right != null){
                queue.add(temp.right);
            }
        }

        return resultList;

    }





    TreeNode BinarySortedTreeToDoubleLinkedList(TreeNode root){


        return null;
    }

    /**
     * use a stack
     * @param root
     * @return
     * successful
     * https://www.nowcoder.com/questionTerminal/947f6eb80d944a84850b0538bf0ec3a5
     * not familiar
     */
    TreeNode BSTreeToDoubleLinkListRecursion1(TreeNode root){

        /**
         * 1、健壮性处理，预处理
         */
        if (root == null) return null;
        if(root.left==null&&root.right==null)
            return root;
        /**
         * 2、递归转化左子树，返回头结点，通过头结点得到尾节点，将尾节点与当前root相连;
         */

        TreeNode left = BSTreeToDoubleLinkListRecursion1(root.left);
        TreeNode tail = left;
        while(tail!=null  && tail.right != null  )  tail = tail.right; // 易出错处1 tail.right != null
        if (tail!=null){
            tail.right = root;
            root.left = tail;
        }
        /**
         * 3、递归转化右子树，返回头结点，将头结点连接到root右侧;
         */
        TreeNode right = BSTreeToDoubleLinkListRecursion1(root.right);
        if (right!=null){
            right.left = root;
            root.right = right;
        }
        /**
         * 4、  2 和3 得到了一整个链表L， 最后将这一整个链表的头部返回
         */

        return left!=null ? left : root; // 易出错处2  left 可能并没有接到 root上
    }





    /**
     * use a stack
     * @param root
     * @return
     *
     * https://www.nowcoder.com/questionTerminal/947f6eb80d944a84850b0538bf0ec3a5
     */
    TreeNode BSTreeToDoubleLinkListRecursionWithStack(TreeNode root){

        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;
        boolean isFirst = true;
        while( cur!=null || stack.isEmpty()){
            break;
        }
        //还没写完
        return root;
    }



}

