import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * An infectious disease is spreading in a city. The Mayor of the city wants to take strict action to provide proper prevention from the disease.

There are N persons in the city who stay side by side in increasing order of their index, that is, the ith person has the (i - 1)th and (i + 1)th person staying next to him or her.

Initially, it is found that among N persons, exactly M are infected with the disease. Further, it was found the disease is contagious, and it spreads from an infected person to his or her neighbours. It means that if the ith person is infected, then the (i - 1)th person and (i + 1)th person staying just next to the ith person can get infected from him or her.

Also, it is observed that people who are not infected(people apart from initial M infected people) get infected in a particular sequence. This sequence provides the order in which people(who are not infected) get infected with the increasing span of time. It may be possible that the xth person gets infected only after the yth person and this defines a particular order among them, that it, y must always be placed before x in the sequence.
For example, N = 6 and person 3 and 5 are infected. Here, person 2 gets affected before person 1. Similarly, person 2 and 4 can be placed with any relative order as they can get infected from 3 directly.

The Mayor wants you to determine the number of unique sequences in which the other persons can get infected. Since the answer can be large, print it modulo 1000000007 (10^9 + 7).

Note: Two sequences are said to be different if there exists at least one position among sequences where a person present is different.

Input format

The first line contains two space separated integers N and M
The next line contains M space-separated integers denoting integers denoting infected people.

Output format
Print a single integer denoting the number of unique sequences.
 */
public class InfectiousDiseaseInCity {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println(" Enter N of people and M infected people: ");
		String nmStr = sc.nextLine();
		String[] line1 = nmStr.split(" ");
		System.out.println(" Enter M infected people : ");
		String mStr = sc.nextLine();
		String[] line2 = mStr.split(" ");
		sc.close();
		int n = Integer.parseInt(line1[0]);
		int m = Integer.parseInt(line1[1]);
		int[] sources = new int[m];
		int index = 0;
		for(String source:line2) {
			sources[index++] = Integer.parseInt(source); 
		}
		System.out.println(findWays(n, sources));
		

	}
	static Map<String, Integer> memo = new HashMap<>();
	private static int findWays(int n, int[] sources) {
		int m = sources.length;
		if(n == m) return 1;
		int[] people = new int[n];
		for(int source: sources) {
			people[source-1] = 1;
		}
		StringBuffer sb = new StringBuffer();
		StringBuffer endStateBuffer = new StringBuffer();
		for(int p: people) {
			sb.append(p);
			endStateBuffer.append('1');
		}
		String initialState = sb.toString();
		String endState = endStateBuffer.toString();
		memo.put(endState, 1);
		return countWays(initialState);
	}

	private static int countWays(String state) {
		if(memo.containsKey(state))
			return memo.get(state);
		int ways = 0;
		for(int i=0; i<state.length(); i++) {
			if(state.charAt(i) == '0') {
				if((i>0 && state.charAt(i-1) == '1') || (i<state.length()-1 && state.charAt(i+1) == '1')) {
					String newState = state.substring(0, i)+"1"+state.substring(i+1, state.length());
					ways+=countWays(newState);
				}
			}
		}
		memo.put(state, ways);
		return memo.get(state);
	}

}
