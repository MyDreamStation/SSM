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
	 *            流程定义中的id
	 * @return
	 * @throws Exception
	 */
	public String startProcessByKey(String key) throws Exception;

	/**
	 * 通过流程定义的id和流程变量启动流程
	 * 
	 * @param key
	 *            流程定义中的id
	 * @param variables
	 *            流程变量
	 * @return
	 * @throws Exception
	 */
	public String startProcessByKeyWithVariables(String key, Map<String, Object> variables) throws Exception;

	/**
	 * 
	 */
	public void getTodo() throws Exception;

	/**
	 * 提交任务中的参数
	 * 
	 * @param param
	 *            参数
	 * @param id
	 * @throws Exception
	 */
	public void submitParameter(Map<String,Object> param, String id) throws Exception;

	
	/**
	 * 根据流程实例的id获取流程实例
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ProcessInstance getProcessInstanceById(String id) throws Exception;
	
	
	/**
	 * 已办
	 */
	
	
	
	/**
	 * 
	 */

}
