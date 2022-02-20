
public class MaxSumKContiguousElements {

	public static void main(String[] args) {
		int[] arr = {2, 1, 5, 1, 3, 2};
		int k = 3;

		System.out.println("Max Sum of K contiguous elements: "+maxSum(arr, k));
		System.out.println("Max Sum of K contiguous elements: "+maxSum(new int[]{2,3,4,1,5}, 2));
	}
	public static int maxSum(int[] arr, int k) {
		int left = 0;
		int right = 0;
		int n = arr.length;
		int sum = 0;
		int result = sum;
		while(right<n) {
			sum+=arr[right];
			if(right-left==k-1) {
				result = result<sum?sum:result;
				sum-=arr[left];
				left++;
			}
			right++;
		}
		return result;
	}

}
