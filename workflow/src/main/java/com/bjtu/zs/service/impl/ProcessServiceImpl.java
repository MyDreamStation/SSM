package com.bjtu.zs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.form.LongFormType;
import org.activiti.engine.impl.form.StringFormType;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjtu.zs.service.ProcessService;
import com.bjtu.zs.vo.TaskQueryVo;
import com.bjtu.zs.vo.Todo;

@Service
public class ProcessServiceImpl implements ProcessService {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private FormService formService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private HistoryService historyService;

	@Override
	public String startProcessByKey(String key) throws Exception {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key);

		String id = processInstance.getId();

		return id;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Todo> getTodo(String userName,TaskQueryVo taskQueryVo) throws Exception {
		// TODO 获取待办事务
		// 获取当前时间
		Date now = new Date();
		// 根据当前用户查询过期时间为当前时间之后的任务
		TaskQuery taskQuery = taskService.createTaskQuery().orderByTaskCreateTime().taskAssignee(userName).asc();
		
		if (taskQueryVo != null) {  
            String candidateUser = taskQueryVo.getCandidateUser();  
            if (StringUtils.isNotBlank(candidateUser))  {
            	taskQuery = taskQuery.taskCandidateUser(candidateUser);  
            }
            String taskName = taskQueryVo.getTaskName();
            if(StringUtils.isNotBlank(taskName)){
            	taskQuery = taskQuery.taskNameLike(taskName);
            }
            Date startDate = taskQueryVo.getStartDate();
            if(StringUtils.isNotBlank(startDate.toString())){
            	taskQuery = taskQuery.dueAfter(startDate);
            }
            Date endDate = taskQueryVo.getEndDate();
            if(StringUtils.isNotBlank(endDate.toString())){
            	taskQuery = taskQuery.dueBefore(endDate);
            }
        }
		List<Task> tasks=taskQuery.listPage(taskQueryVo.getStart(), taskQueryVo.getLimit());
		List<Todo> myTodoList = new ArrayList<Todo>();
		if(tasks.isEmpty()){
			return myTodoList;
		}
		Todo myTodo = new Todo();
		for (Task task : tasks) {
			myTodo.setAssign(userName);
			myTodo.setTaskId(task.getId());
			myTodo.setOwner(task.getOwner());
			myTodo.setTaskName(task.getName());
			myTodo.setCreateTime(task.getCreateTime());
			myTodo.setDueDate(task.getDueDate());
			myTodo.setDescription(task.getDescription());
			
			myTodoList.add(myTodo);
		}

		return myTodoList;
	}

	@Override
	public void submitParameter(Map<String, Object> param, String id) throws Exception {
		ProcessInstance processInstance = getProcessInstanceById(id);

		if (null != processInstance && !processInstance.isEnded()) {
			List<Task> tasks = taskService.createTaskQuery().list();

			for (Task task : tasks) {

				// 保存要提交的参数
				Map<String, Object> variables = new HashMap<>();

				String taskId = task.getId();

				TaskFormData taskFormData = formService.getTaskFormData(taskId);
				List<FormProperty> formProperties = taskFormData.getFormProperties();

				for (FormProperty formProperty : formProperties) {
					String formId = formProperty.getId();
					String value = formProperty.getValue();
					// 判断参数的数据类型
					if (StringFormType.class.isInstance(formProperty.getType())) {
						String temp = (String) param.get(formId);
						variables.put(formId, temp);
					} else if (LongFormType.class.isInstance(formProperty.getType())) {
						String s = (String) param.get(formId);
						long temp = Long.parseLong(s);
						variables.put(formId, temp);
					}
				}

				taskService.complete(taskId, variables);
			}
		}
	}

	@Override
	public ProcessInstance getProcessInstanceById(String id) throws Exception {
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(id)
				.singleResult();
		return processInstance;
	}

	@Override
	public String startProcessByKeyWithVariables(String key, Map<String, Object> variables) throws Exception {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, variables);

		String id = processInstance.getId();

		return id;
	}

}
