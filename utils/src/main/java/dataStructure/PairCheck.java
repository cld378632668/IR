package dataStructure;

import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by ChenLD on 2017/12/25.
 *
 * @author ChenLD
 * @version 1.0
 *          配对问题，判断一个字符串是否是配对的
 */
public class PairCheck {
//    配对问题 leetcode handbook  41.use  Parentheses 括号 as a case
//    Code it now: https://oj.leetcode.com/problems/valid-parentheses/
//
//    opening parenthesis ： （ [ {
//        closing parenthesis : ) } ]
//
// if the parenthesis is opening parenthesis :
//    directly push it
//else
//    check if  it and the peek of the stack are a pair
//
//    at last , return stack.isEmpty()

    private Map<Character,Character> map = new HashMap<Character,Character>(){{
        put('(',')');
        put('{','}');
        put('[',']');
    }};


    public boolean isValid(String s){
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()){
            if (map.containsKey(c))
                stack.push(c);
            else if (stack.isEmpty() || map.get(stack.pop()) != c)
                return false;
        }

        return stack.isEmpty();
    }

}
