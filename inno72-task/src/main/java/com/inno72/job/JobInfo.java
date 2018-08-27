package com.inno72.job;

import java.util.List;

import com.inno72.common.TaskProperties;
import com.inno72.service.TaskService;

public class JobInfo {
	private List<String> machineCode;
	private TaskProperties taskProperties;
	private int taskType;// 1获取机器状态 2 执行定时任务
	private TaskService taskService;
	private String taskId;

	public List<String> getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(List<String> machineCode) {
		this.machineCode = machineCode;
	}

	public TaskProperties getTaskProperties() {
		return taskProperties;
	}

	public void setTaskProperties(TaskProperties taskProperties) {
		this.taskProperties = taskProperties;
	}

	public int getTaskType() {
		return taskType;
	}

	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

}
