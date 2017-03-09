package com.bjtu.zs.service;

import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;

/**
 * @ClassName ProcessService
 * @Description 流程服务接口
 * @author 曾双 631710518@qq.com
 * @Date 2017年3月2日15:39:03
 */
public interface ProcessService {

	/**
	 * 通过流程定义的id启动流程
	 * 
	 * @param key
	 *            程定义中的id
	 * @throws Exception
	 */
	public String startProcessByKey(String key) throws Exception;

	/**
	 * 
	 */
	public void getTodo() throws Exception;

	/**
	 * 提交任务中的参数
	 * 
	 * @param param 参数
	 * @param id 
	 * @throws Exception
	 */
	public void submitParameter(Map param, String id) throws Exception;
	
	
	public ProcessInstance getProcessInstanceById(String id) throws Exception;

}
