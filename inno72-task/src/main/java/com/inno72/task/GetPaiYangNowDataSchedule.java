package com.inno72.task;

import com.inno72.common.TaskProperties;
import com.inno72.plugin.http.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

@Configuration
@EnableScheduling
public class GetPaiYangNowDataSchedule {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TaskProperties taskProperties;

    @Scheduled(cron = "0 0/30 * * * ?")
    public void addData(){
        logger.info("执行插入派样活动商品数量统计数据定时开始");
        String url = taskProperties.getProps().get("setPaiYangTotalData");
        HttpClient.get(url);
        logger.info("执行插入派样活动商品数量统计数据定时结束");
    }
}
