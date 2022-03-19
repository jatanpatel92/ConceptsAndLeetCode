/*
 * Given a string, find maximum possible frequency deviation among all substrings.
 * Frequency deviation is the difference between the most and the least repeated character frequencies.
 */
import java.util.HashMap;
import java.util.Map;

public class MaxFreqDevAmongSubStr {

	public static void main(String[] args) {

		System.out.println(maxFerqDevAmongSubStr("aabb"));
		System.out.println(maxFerqDevAmongSubStr("bbacccc"));
		System.out.println(maxFerqDevAmongSubStr("bbaccc"));
		System.out.println(maxFerqDevAmongSubStr("bbacc"));
		System.out.println(maxFerqDevAmongSubStr("bbbacc"));
		System.out.println(maxFerqDevAmongSubStr("abbbaccc"));
		System.out.println(maxFerqDevAmongSubStr("abbbcacbcdce"));
		System.out.println(maxFerqDevAmongSubStr("abacadaeafcccca"));
		System.out.println(maxFerqDevAmongSubStr("aacaaacccca"));
		System.out.println(maxFerqDevAmongSubStr("abbaaabaabaaaaaaaaaaaaa"));
		System.out.println(maxFerqDevAmongSubStr("accaca"));
		System.out.println(maxFerqDevAmongSubStr("aaccaaacccc"));
		System.out.println(maxFerqDevAmongSubStr("aacaaacccc"));
		System.out.println(maxFerqDevAmongSubStr("bbbabbbaaaaaaaaaaaa"));
	}
	// Sliding Window
	private static int maxFerqDevAmongSubStr(String str) {
		System.out.println("String: "+str);
		if(str == null || str.length()<=1) return 0;
		int right = 0;
		int left = 0;
		int n = str.length();
		Map<Character, Integer> frequencyMap = new HashMap<>();
		int maxFreq = Integer.MIN_VALUE;
		int minFreq = Integer.MAX_VALUE;
		int maxDev = Integer.MIN_VALUE;
		String subStr = "";
		while(right<n) {
			char current = str.charAt(right);
			frequencyMap.put(current, frequencyMap.getOrDefault(current, 0)+1);
			maxFreq = getMaxFreq(frequencyMap);
			minFreq = getMinFreq(frequencyMap);
			maxDev = Math.max(maxDev, Math.abs(maxFreq-minFreq));
			if(maxDev>Math.abs(maxFreq-minFreq)) { // Shrink window
				char removeChar = str.charAt(left);
				frequencyMap.put(removeChar, frequencyMap.get(removeChar)-1);
				if(frequencyMap.get(removeChar)==0)
					frequencyMap.remove(removeChar);
				left++;
			}
			else {
				subStr = str.substring(left, right+1);
			}
			right++;
		}
		System.out.println("Max Deviation Sub Str: "+ subStr);
		return maxDev;
	}

	private static int getMinFreq(Map<Character, Integer> frequencyMap) {
		int minFreq = Integer.MAX_VALUE;
		for(Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
			if(entry.getValue()<minFreq) {
				minFreq = entry.getValue();
			}
		}
		return minFreq;
	}

	private static int getMaxFreq(Map<Character, Integer> frequencyMap) {
		int maxFreq = Integer.MIN_VALUE;
		for(Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
			if(entry.getValue()>maxFreq) {
				maxFreq = entry.getValue();
			}
		}
		return maxFreq;
	}
}
