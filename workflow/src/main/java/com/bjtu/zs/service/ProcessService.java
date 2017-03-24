package com.bjtu.zs.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;

import com.bjtu.zs.pojo.Hasdo;
import com.bjtu.zs.pojo.ProcInstance;
import com.bjtu.zs.pojo.Todo;
import com.bjtu.zs.vo.ProcessInstanceQueryVo;
import com.bjtu.zs.vo.TaskQueryVo;

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
	 * 根据当前用户的用户名和查询参数查询待办事务
	 * 
	 * @param userName 用户名
	 * @param taskQueryVo 查询参数
	 * @return
	 * @throws Exception
	 */
	public List<Todo> getTodo(String userName, TaskQueryVo taskQueryVo) throws Exception;

	/**
	 * 提交任务中的参数
	 * 
	 * @param param
	 *            参数
	 * @param id
	 * @throws Exception
	 */
	public void submitParameter(Map<String, Object> param, String id) throws Exception;

	/**
	 * 根据流程实例的id获取流程实例
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ProcessInstance getProcessInstanceById(String id) throws Exception;

	/**
	 * 根据参数查询流程实例
	 * 
	 * @param processInstanceQueryVo
	 *            流程实例查询参数Vo
	 * @return
	 * @throws Exception
	 */
	public List<ProcInstance> getProcessInstanceByParam(ProcessInstanceQueryVo processInstanceQueryVo) throws Exception;

	/**
	 * 
	 */

}
