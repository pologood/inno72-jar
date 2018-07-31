package com.inno72.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.inno72.service.EnterpriseWeChatService;

/**
 * @Auther: zhangwenjie
 * @Date: 2018/7/31 12:12
 * @Description:企业微信定时任务
 */
@Configuration
@EnableScheduling
public class EnterpriseWeChatSchedule {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EnterpriseWeChatService enterpriseWeChatService;

	@Scheduled(fixedRate = 3000)
	public void getAccessToken() {

		log.info("获取企业微信 access_token，开始");

		enterpriseWeChatService.getAccessToken();

		log.info("获取企业微信 access_token，结束");

	}
}
