package com.inno72.job;

import static org.quartz.JobBuilder.newJob;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

public class JobFactory {
	private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
	private static String JOB_GROUP_NAME = "MY_JOBGROUP_NAME";
	private static String TRIGGER_GROUP_NAME = "MY_TRIGGERGROUP_NAME";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void addJob(String jobName, Class cls, String time, Object scheduleJob) {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			JobDetail job = newJob(cls).withIdentity(jobName, JOB_GROUP_NAME).build();
			// 添加具体任务方法
			job.getJobDataMap().put("jobInfo", scheduleJob);
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(time);
			// 按新的cronExpression表达式构建一个新的trigger
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, TRIGGER_GROUP_NAME)
					.withSchedule(scheduleBuilder).build();

			// 交给scheduler去调度
			sched.scheduleJob(job, trigger);

			// 启动
			if (!sched.isShutdown()) {
				sched.start();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void removeJob(String jobName) {
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
		JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			Trigger trigger = sched.getTrigger(triggerKey);
			if (trigger == null) {
				return;
			}
			sched.pauseTrigger(triggerKey);
			sched.unscheduleJob(triggerKey);// 移除触发器
			sched.deleteJob(jobKey);// 删除任务
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
