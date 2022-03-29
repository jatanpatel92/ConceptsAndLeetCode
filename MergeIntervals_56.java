/*
56. Merge Intervals
Medium

Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.



Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.



Constraints:

    1 <= intervals.length <= 104
    intervals[i].length == 2
    0 <= starti <= endi <= 104


*/
class Solution {
    public int[][] merge(int[][] intervals) {
        List<Interval> listOfIntervals = new LinkedList<>();
        List<Interval> mergedIntervals = new LinkedList<>();
        for(int[] interval:intervals){
            listOfIntervals.add(new Interval(interval[0], interval[1]));
        }
        Collections.sort(listOfIntervals, (a, b) -> Integer.compare(a.start, b.start));
        Iterator<Interval> iterator = listOfIntervals.iterator();
        Interval current = iterator.next();
        int start = current.start;
        int end = current.end;
        while(iterator.hasNext()){
            current = iterator.next();
            if(end>=current.start){ // Overlapping, change end to max of current and next
                end = Math.max(current.end, end);
            } else { // not overlapping, add current to the merged list
                mergedIntervals.add(new Interval(start, end));
                start = current.start;
                end = current.end;
            }
        }
        mergedIntervals.add(new Interval(start, end));
        int[][] result = new int[mergedIntervals.size()][2];
        int count = 0;
        for(Interval interval : mergedIntervals){
            result[count][0] = interval.start;
            result[count][1] = interval.end;
            count++;
        }
        return result;
    }
    class Interval{
        int start;
        int end;
        public Interval(int s, int e){
            start = s;
            end  = e;
        }
        @Override
        public String toString(){
            return "("+start+", "+end+")";
        }
    }
}
