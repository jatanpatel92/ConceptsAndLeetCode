/*
17. Letter Combinations of a Phone Number
Medium

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

Example 2:

Input: digits = ""
Output: []

Example 3:

Input: digits = "2"
Output: ["a","b","c"]



Constraints:

    0 <= digits.length <= 4
    digits[i] is a digit in the range ['2', '9'].


*/
class Solution {
    static Map<String, String> digitToLetterMap;
    public List<String> letterCombinations(String digits) {
        digitToLetterMap = new HashMap<>();
        digitToLetterMap.put("2", "abc");
        digitToLetterMap.put("3", "def");
        digitToLetterMap.put("4", "ghi");
        digitToLetterMap.put("5", "jkl");
        digitToLetterMap.put("6", "mno");
        digitToLetterMap.put("7", "pqrs");
        digitToLetterMap.put("8", "tuv");
        digitToLetterMap.put("9", "wxyz");
        return getCombinations("", digits);
    }
    private List<String> getCombinations(String combo, String digits){
        if(digits == null || digits.isEmpty()) return new ArrayList<>();
        List<String> result = new ArrayList<>();
        addCombinations(combo, digits, result);
        return result;
    }
    private void addCombinations(String combo, String digits, List<String> result){
        if(digits.equals("")){
            result.add(combo);
        }
        else{
            String digit = digits.substring(0, 1);
            String letters = digitToLetterMap.get(digit);
            for(int i=0; i<letters.length(); i++){
                addCombinations(combo+letters.substring(i, i+1), digits.substring(1, digits.length()), result);
            }
        }
    }
}
