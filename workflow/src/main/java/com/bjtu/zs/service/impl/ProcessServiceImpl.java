package com.bjtu.zs.service.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjtu.zs.service.ProcessService;

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

	@Override
	public void getTodo() throws Exception {
		//TODO 获取待办事务
	}

	@Override
	public void submitParameter(Map<String,Object> param, String id) throws Exception {
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
