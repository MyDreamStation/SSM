package test;

import org.activiti.engine.FormService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;

public class TestMailTask {

	
	public static void main(String[] args) {
		ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
		
		RepositoryService repositoryService = engine.getRepositoryService();
		RuntimeService runtimeService = engine.getRuntimeService();
		FormService formService = engine.getFormService();
		TaskService taskService = engine.getTaskService();
		
		Deployment deploy = repositoryService.createDeployment().addClasspathResource("testMailProcess.bpmn20.xml").deploy();
		
		runtimeService.startProcessInstanceByKey("testMailProcess");
		
		
		
	}
	
}
