package com.inno72.bean;

public class SendMessageTaskBean {
	// {"data":{"appPackageName":"com.net","versionCode":10,"url":"http://www.baidu.com"},"doType":1,"eventType":1,"machineId":"111111"}
	// {"data":{"appPackageName":"com.net"},"doType":1,"eventType":2,"machineId":"111111"}
	// {"data":{"channelCode":"11"},"doType":1,"eventType":3,"machineId":"111111"}
	// {"data":{"channelCode":"11"},"doType":1,"eventType":4,"machineId":"111111"}

	private Integer eventType; // 1 升级 2卸载 3合并 4拆分
	private Integer doType;// 1socket 2push
	private String taskId;
	private String machineId;
	private String id;
	private Object data;

	public Integer getEventType() {
		return eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getDoType() {
		return doType;
	}

	public void setDoType(Integer doType) {
		this.doType = doType;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// public static void main(String[] args) {
	// SendMessageTaskBean b = new SendMessageTaskBean();
	// b.setEventType(3);
	// b.setSubEventType(31);
	// b.setDoType(1);
	// b.setMachineId("111111");
	// Map<String, Object> map = new HashMap<>();
	// map.put("channelCode", "11");
	// b.setData(map);
	// System.out.println(JSON.toJSONString(b));
	// }
}
