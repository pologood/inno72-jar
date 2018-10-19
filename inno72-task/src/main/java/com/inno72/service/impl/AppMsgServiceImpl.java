package com.inno72.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.inno72.bean.SendMessageBean;
import com.inno72.bean.SendMessageTaskBean;
import com.inno72.common.AbstractService;
import com.inno72.common.utils.StringUtil;
import com.inno72.mapper.Inno72AppMsgMapper;
import com.inno72.mapper.Inno72LocaleMapper;
import com.inno72.mapper.Inno72MachineMapper;
import com.inno72.model.Inno72AppMsg;
import com.inno72.model.Inno72Machine;
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
	private Inno72LocaleMapper inno72LocaleMapper;
	@Resource
	private Inno72MachineMapper inno72MachineMapper;
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
		msg1.setMsgType(2);
		save(msg1);
	}

	@Override
	public void sendPushMsg(SendMessageTaskBean bean) {
		Map<String, Object> p = new HashMap<>();
		p.put("pushType", 10);
		p.put("msgInfo", bean);
		Map<String, String> params = new HashMap<>();
		params.put("msg", JSON.toJSONString(p));
		Inno72Machine machine = inno72MachineMapper.selectByPrimaryKey(bean.getId());
		if (machine != null && machine.getMachineStatus() == 9) {
			msgUtil.sendPush("push_android_tm_transmission_common", params, bean.getMachineId(),
					"machine-app-backend--pushMsg", "", "");
			return;
		}
		msgUtil.sendPush("push_android_transmission_common", params, bean.getMachineId(),
				"machine-app-backend--pushMsg", "", "");
	}

	@Override
	public void sendSocketMsg(SendMessageBean bean) {
		String result = GZIPUtil.compress(AesUtils.encrypt(JSON.toJSONString(bean)));
		Inno72AppMsg msg1 = new Inno72AppMsg();
		msg1.setId(StringUtil.uuid());
		msg1.setCreateTime(LocalDateTime.now());
		msg1.setMachineCode(bean.getMachineId());
		msg1.setContent(result);
		msg1.setStatus(0);
		msg1.setMsgType(4);
		save(msg1);
	}

}
