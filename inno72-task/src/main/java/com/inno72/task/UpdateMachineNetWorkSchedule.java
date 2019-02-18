package com.inno72.task;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.inno72.common.utils.StringUtil;
import com.inno72.mapper.Inno72MachineMapper;
import com.inno72.model.Inno72Machine;
import com.inno72.redis.IRedisUtil;

import tk.mybatis.mapper.entity.Condition;

@Configuration
@EnableScheduling
public class UpdateMachineNetWorkSchedule {
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private Inno72MachineMapper inno72MachineMapper;
	@Resource
	private IRedisUtil redisUtil;

	@Scheduled(cron = "0 0/2 * * * ?")
	public void updateMachineNetWorkStatus() {
		log.info("=======================更新网络状态====================");
		Condition condition = new Condition(Inno72Machine.class);
		condition.createCriteria().andNotEqualTo("netStatus", 0);
		List<Inno72Machine> machines = inno72MachineMapper.selectByCondition(condition);
		if (machines == null || machines.isEmpty()) {
			return;
		}
		List<String> list = new ArrayList<>();
		for (Inno72Machine machine : machines) {
			String value = redisUtil.get("monitor:session:" + machine.getMachineCode());
			if (StringUtil.isEmpty(value)) {
				list.add(machine.getId());
			}
		}
		if (!list.isEmpty()) {
			inno72MachineMapper.updateNetStatus(list);
		}
	}

}