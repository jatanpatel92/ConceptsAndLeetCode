import java.util.Arrays;

public class AverageOfKContiguousElements {

	public static void main(String[] args) {
		int[] arr = {1, 3, 2, 6, -1, 4, 1, 8, 2};
		int k = 5;
		System.out.println("Average of K contiguous elements: "+Arrays.toString(avgK(arr, k)));
	}
	public static double[] avgK(int[] arr, int k) {
		int left = 0;
		int right = 0;
		int n = arr.length;
		int sum = 0;
		double[] result = new double[n-k+1];
		while(right<n) {
			sum+=arr[right];
			if(right-left>=k-1) {
				result[left] = (double) sum/k;
				sum-=arr[left];
				left++;
			}
			right++;
		}
		return result;
	}
}
