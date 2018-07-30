package dataStructure;

import java.util.Stack;

/**
 * Created by ChenLD on 2017/12/25.
 *
 * @author ChenLD
 * @version 1.0
 *          a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 */
public class MinStack {

    private Stack<Integer> dataStack = new Stack<>();//primary stack
    private Stack<Integer> minStack = new Stack<>();//primary stack

    public void push(int x){
        dataStack.push(x);
        if (minStack.size() == 0){
            minStack.push(x);
            return ;
        }
        else
            minStack.push( x < minStack.peek() ? x : minStack.peek() );
    }

    public void pop(){
        if (dataStack.size() > 0){
            dataStack.pop();
            minStack.pop();
        }
    }

    public int top(){
        return dataStack.peek();
    }

    public Integer getMin(){
        if ( minStack.size() > 0 ){
            return  minStack.peek();
        }
        return null;
    }
}
