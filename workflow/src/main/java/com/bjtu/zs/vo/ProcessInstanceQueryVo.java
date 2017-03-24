package com.bjtu.zs.vo;

import java.util.Date;

/**
 * @ClassName ProcessInstanceQueryVo
 * @Description 用于通过流程信息查询
 * @author Administrator
 * @Date 2017年3月21日16:01:17
 */
public class ProcessInstanceQueryVo {

	//流程实例id
	private String processInstanceId;
	
	//开始时间
	private Date startDate;
	
	//结束时间
	private Date endDate;
	
	//流程实例名
	private String processsInstanceName;
	
	//是否已经结束状态位
	private boolean isFinished;
	
	//分页
	private int start;
	
	private int limit;

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
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

	public String getProcesssInstanceName() {
		return processsInstanceName;
	}

	public void setProcesssInstanceName(String processsInstanceName) {
		this.processsInstanceName = processsInstanceName;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
	
	
	
}
