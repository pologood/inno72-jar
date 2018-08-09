package com.inno72.job;

import java.util.List;

import com.inno72.common.TaskProperties;

public class JobInfo {
	private List<String> machineCode;
	private TaskProperties taskProperties;

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

}
