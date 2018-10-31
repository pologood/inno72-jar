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
 * @Date: 2018/10/30 12:12
 * @Description:统计机器商品剩余数量定时任务
 */
@Configuration
@EnableScheduling
public class MachineGoodsCountSchedule {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MachineGoodsCountService machineGoodsCountService;

	@Scheduled(cron = "0 30 0 * * ? ")
	public void saveMachineGoodsCoun() {

		log.info("统计机器商品剩余数量，开始");

		int num = machineGoodsCountService.saveMachineGoodsCoun();

		log.info("统计机器商品剩余数量，结束:" + num);

	}

}
