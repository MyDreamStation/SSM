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

public class TestActiviti {

	public static void main(String[] args) {

		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

		RepositoryService repositoryService = processEngine.getRepositoryService();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		FormService formService = processEngine.getFormService();
		TaskService taskService = processEngine.getTaskService();
		HistoryService historyService = processEngine.getHistoryService();

		Deployment deploy = repositoryService.createDeployment().addClasspathResource("MyProcess.bpmn").deploy();

		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.deploymentId(deploy.getId()).singleResult();

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess");

		/*
		 * for (Task task : tasks) { System.out.println("任务id：" + task.getId());
		 * System.out.println("任务名称：" + task.getName());
		 * System.out.println("任务描述：" + task.getDescription());
		 * System.out.println("任务：" + task.toString()); }
		 */

		Scanner in = new Scanner(System.in);
		while (processInstance != null && !processInstance.isEnded()) {
			List<Task> tasks = taskService.createTaskQuery().list();
			System.out.println("任务个数：" + tasks.size());
			for (Task task : tasks) {
				System.out.println("Processing task:" + task.toString());
				Map<String, Object> map = new HashMap<>();

				String id = task.getId();// 获取任务id
				TaskFormData taskFormData = formService.getTaskFormData(id);// 获取要提交的表单信息
				List<FormProperty> formProperties = taskFormData.getFormProperties();// 获取要提交的信息

				for (FormProperty formProperty : formProperties) {// 判断类型
					if (StringFormType.class.isInstance(formProperty.getType())) {
						System.out.println(formProperty.getId() + ":");
						String temp = in.nextLine();
						map.put(formProperty.getId(), temp);
					} else {
						if (LongFormType.class.isInstance(formProperty.getType())) {
							System.out.println(formProperty.getId() + ":");
							Long temp = in.nextLong();
							map.put(formProperty.getId(), temp);
						}
					}
				}
				taskService.complete(task.getId(), map);
				HistoricActivityInstance endActivity = null;
				List<HistoricActivityInstance> activities = historyService.createHistoricActivityInstanceQuery()
						.processInstanceId(processInstance.getId()).finished().orderByHistoricActivityInstanceEndTime()
						.asc().list();
				for (HistoricActivityInstance activity : activities) {
					if (activity.getActivityType() == "startEvent") {
						System.out.println("BEGIN " + processDefinition.getName() + " ["
								+ processInstance.getProcessDefinitionKey() + "] " + activity.getStartTime());
					}
					if (activity.getActivityType() == "endEvent") {
						// Handle edge case where end step happens so fast that
						// the end step
						// and previous step(s) are sorted the same. So, cache
						// the end step
						// and display it last to represent the logical
						// sequence.
						endActivity = activity;
					} else {
						System.out.println("-- " + activity.getActivityName() + " [" + activity.getActivityId() + "] "
								+ activity.getAssignee()+activity.toString()+activity.getDurationInMillis() + " ms");
					}
				}
				if (endActivity != null) {
					System.out.println("-- " + endActivity.getActivityName() + " [" + endActivity.getActivityId() + "] "
							+ endActivity.getDurationInMillis() + " ms");
					System.out.println("COMPLETE " + processDefinition.getName() + " ["
							+ processInstance.getProcessDefinitionKey() + "] " + endActivity.getEndTime());
				}
			}
			// Re-query the process instance, making sure the latest state is
			// available

			processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId())
					.singleResult();
		}

	}
}
