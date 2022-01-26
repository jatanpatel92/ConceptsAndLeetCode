class Solution {
    static Map<Character, String> map;
    public List<String> letterCombinations(String digits) {
        initializeMap();
        if(digits == null || digits.isEmpty()) return new LinkedList<String>();
        List<String> combos = new LinkedList<>();
        getCombos(digits, combos, "", 0);
        return combos;
    }
    public void initializeMap(){
        map = new HashMap<Character, String>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
    }
    public void getCombos(String digits, List<String> combos, String current, int index){
        if(current.length() == digits.length()){
            combos.add(current);
        } else {
            String letters = map.get(digits.charAt(index));
            for(int i=0; i<letters.length(); i++){
                getCombos(digits, combos, current.concat(letters.substring(i, i+1)), index+1);
            }
        }
    }
}
