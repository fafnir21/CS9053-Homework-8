package edu.nyu.cs9053.homework8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author fafnir21
 * Date: 04/03/17
 * Time: 6:04 PM
 * Use Greedy method to implement LambdaScheduler class
 * Result is added into a job list
 */

public class LambdaScheduler extends AbstractLambdaScheduler{
	
	public LambdaScheduler(List<Job> jobList) {
		super(jobList);
		init();
		jobScheduler();
	}
	
	public void jobScheduler() {
		if(jobList == null || jobList.size() == 0)
			return;
		sortJobList(jobList);
		findSolution();
	}
	
	private void init() {
		resultList = new ArrayList<Job>();
	}
	
	//Sort jobs according to finish time
	private void sortJobList(List<Job> jobList) {
		Collections.sort(jobList, new Comparator<Job>() {
			@Override
			public int compare(Job j1, Job j2) {
				return j1.getFinishTime().compareTo(j2.getFinishTime());
			}
		});
	}
	
	//Scan the list, use greedy method to find the max number of compatible jobs
	private void findSolution() {
		Job pre = jobList.get(0);
		resultList.add(pre);
		for(int i = 1; i < n; i++) {
			Job cur = jobList.get(i);
			if(cur.getStartTime() >= pre.getFinishTime()) {
				resultList.add(cur);
				pre = cur;
			}
		}
	}
	
	public static void main(String[] args) {
		List<Job> jobList = new ArrayList<Job>();
		Job j1 = new Job("job1", "", 1L, 2L, 0d);
		Job j2 = new Job("job2", "", 2L, 5L, 0d);
		Job j3 = new Job("job3", "", 3L, 4L, 0d);
		Job j4 = new Job("job4", "", 4L, 10L, 0d);
		jobList.add(j1);
		jobList.add(j2);
		jobList.add(j3);
		jobList.add(j4);
		LambdaScheduler ls = new LambdaScheduler(jobList);
		List<Job> resultList = ls.getSolution();
		for(Job job : resultList) {
			System.out.printf("%s%n", job.getName());
		}
		System.out.printf("The total number of jobs is %d%n", resultList.size());
	}
}
