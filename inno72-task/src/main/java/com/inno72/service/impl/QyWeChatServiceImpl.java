package com.inno72.service.impl;

import java.text.MessageFormat;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.inno72.common.CommonConstants;
import com.inno72.config.client.QyhProperties;
import com.inno72.plugin.http.HttpClient;
import com.inno72.redis.IRedisUtil;
import com.inno72.service.QyWeChatService;

@Component
public class QyWeChatServiceImpl implements QyWeChatService {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource
	private IRedisUtil redisUtil;

	@Resource
	private QyhProperties qyhProperties;

	@Override
	public void getCheckAgentAccessToken() {

		String corpid = qyhProperties.getProps().get("corpid");
		String corpsecret = qyhProperties.getProps().get("checkAgentCorpsecret");
		String accessTokenKey = qyhProperties.getProps().get("qyCheckAgentAccTokenKey");

		String url = MessageFormat.format(CommonConstants.qyWeChatGetsAccessTokenUrl, corpid, corpsecret);
		String result = HttpClient.get(url);
		JSONObject resultJson = JSON.parseObject(result);
		if (resultJson.getInteger("errcode") == 0) {
			redisUtil.set(accessTokenKey, resultJson.getString("access_token"));
			log.info(resultJson.getString("access_token"));
		} else {
			log.info(resultJson.getString("errmsg"));
		}

	}

	@Override
	public void getMemberAccessToken() {

		String corpid = qyhProperties.getProps().get("corpid");
		String corpsecret = qyhProperties.getProps().get("userCorpsecret");
		String accessTokenKey = qyhProperties.getProps().get("qyUserAccTokenKey");

		String url = MessageFormat.format(CommonConstants.qyWeChatGetsAccessTokenUrl, corpid, corpsecret);
		String result = HttpClient.get(url);
		JSONObject resultJson = JSON.parseObject(result);
		if (resultJson.getInteger("errcode") == 0) {
			redisUtil.set(accessTokenKey, resultJson.getString("access_token"));
			log.info(resultJson.getString("access_token"));
		} else {
			log.info(resultJson.getString("errmsg"));
		}

	}

}
