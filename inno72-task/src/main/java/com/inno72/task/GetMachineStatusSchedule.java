package com.inno72.task;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.inno72.bean.MachineLogInfo;
import com.inno72.common.DateUtil;
import com.inno72.common.TaskProperties;
import com.inno72.job.JobFactory;
import com.inno72.job.JobInfo;
import com.inno72.job.QuartzJobFactory;

@Configuration
@EnableScheduling
public class GetMachineStatusSchedule {
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	private TaskProperties taskProperties;

	@Autowired
	private MongoOperations mongoTpl;

	@Scheduled(cron = "0 */30 * * * ?")
	public void getMachineStatus() {
		List<String> machineCodeList = new ArrayList<>();
		List<MachineLogInfo> netList = mongoTpl.find(new Query(), MachineLogInfo.class, "MachineLogInfo");
		for (MachineLogInfo machineLogInfo : netList) {
			LocalDateTime createTime = machineLogInfo.getCreateTime();
			Duration duration = Duration.between(createTime, LocalDateTime.now());
			long between = duration.toMinutes();
			if (between <= 2) {
				machineCodeList.add(machineLogInfo.getMachineId());
			}
		}
		List<List<String>> splitList = split(machineCodeList, 10);
		int i = 1;
		for (List<String> list : splitList) {
			LocalDateTime now = LocalDateTime.now().plusMinutes(i);
			StringBuffer corn = new StringBuffer();
			corn.append("0 ").append(now.getMinute()).append(" ").append(now.getHour()).append(" ")
					.append(now.getDayOfMonth()).append(" ").append(now.getMonthValue()).append(" ? ")
					.append(now.getYear());
			JobInfo job = new JobInfo();
			job.setMachineCode(list);
			job.setTaskProperties(taskProperties);
			log.info("此次获取机器状态共{}次，将在{}执行第{}次", splitList.size(), DateUtil.toTimeStr(now, DateUtil.DF_FULL_S1), i++);
			JobFactory.addJob(corn.toString(), QuartzJobFactory.class, corn.toString(), job);
		}
	}

	public static <T> List<List<T>> split(List<T> resList, int subListLength) {
		if (resList == null || subListLength <= 0) {
			return new ArrayList<>();
		}
		List<List<T>> ret = new ArrayList<>();
		int size = resList.size();
		if (size <= subListLength) {
			// 数据量不足 subListLength 指定的大小
			ret.add(resList);
		} else {
			int pre = size / subListLength;
			int last = size % subListLength;
			// 前面pre个集合，每个大小都是 subListLength 个元素
			for (int i = 0; i < pre; i++) {
				List<T> itemList = new ArrayList<>();
				for (int j = 0; j < subListLength; j++) {
					itemList.add(resList.get(i * subListLength + j));
				}
				ret.add(itemList);
			}
			// last的进行处理
			if (last > 0) {
				List<T> itemList = new ArrayList<>();
				for (int i = 0; i < last; i++) {
					itemList.add(resList.get(pre * subListLength + i));
				}
				ret.add(itemList);
			}
		}
		return ret;
	}

}
