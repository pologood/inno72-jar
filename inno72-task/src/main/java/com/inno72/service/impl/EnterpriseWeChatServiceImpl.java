package com.inno72.service.impl;

import java.text.MessageFormat;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.inno72.common.TaskProperties;
import com.inno72.plugin.http.HttpClient;
import com.inno72.redis.IRedisUtil;
import com.inno72.service.EnterpriseWeChatService;

@Component
public class EnterpriseWeChatServiceImpl implements EnterpriseWeChatService {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource
	private IRedisUtil redisUtil;

	@Resource
	private TaskProperties taskProperties;

	@Override
	public void getAccessToken() {

		String corpid = taskProperties.getProps().get("corpid");

		String corpsecret = taskProperties.getProps().get("corpsecret");

		String enterpriseWeChatGetsAccessTokenUrl = taskProperties.getProps().get("enterpriseWeChatGetsAccessTokenUrl");

		String url = MessageFormat.format(enterpriseWeChatGetsAccessTokenUrl, corpid, corpsecret);
		String result = HttpClient.get(url);
		JSONObject resultJson = JSON.parseObject(result);
		if (resultJson.getInteger("errcode") == 0) {
			redisUtil.set("enterpriseWeChatAccessToken", resultJson.getString("access_token"));
			log.info(resultJson.getString("access_token"));
		} else {
			log.info(resultJson.getString("errmsg"));
		}

	}

}
