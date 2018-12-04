package com.inno72.vo;

import java.time.LocalDateTime;
import java.util.List;

public class AppInstallHistory {
	private String machineCode;
	private List<MachineInstallAppBean> apps;
	private int status;
	private LocalDateTime createTime;

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public List<MachineInstallAppBean> getApps() {
		return apps;
	}

	public void setApps(List<MachineInstallAppBean> apps) {
		this.apps = apps;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

}
