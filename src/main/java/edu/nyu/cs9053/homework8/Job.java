package edu.nyu.cs9053.homework8;

public class Job {
	private final String name;
	private final String description;
	private final Long startTime;
	private final Long finishTime;
	private final Double weight;
	
	public Job(String name, String description, Long startTime, Long finishTime, Double weight) {
		this.name = name;
		this.description = description;
		this.startTime = startTime;
		this.finishTime = finishTime;
		this.weight = weight;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Long getStartTime() {
		return startTime;
	}
	
	public Long getFinishTime() {
		return finishTime;
	}
	
	public Double getWeight() {
		return weight;
	}
}
