package com.bjtu.zs.vo;

import java.util.Date;

/**
 * @ClassName TaskQueryVo
 * @Description 查询已办事项的参数实体类
 * @author 曾双  631710518@qq.com
 * @Date 2017年3月17日14:13:57
 *
 */
public class TaskQueryVo {

	//任务名称
	private String taskName;
	
	//开始时间
	private Date startDate;
	
	//结束时间
	private Date endDate;
	
	//任务负责人
	private String assign;
	
	//任务所有者
	private String owner;
	
	//分页
	private int start=0;
	
	private int limit=15;

	private String candidateUser;
	
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getAssign() {
		return assign;
	}

	public void setAssign(String assign) {
		this.assign = assign;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getCandidateUser() {
		return candidateUser;
	}

	public void setCandidateUser(String candidateUser) {
		this.candidateUser = candidateUser;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	
}
