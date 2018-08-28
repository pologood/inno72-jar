package com.inno72.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.inno72.common.TaskProperties;
import com.inno72.job.JobFactory;
import com.inno72.job.JobInfo;
import com.inno72.job.QuartzJobFactory;
import com.inno72.model.Inno72Task;
import com.inno72.service.AppMsgService;
import com.inno72.service.TaskMachineService;
import com.inno72.service.TaskService;

import tk.mybatis.mapper.entity.Condition;

@Configuration
@EnableScheduling
public class TaskSchedule {
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	private TaskProperties taskProperties;
	@Resource
	private TaskService taskService;
	@Resource
	private TaskMachineService taskMachineService;
	@Resource
	private AppMsgService appMsgService;

	@Scheduled(cron = "0 */1 * * * ?")
	public void getTasks() {
		Condition condition = new Condition(Inno72Task.class);
		List<Integer> list = new ArrayList<>();
		list.add(0);
		list.add(3);
		condition.createCriteria().andIn("status", list);
		List<Inno72Task> tasks = taskService.findByCondition(condition);
		if (tasks == null || tasks.isEmpty()) {
			log.info("===================没有新的任务==============");
			return;
		}
		for (Inno72Task task : tasks) {
			LocalDateTime doTime = task.getDoTime();
			if (doTime != null && doTime.isAfter(LocalDateTime.now())) {
				addJob(doTime, task.getId());
				task.setStatus(1);
				task.setUpdateTime(LocalDateTime.now());
				taskService.update(task);
			} else {
				taskService.executeTask(task.getId());

			}

		}
		log.info("===================执行完成任务==============");
	}

	private void addJob(LocalDateTime doTime, String taskId) {
		JobInfo info = new JobInfo();
		info.setTaskType(2);
		info.setTaskService(taskService);
		info.setTaskId(taskId);
		StringBuffer corn = new StringBuffer();
		corn.append(doTime.getSecond()).append(" ").append(doTime.getMinute()).append(" ").append(doTime.getHour())
				.append(" ").append(doTime.getDayOfMonth()).append(" ").append(doTime.getMonthValue()).append(" ? ")
				.append(doTime.getYear());
		JobFactory.addJob(taskId, QuartzJobFactory.class, corn.toString(), info);
	}

}