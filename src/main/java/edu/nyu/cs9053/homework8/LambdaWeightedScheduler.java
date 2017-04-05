package edu.nyu.cs9053.homework8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author fafnir21
 * Date: 05/03/17
 * Time: 3:04 PM
 * Use a bottom-up DP method to implement LambdaWeightedScheduler class
 * Optimal value is in optimal[] array, optimal[n-1] is the final n step
 * Result is added into a job list
 */

public class LambdaWeightedScheduler {
	
	private int[] maxCompatibleIndexBefore;
	private static double[] optimal;
	private List<Job> resultList;
	private final List<Job> jobList;
	private final int n;
	
	public LambdaWeightedScheduler(List<Job> jobList) {
		this.jobList = jobList;
		this.n = jobList.size();
		init();
		jobScheduler();
	}
	
	private void init() {
		resultList = new ArrayList<Job>();
		maxCompatibleIndexBefore = new int[n + 1];
		optimal = new double[n + 1];
	}
	
	public void jobScheduler() {
		if(jobList == null || jobList.size() == 0)
			return;
		sortJobList(jobList);
		computeMaxCompatibleIndexBefore();
		computeOptimal();
		findSolution(n);
		Collections.reverse(resultList);
	}
	
	private void sortJobList(List<Job> jobList) {//Sort jobs according to finish time
		Collections.sort(jobList, new Comparator<Job>() {
			@Override
			public int compare(Job j1, Job j2) {
				return j1.getFinishTime().compareTo(j2.getFinishTime());
			}
		});
	}
	
	private void computeMaxCompatibleIndexBefore() {
		for(int i = 1; i <= n; i++)
			maxCompatibleIndexBefore[i] = 0;
		for(int i = 2; i <= n; i++) {
			for(int j = i - 1; j > 0; j--) {
				if(jobList.get(i - 1).getStartTime() >= jobList.get(j - 1).getFinishTime()) {
					maxCompatibleIndexBefore[i] = j;
					break;
				}
			}
		}
	}
	
	private void computeOptimal() {
		optimal[0] = 0d;
		for(int i = 1; i <= n; i++) {
			optimal[i] = Math.max(jobList.get(i - 1).getWeight() 
					+ optimal[maxCompatibleIndexBefore[i]], 
					optimal[i - 1]);
		}
	}
	
	private void findSolution(int n) {
		if(n == 0) {
			return;
		} else if(jobList.get(n - 1).getWeight() 
				+ optimal[maxCompatibleIndexBefore[n]] 
						> optimal[n - 1]) {
			resultList.add(jobList.get(n - 1));
			findSolution(maxCompatibleIndexBefore[n]);
		} else {
			findSolution(n - 1);
		}
	}
	
	public List<Job> getSolution() {
		return resultList;
	}
	
	public static void main(String[] args) {
		List<Job> jobList = new ArrayList<Job>();
		Job j1 = new Job("job1", "", 1L, 2L, 1d);
		Job j2 = new Job("job2", "", 2L, 5L, 10d);
		Job j3 = new Job("job3", "", 3L, 4L, 2d);
		Job j4 = new Job("job4", "", 4L, 10L, 14d);
		Job j5 = new Job("job5", "", 5L, 7L, 4d);
		Job j6 = new Job("job6", "", 9L, 10L, 1d);
		jobList.add(j1);
		jobList.add(j2);
		jobList.add(j3);
		jobList.add(j4);
		jobList.add(j5);
		jobList.add(j6);
		LambdaWeightedScheduler lws = new LambdaWeightedScheduler(jobList);
		List<Job> resultList = lws.getSolution();
		for(Job job : resultList) {
			System.out.printf("%s%n", job.getName());
		}
		System.out.printf("The total value of optimal sublist of jobs is %f%n", optimal[jobList.size() - 1]);
	}
}
