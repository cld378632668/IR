package dataStructure.JianZhiOfferLeetcode.Stack的题目比较难做;

import java.util.Stack;


/**
 * Created by ChenLD on 2018/4/8.
 *
 * @author ChenLD
 * @version 1.0
 */
public class QueueImplementWithTwoStacks {


    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {

        //先把stack2的元素全部搬进stack1
        int size = stack2.size();
        for (int i = 0; i < size ; i++) {
            stack1.push(stack2.pop());
        }

        //insert node into stack2
        stack2.push(node);

        //retrive the size nodes from stack1 into stack2
        for (int i = 0; i < size ; i++) {
            stack2.push(stack1.pop());
        }

        //insert node into stack1
        stack1.push(node);


    }

    public int pop() {
        if (stack2.size() > 0)
            return  stack2.pop();

        try {
            throw new Exception("no emlement to pop");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

}
