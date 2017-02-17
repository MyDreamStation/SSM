package com.activiti.test;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

public class TestActiviti {

	public static void main(String[] args) {

		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		RuntimeService runtimeService = processEngine.getRuntimeService();
//		FormService formService = processEngine.getFormService();
		TaskService taskService = processEngine.getTaskService();

		repositoryService.createDeployment().addClasspathResource("MyProcess.bpmn").deploy();

		runtimeService.startProcessInstanceByKey("myProcess");

		List<Task> tasks = taskService.createTaskQuery().list();

		System.out.println("一共有" + tasks.size() + "个任务");

		for (Task task : tasks) {
			System.out.println("任务id：" + task.getId());
			System.out.println("任务名称：" + task.getName());
			System.out.println("任务描述：" + task.getDescription());
			System.out.println("任务：" + task.toString());
		}

	}
}
