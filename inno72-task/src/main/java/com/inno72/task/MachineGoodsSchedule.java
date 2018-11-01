package com.inno72.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.inno72.service.MachineGoodsService;

/**
 * @Auther: zhangwenjie
 * @Date: 2018/10/30 12:12
 * @Description:获取机器在进行活动货道商品定时任务
 */
@Configuration
@EnableScheduling
public class MachineGoodsSchedule {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MachineGoodsService machineGoodsService;

	@Scheduled(cron = "*/30 * * * * ?")
	public void saveMachineGoodsCoun() {

		log.info("获取机器在进行活动货道商品，开始");

		int num = machineGoodsService.saveMachineGoods();

		log.info("获取机器在进行活动货道商品，结束:" + num);

	}

}
