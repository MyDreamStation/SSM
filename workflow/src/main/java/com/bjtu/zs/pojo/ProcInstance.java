package com.bjtu.zs.pojo;

import java.util.Date;

/**
 * @ClassName ProcInstance
 * @Description 
 * @author Administrator
 *
 */
public class ProcInstance {
	
	//id
	private String id;
	
	//流程实例id
	private String procInstanceId;
	
	//流程实例名称
	private String procInstanceName;
	
	//实例开始时间
	private Date startDate;
	
	//实例结束时间
	private Date endDate;
	
	//是否结束的状态位
	private boolean isFinished;
	
	//流程实例描述
	private String description;

	//持续时间
	private Long durationInMillis;
	
	public String getId() {
		return id;
	}

	public void setId(String string) {
		this.id = string;
	}

	public String getProcInstanceId() {
		return procInstanceId;
	}

	public void setProcInstanceId(String procInstanceId) {
		this.procInstanceId = procInstanceId;
	}

	public String getProcInstanceName() {
		return procInstanceName;
	}

	public void setProcInstanceName(String procInstanceName) {
		this.procInstanceName = procInstanceName;
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

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getDurationInMillis() {
		return durationInMillis;
	}

	public void setDurationInMillis(Long durationInMillis) {
		this.durationInMillis = durationInMillis;
	}
	
	
	
	

}
