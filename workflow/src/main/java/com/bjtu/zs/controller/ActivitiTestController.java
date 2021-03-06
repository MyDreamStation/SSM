package com.bjtu.zs.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormData;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.form.DateFormType;
import org.activiti.engine.impl.form.LongFormType;
import org.activiti.engine.impl.form.StringFormType;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjtu.zs.pojo.ProcInstance;
import com.bjtu.zs.pojo.Todo;
import com.bjtu.zs.pojo.User;
import com.bjtu.zs.service.ProcessService;
import com.bjtu.zs.util.MailEntity;
import com.bjtu.zs.util.QuickReturn;
import com.bjtu.zs.vo.ProcessInstanceQueryVo;
import com.bjtu.zs.vo.TaskQueryVo;

@Controller
@RequestMapping("/activiti")
public class ActivitiTestController {

	/**
	 * 用于测试activiti与spring整合，是否创建了一个流程引擎
	 */
	@Autowired
	private ProcessEngine processEngine;

	@Autowired
	private RuntimeService runtimeService;

	/**
	 * This service introduces the concept of a start form and a task form. A
	 * start form is a form that is shown to the user before the process
	 * instance is started, while a task form is the form that is displayed when
	 * a user wants to complete a form. Activiti allows to define these forms in
	 * the BPMN 2.0 process definition
	 */
	@Autowired
	private FormService formService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private HistoryService historyService;// 历史流程信息

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private ProcessService processService;

	// TODO 存在线程安全问题,最好使用new来解决，将邮件发件和收件人信息保存在runtimeService中的variable中
	// 或者使用ctx.getBean，并在配置文件将MailEntity的bean定义的scope设置为prototype
	@Autowired
	private MailEntity mail;

	@RequestMapping("/test")
	@ResponseBody
	public Map<String, Object> test(String info) throws ParseException {

		// 输出流程引擎的版本信息
		String pName = processEngine.getName();
		String ver = ProcessEngine.VERSION;
		System.out.println("ProcessEngine [" + pName + "] Version: [" + ver + "]");
		// 获取流程中的流程模型数目
		long count = repositoryService.createProcessDefinitionQuery().processDefinitionKey("onboarding").count();
		System.out.println("一共有:" + count + "个流程实例");

		// 获取流程定义
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey("onboarding").singleResult();

		// 获取名为onboarding的流程实例,并开启
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("onboarding");
		// 输出流程实例的基本信息
		System.out.println("Onboarding process started with process instance id ["
				+ processInstance.getProcessInstanceId() + "] key [" + processInstance.getProcessDefinitionKey() + "]");

		if (processInstance != null && !processInstance.isEnded()) {
			List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("managers").list();
			System.out.println("Active outstanding tasks: [" + tasks.size() + "]");
			for (int i = 0; i < tasks.size(); i++) {
				Task task = tasks.get(i);
				System.out.println("Processing Task [" + task.getName() + "]");
				Map<String, Object> variables = new HashMap<String, Object>();
				// 通过任务id获取要提交的数据
				FormData formData = formService.getTaskFormData(task.getId());
				for (FormProperty formProperty : formData.getFormProperties()) {
					if (StringFormType.class.isInstance(formProperty.getType())) {
						System.out.println(formProperty.getName() + "?");
						String value = info;
						variables.put(formProperty.getId(), value);
					} else if (LongFormType.class.isInstance(formProperty.getType())) {
						System.out.println(formProperty.getName() + "? (Must be a whole number)");
						Long value = Long.valueOf(info);
						variables.put(formProperty.getId(), value);
					} else if (DateFormType.class.isInstance(formProperty.getType())) {
						System.out.println(formProperty.getName() + "? (Must be a date m/d/yy)");
						DateFormat dateFormat = new SimpleDateFormat("m/d/yy");
						Date value = dateFormat.parse(info);
						variables.put(formProperty.getId(), value);
					} else {
						System.out.println("<form type not supported>");
					}
				}
				taskService.complete(task.getId(), variables);

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
								+ activity.getDurationInMillis() + " ms");
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

		return QuickReturn.mapOk("success");
	}

	@RequestMapping(value = "/startProcess", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> startProcessByKey(String id) {
		try {
			System.out.println("流程Id为:" + id);
			String pId = processService.startProcessByKey(id);
			return QuickReturn.mapOk(pId);
		} catch (Exception e) {
			e.printStackTrace();
			return QuickReturn.mapError("服务器错误" + e.getMessage());
		}
	}

	@RequestMapping(value = "/submitParam", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> submitParam(String id, String content, String to) {
		Map<String, Object> param = new HashMap<>();

		param.put("content", content);
		mail.setTo(to);
		try {
			processService.submitParameter(param, id);
			return QuickReturn.mapOk("提交成功");
		} catch (Exception e) {
			e.printStackTrace();
			return QuickReturn.mapError("服务器错误" + e.getMessage());
		}
	}

	@RequestMapping(value = "/getTodo", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> myTodo(HttpServletRequest request, TaskQueryVo taskQueryVo) {
		User user = (User) request.getSession().getAttribute("user");

		if (user == null) {
			return QuickReturn.mapError("用户未登录！");
		}
		// 获取用户名
		String loginId = user.getLoginId();

		// 获取待办事项
		List<Todo> list = new ArrayList<Todo>();

		try {
			list = processService.getTodo(loginId, taskQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
			return QuickReturn.mapError("服务器错误：" + e.getMessage());
		}
		if (list.isEmpty()) {
			return QuickReturn.mapOk("");
		}
		return QuickReturn.mapOk(list);

	}

	@RequestMapping(value = "/getProcIns",method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> getProcInstance(ProcessInstanceQueryVo processInstanceQueryVo){
		
		List<ProcInstance> list = new ArrayList<ProcInstance>();
		
		try {
			list = processService.getProcessInstanceByParam(processInstanceQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
			return QuickReturn.mapError("服务器错误：" + e.getMessage());
		}
		if (list.isEmpty()) {
			return QuickReturn.mapOk("");
		}
		return QuickReturn.mapOk(list);
	};
}
