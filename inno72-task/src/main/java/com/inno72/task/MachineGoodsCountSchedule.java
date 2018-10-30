package com.inno72.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.inno72.service.MachineGoodsCountService;

/**
 * @Auther: zhangwenjie
 * @Date: 2018/7/31 12:12
 * @Description:企业微信定时任务
 */
@Configuration
@EnableScheduling
public class MachineGoodsCountSchedule {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MachineGoodsCountService machineGoodsCountService;

	@Scheduled(cron = "*/5 * * * * ?")
	public void getCheckAgentAccessToken() {

		log.info("统计机器商品剩余数量，开始");

		int num = machineGoodsCountService.saveMachineGoodsCoun();

		log.info("统计机器商品剩余数量，结束:" + num);

	}

}
