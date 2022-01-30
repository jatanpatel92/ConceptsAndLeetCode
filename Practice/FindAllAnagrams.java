import java.util.Scanner;

public class FindAllAnagrams {

	public static void main(String[] args) {
		String str = "abc";
		System.out.println("Sample String "+str+" anagrams:");
		generateAnagrams(str, 0, str.length()-1);
		Scanner sc = new Scanner(System.in);
		System.out.println("User input: ");
		str = sc.next();
		System.out.println("User String "+str+" anagrams:");
		generateAnagrams(str, 0, str.length()-1);
		sc.close();
	}

	private static void generateAnagrams(String str, int start, int end) {
		if(start == end) {
			System.out.println(str);
			return;
		}
		for(int i = start; i<=end; i++) {
			str = swap(str, start, i);
			generateAnagrams(str, start+1, end);
			str = swap(str, start, i);
		}
	}
	
	private static String swap(String str, int i, int j) {
		char[] arr = str.toCharArray();
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		return String.valueOf(arr);
	}
}
/*
 * Output:
Sample String abc anagrams:
abc
acb
bac
bca
cba
cab
User input: 
abcd
User String abcd anagrams:
abcd
abdc
acbd
acdb
adcb
adbc
bacd
badc
bcad
bcda
bdca
bdac
cbad
cbda
cabd
cadb
cdab
cdba
dbca
dbac
dcba
dcab
dacb
dabc
 
*/