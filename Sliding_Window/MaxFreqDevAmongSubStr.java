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
