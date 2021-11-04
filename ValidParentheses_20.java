/*
20. Valid Parentheses
Easy

Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

  Open brackets must be closed by the same type of brackets.
  Open brackets must be closed in the correct order.



Example 1:

Input: s = "()"
Output: true

Example 2:

Input: s = "()[]{}"
Output: true

Example 3:

Input: s = "(]"
Output: false

Example 4:

Input: s = "([)]"
Output: false

Example 5:

Input: s = "{[]}"
Output: true



Constraints:

  1 <= s.length <= 104
  s consists of parentheses only '()[]{}'.


*/


class Solution {
    public boolean isValid(String s) {
        if(s == null) return true;
        if(s.length()%2==1) return false;
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(isOpenBracket(c)){
                stack.push(c);
            } else if(isCloseBracket(c)){
                if(stack.isEmpty()){
                    return false;
                }
                char pop = stack.pop();
                if(areNotCompatible(pop, c)){
                    return false;
                }
            }
            else{
                return false;
            }
        }
        return stack.isEmpty();

    }
    static boolean isOpenBracket(char c){
        return c == '(' || c == '{' || c == '[';
    }
    static boolean isCloseBracket(char c){
        return c == ')' || c == '}' || c == ']';
    }
    static boolean areNotCompatible(char a, char b){
        return (a == '(' && b!=')') || (a == '{' && b!='}') || (a == '[' && b!=']');
    }
}
