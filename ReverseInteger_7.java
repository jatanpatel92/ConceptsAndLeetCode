/*
7. Reverse Integer
Easy

Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).



Example 1:

Input: x = 123
Output: 321

Example 2:

Input: x = -123
Output: -321

Example 3:

Input: x = 120
Output: 21

Example 4:

Input: x = 0
Output: 0



Constraints:

  -231 <= x <= 231 - 1


*/


class Solution {
    public int reverse(int x) {
        if(x<Integer.MIN_VALUE || x>Integer.MAX_VALUE || x == 0)
            return 0;
        int reverse = 0;
        int num = x;
        while(num!=0){
            int mod = num%10;
            boolean edgeCases = (reverse > Integer.MAX_VALUE/10) ||
                                (reverse < Integer.MIN_VALUE/10) ||
                                (reverse == Integer.MAX_VALUE/10 && mod>7) ||
                                (reverse == Integer.MIN_VALUE/10 && mod<-8);
            if(edgeCases) return 0;
            reverse = reverse * 10 + mod;
            num = num/10;
        }
        return reverse;
    }
}
