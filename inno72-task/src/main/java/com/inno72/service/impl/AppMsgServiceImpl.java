package com.inno72.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.inno72.bean.SendMessageTaskBean;
import com.inno72.common.AbstractService;
import com.inno72.common.utils.StringUtil;
import com.inno72.mapper.Inno72AppMsgMapper;
import com.inno72.model.Inno72AppMsg;
import com.inno72.msg.MsgUtil;
import com.inno72.service.AppMsgService;
import com.inno72.util.AesUtils;
import com.inno72.util.GZIPUtil;

/**
 * Created by CodeGenerator on 2018/08/16.
 */
@Service
@Transactional
public class AppMsgServiceImpl extends AbstractService<Inno72AppMsg> implements AppMsgService {
	@Resource
	private Inno72AppMsgMapper inno72AppMsgMapper;
	@Resource
	private MsgUtil msgUtil;

	@Override
	public void sendSocketMsg(SendMessageTaskBean bean) {
		String result = GZIPUtil.compress(AesUtils.encrypt(JSON.toJSONString(bean)));
		Inno72AppMsg msg1 = new Inno72AppMsg();
		msg1.setId(StringUtil.uuid());
		msg1.setCreateTime(LocalDateTime.now());
		msg1.setMachineCode(bean.getMachineId());
		msg1.setContent(result);
		msg1.setStatus(0);
		save(msg1);
	}

	@Override
	public void sendPushMsg(SendMessageTaskBean bean) {
		Map<String, String> params = new HashMap<>();
		params.put("msg", JSON.toJSONString(bean));
		msgUtil.sendPush("push_android_transmission_common", params, bean.getMachineId(),
				"machine-app-backend--pushMsg", "", "");
	}

}
