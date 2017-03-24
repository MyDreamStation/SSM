package com.bjtu.zs.pojo;

import java.util.Date;

/**
 * @ClassName Todo
 * @Description 待办事项Vo
 * @author 曾双 631710518@qq.com
 * @Date 2017年3月17日10:59:45
 */
public class Todo {
	
	//id
	private int id;

	// 任务id
	private String taskId;

	// 任务名称
	private String taskName;

	// 任务负责人
	private String assign;

	// 任务所有者
	private String owner;

	// 任务创建时间
	private Date createTime;

	// 任务过期时间
	private Date dueDate;
	
	//任务描述
	private String description;

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getAssign() {
		return assign;
	}

	public void setAssign(String assign) {
		this.assign = assign;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
