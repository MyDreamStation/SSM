package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.form.LongFormType;
import org.activiti.engine.impl.form.StringFormType;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class ComplicateTaskDemo {

	public static void main(String[] args) {
		// 获取默认的流程引擎
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

		// 获取流程引擎相关服务
		RepositoryService repositoryService = engine.getRepositoryService();
		RuntimeService runtimeService = engine.getRuntimeService();
		FormService formService = engine.getFormService();
		TaskService taskService = engine.getTaskService();
		HistoryService historyService = engine.getHistoryService();

		// 部署流程定义
		Deployment deploy = repositoryService.createDeployment().addClasspathResource("ifTimeOutThenMail.bpmn")
				.deploy();

		// 获取流程定义
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.deploymentId(deploy.getId()).singleResult();

		// 启动流程
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("ifTimeOutThenMail");

		
		//获取流程实例Id
		String processInstanceId = processInstance.getId();
		
		//流程实例信息
		System.out.println(processInstance.toString());

		// 定义标准输入
		Scanner in = new Scanner(System.in);
		
		// 跟进流程
		while (null != processInstance && !processInstance.isEnded()) {

			// 获取当前流程中的所有任务列表
			List<Task> tasks = taskService.createTaskQuery().list();

			// 输出列表中的任务数
			System.out.println("当前流程一共有：" + tasks.size() + "个流程");

			// 遍历任务
			for (Task task : tasks) {
				// 保存要提交的参数
				Map<String, Object> variables = new HashMap<>();

				// 获取任务的信息
				String string = task.toString();
				System.out.println("任务信息：" + string);

				// 获取任务名称
				String name = task.getName();
				System.out.println("任务名称：" + name);

				// 获取任务id
				String id = task.getId();
				System.out.println("任务id：" + id);

				// 获取任务描述
				String description = task.getDescription();
				System.out.println("任务描述：" + description);

				// 获取当前任务要提交的表单信息
				TaskFormData taskFormData = formService.getTaskFormData(id);

				// 输出表单信息
				System.out.println("要提交的表单信息：" + taskFormData.toString());

				// 获取要提交的属性列表
				List<FormProperty> formProperties = taskFormData.getFormProperties();

				// 输出属性列表
				System.out.println("表单中属性列表" + formProperties.toString());

				
				// 遍历属性列表
				for (FormProperty formProperty : formProperties) {
					String formId = formProperty.getId();
					String value = formProperty.getValue();
					System.out.println(formId + ":(" + value + ")");
					// 判断参数的数据类型
					if (StringFormType.class.isInstance(formProperty.getType())) {
						String temp = in.nextLine();
						variables.put(formId, temp);
					} else if (LongFormType.class.isInstance(formProperty.getType())) {
						String s=in.nextLine();
						long temp = Long.parseLong(s);
						variables.put(formId, temp);
					}
				}
				
				
				
				// 提交该任务，附带参数
				taskService.complete(id, variables);

				HistoricActivityInstance endActivityInstance = null;
				
				List<HistoricActivityInstance> historicActivityInstances = historyService
						.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).finished()
						.orderByHistoricActivityInstanceEndTime().asc().list();
				for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
					// 输出历史流程信息
					String temp2 = historicActivityInstance.toString();
					System.out.println("此条历史流程信息为：" + temp2);
					if (historicActivityInstance.getActivityType() == "startEvent") {
						System.out.println("BEGIN " + processDefinition.getName() + " ["
								+ processInstance.getProcessDefinitionKey() + "] " + historicActivityInstance.getStartTime());
					}
					if (historicActivityInstance.getActivityType() == "endEvent") {
						// Handle edge case where end step happens so fast that
						// the end step
						// and previous step(s) are sorted the same. So, cache
						// the end step
						// and display it last to represent the logical
						// sequence.
						endActivityInstance = historicActivityInstance;
					} else {
						System.out.println("-- " + historicActivityInstance.getActivityName() + " ["
								+ historicActivityInstance.getActivityId() + "] "
								+ historicActivityInstance.getAssignee() + historicActivityInstance.toString()
								+ historicActivityInstance.getDurationInMillis() + " ms");
					}
				}
				if (endActivityInstance != null) {
					System.out.println(
							"-- " + endActivityInstance.getActivityName() + " [" + endActivityInstance.getActivityId()
									+ "] " + endActivityInstance.getDurationInMillis() + " ms");
					System.out.println("COMPLETE " + processDefinition.getName() + " ["
							+ processInstance.getProcessDefinitionKey() + "] " + endActivityInstance.getEndTime());
				}
			}
			processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId())
					.singleResult();
		}
		
		in.close();
	}
}
