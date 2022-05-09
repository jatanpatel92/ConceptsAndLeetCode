class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {

        int wordLen = word.length();
        int abbrLen = abbr.length();

        int i=0, j =0;

        while( i< wordLen && j < abbrLen) {
            if(word.charAt(i) != abbr.charAt(j)) {
                //only time they have to be different will be if abbr contains a digit.
               if(!Character.isDigit(abbr.charAt(j))) {
                   return false;
               }

                StringBuilder sb = new StringBuilder();
                while(j < abbrLen && Character.isDigit(abbr.charAt(j))) {
                    sb.append(abbr.charAt(j));
                    j++;
                }
                //check for trailing zero
                if(sb.charAt(0) == '0')
                    return false;

                int shiftCount = Integer.parseInt(sb.toString());
                i = i + shiftCount;

            } else {
                 i++;
                 j++;
            }


        }

        return i == wordLen && abbrLen==j;
    }
}
