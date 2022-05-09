import java.util.*;
public class MinDistanceInMNGroups {
	public static void main(String[] args) {
		List<Integer> grp1 = Arrays.asList(21, 1, 150, 289, -321);
		List<Integer> grp2 = Arrays.asList(160, 3, 30);
		List<Integer> grp3 = Arrays.asList(170, 22, 6, 7);
		List<List<Integer>> list = new LinkedList<>();
		list.add(grp1);
		list.add(grp2);
		list.add(grp3);
		System.out.println(getShortestDistance(list));
	}
	public static List<Integer> getShortestDistance(List<List<Integer>> groups){
		List<Integer> result = new LinkedList<>();
		for(List<Integer> group:groups) {
			Collections.sort(group);
		}
		System.out.println(groups);
		int minDistance = Integer.MAX_VALUE;
		for(int first: groups.get(0)) {
			List<Integer> tuple = new LinkedList<>();
			tuple.add(first);
			int closestElement = first;
			int localDistance = 0;
			for(int i=1; i<groups.size(); i++) {
				int next = binarySearch(groups.get(i), 0, groups.get(i).size()-1, closestElement);
				localDistance+=Math.abs(closestElement-next);
				closestElement = next;
				tuple.add(closestElement);
			}
			if(minDistance>localDistance) {
				minDistance = localDistance;
				result = tuple;
			}
		}
		return result;

	}
	private static int binarySearch(List<Integer> list, int left, int right, int x) {
		if(left>=right)
			return list.get(right);
		int mid = left + (right-left)/2;
		if(list.get(mid) == x)
			return list.get(mid);
		if(list.get(mid) < x)
			return binarySearch(list, mid+1, right, x);
		return binarySearch(list, left, mid-1, x);
	}
}
