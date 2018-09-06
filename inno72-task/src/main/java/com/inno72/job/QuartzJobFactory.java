package com.inno72.job;

import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inno72.bean.SendMessageBean;

@DisallowConcurrentExecution
public class QuartzJobFactory implements Job {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			JobInfo scheduleJob = (JobInfo) context.getMergedJobDataMap().get("jobInfo");
			log.info("执行定时任务");
			if (scheduleJob.getTaskType() == 2) {
				String taskid = scheduleJob.getTaskId();
				scheduleJob.getTaskService().executeTask(taskid);
			} else {
				log.info("获取机器{}的状态", scheduleJob.getMachineCode().toString());
				updateMachineStatus(scheduleJob);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateMachineStatus(JobInfo scheduleJob) {
		List<String> machineCodes = scheduleJob.getMachineCode();
		for (String machineCode : machineCodes) {
			SendMessageBean msg = new SendMessageBean();
			msg.setEventType(1);
			msg.setSubEventType(1);
			msg.setMachineId(machineCode);
			scheduleJob.getAppMsgService().sendSocketMsg(msg);
		}
	}
}
