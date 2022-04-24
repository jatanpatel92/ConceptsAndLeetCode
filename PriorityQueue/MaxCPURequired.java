import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class MaxCPURequired {

	public static void main(String[] args) {
		/*
		 * Given a set of jobs array and max number of cpus, where each job object contains 3 props {starttime,duration,numberofCpusNeeded},
		 * write a function which returns true if the jobs can be executed with the given max cpus else return false even if one job can't be executed?
		 */
		int[][] jobsArr = new int[3][3];
		jobsArr[0] = new int[]{1, 3, 2};
		jobsArr[1] = new int[]{2, 3, 1};
		jobsArr[2] = new int[]{5, 3, 1};
		List<Job> jobs = new LinkedList<>();
		for(int[] job : jobsArr) {
			jobs.add(new Job(job[0], job[0]+job[1], job[2]));
		}
		System.out.print("Max CPU required to finish the jobs: "+ canExecute(jobs));
	}
	public static int canExecute(List<Job> jobs) {
		System.out.println(jobs);
		Collections.sort(jobs, (a, b)->Integer.compare(a.start, b.start));
		PriorityQueue<Job> pq = new PriorityQueue<>((a, b)->Integer.compare(a.end, b.end));
		int max = 0;
		int load = 0;
		for(Job job:jobs) {
			while(!pq.isEmpty() && pq.peek().start<job.start) {
				load-=pq.poll().cpuRequired;
			}
			pq.offer(job);
			load+=job.cpuRequired;
			max = Math.max(max, load);
		}
		return max;
	}
	static class Job{
		int start;
		int end;
		int cpuRequired;
		public Job(int s, int e, int l) {
			start = s;
			end = e;
			cpuRequired = l;
		}
		@Override
		public String toString() {
			return "["+start+", "+end+", "+cpuRequired+"]";
		}
	}
}
