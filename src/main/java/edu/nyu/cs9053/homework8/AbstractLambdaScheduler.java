package edu.nyu.cs9053.homework8;

import java.util.List;

public abstract class AbstractLambdaScheduler implements JobScheduler{

	protected List<Job> resultList;
	protected final List<Job> jobList;
	protected final int n;
	
	public AbstractLambdaScheduler(List<Job> jobList) {
		this.jobList = jobList;
		this.n = jobList != null ? jobList.size() : 0;
	}
	
	@Override
	public List<Job> getSolution() {
		return resultList;
	}
}
