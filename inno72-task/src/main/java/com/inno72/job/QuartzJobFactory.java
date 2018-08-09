package com.inno72.job;

import java.util.ArrayList;
import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.inno72.bean.SendMessageBean;
import com.inno72.common.TaskProperties;
import com.inno72.plugin.http.HttpClient;

@DisallowConcurrentExecution
public class QuartzJobFactory implements Job {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			JobInfo scheduleJob = (JobInfo) context.getMergedJobDataMap().get("jobInfo");
			log.info("获取机器{}的状态", scheduleJob.getMachineCode().toString());
			updateMachineStatus(scheduleJob.getMachineCode(), scheduleJob.getTaskProperties());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateMachineStatus(List<String> machineCodes, TaskProperties taskProperties) {
		List<SendMessageBean> list = new ArrayList<>();
		for (String machineCode : machineCodes) {
			SendMessageBean msg = new SendMessageBean();
			msg.setEventType(1);
			msg.setSubEventType(1);
			msg.setMachineId(machineCode);
			list.add(msg);
		}
		sendMsg(taskProperties, list.toArray(new SendMessageBean[0]));
	}

	private void sendMsg(TaskProperties taskProperties, SendMessageBean... beans) {
		String url = taskProperties.get("sendAppMsgUrl");
		try {
			String result = HttpClient.post(url, JSON.toJSONString(beans));
			log.info("发送结果：{}", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
