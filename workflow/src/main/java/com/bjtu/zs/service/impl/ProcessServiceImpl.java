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
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.impl.form.LongFormType;
import org.activiti.engine.impl.form.StringFormType;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjtu.zs.pojo.Hasdo;
import com.bjtu.zs.pojo.ProcInstance;
import com.bjtu.zs.pojo.Todo;
import com.bjtu.zs.service.ProcessService;
import com.bjtu.zs.vo.ProcessInstanceQueryVo;
import com.bjtu.zs.vo.TaskQueryVo;

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
	public List<Todo> getTodo(String userName, TaskQueryVo taskQueryVo) throws Exception {

		TaskQuery taskQuery = taskService.createTaskQuery().orderByTaskCreateTime().taskAssignee(userName).asc();

		if (taskQueryVo != null) {
			String candidateUser = taskQueryVo.getCandidateUser();
			if (StringUtils.isNotBlank(candidateUser)) {
				taskQuery = taskQuery.taskCandidateUser(candidateUser);
			}
			String taskName = taskQueryVo.getTaskName();
			if (StringUtils.isNotBlank(taskName)) {
				taskQuery = taskQuery.taskNameLike(taskName);
			}
			Date startDate = taskQueryVo.getStartDate();
			if (startDate != null) {
				taskQuery = taskQuery.dueAfter(startDate);
			}
			Date endDate = taskQueryVo.getEndDate();
			if (endDate != null) {
				taskQuery = taskQuery.dueBefore(endDate);
			}
		}
		List<Task> tasks = taskQuery.listPage(taskQueryVo.getStart(), taskQueryVo.getLimit());
		List<Todo> myTodoList = new ArrayList<Todo>();
		if (tasks.isEmpty()) {
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

	@Override
	public List<ProcInstance> getProcessInstanceByParam(ProcessInstanceQueryVo processInstanceQueryVo)throws Exception {
		
		HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceStartTime().desc();
		if (processInstanceQueryVo != null) {
			//根据流程开始时间筛选
			Date startDate = processInstanceQueryVo.getStartDate();
			if (startDate != null) {
				historicProcessInstanceQuery = historicProcessInstanceQuery.startedAfter(startDate);
			}
			Date endDate = processInstanceQueryVo.getEndDate();
			if (endDate != null) {
				historicProcessInstanceQuery = historicProcessInstanceQuery.startedBefore(endDate);
			}
			//根据流程的名称来筛选
			String procInstanceName = processInstanceQueryVo.getProcesssInstanceName();
			if(procInstanceName != null){
				historicProcessInstanceQuery= historicProcessInstanceQuery.processInstanceNameLike(procInstanceName);
			}
			//根据是否结束来筛选
			boolean isFinished = processInstanceQueryVo.isFinished();
			if(isFinished){
				historicProcessInstanceQuery = historicProcessInstanceQuery.finished();
			}else{
				historicProcessInstanceQuery = historicProcessInstanceQuery.unfinished();
			}
			
		}
		List<HistoricProcessInstance> historicProcessInstances = historicProcessInstanceQuery.listPage(processInstanceQueryVo.getStart(), processInstanceQueryVo.getLimit());
		List<ProcInstance> procInsList = new ArrayList<ProcInstance>();
		if (historicProcessInstances.isEmpty()) {
			return procInsList;
		}
		ProcInstance procIns = new ProcInstance();
		for (HistoricProcessInstance historicProcessInstance : historicProcessInstances) {
			procIns.setId(historicProcessInstance.getId());
			procIns.setStartDate(historicProcessInstance.getStartTime());
			procIns.setDescription(historicProcessInstance.getDescription());
			procIns.setDurationInMillis(historicProcessInstance.getDurationInMillis());
			
			procInsList.add(procIns);
		}

		return procInsList;
	}

}
