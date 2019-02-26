package com.inno72.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.inno72.bean.SendMessageBean;
import com.inno72.bean.SendMessageTaskBean;
import com.inno72.common.AbstractService;
import com.inno72.common.TaskProperties;
import com.inno72.mapper.Inno72LocaleMapper;
import com.inno72.mapper.Inno72MachineMapper;
import com.inno72.model.Inno72AppMsg;
import com.inno72.model.Inno72Machine;
import com.inno72.msg.MsgUtil;
import com.inno72.service.AppMsgService;
import com.inno72.util.AesUtils;
import com.inno72.util.GZIPUtil;
import com.inno72.util.HttpFormConnector;
import com.inno72.vo.AppMsgVo;

/**
 * Created by CodeGenerator on 2018/08/16.
 */
@Service
@Transactional
public class AppMsgServiceImpl extends AbstractService<Inno72AppMsg> implements AppMsgService {
	@Resource
	private Inno72LocaleMapper inno72LocaleMapper;
	@Resource
	private Inno72MachineMapper inno72MachineMapper;
	@Resource
	private MsgUtil msgUtil;
	@Resource
	private TaskProperties taskProperties;

	@Override
	public void sendSocketMsg(SendMessageTaskBean bean) {
		String url = taskProperties.get("sendMsgUrl");
		String result = GZIPUtil.compress(AesUtils.encrypt(JSON.toJSONString(bean)));
		AppMsgVo vo = new AppMsgVo();
		vo.setData(result);
		vo.setIsQueue(1);
		vo.setTargetCode(bean.getMachineId());
		vo.setTargetType("1");
		try {
			Map<String, String> headers = new HashMap<>();
			headers.put("MsgType", "taskInfo");
			HttpFormConnector.doPostJson(url, JSON.toJSONString(vo), headers, 5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		String url = taskProperties.get("sendMsgUrl");
		String result = GZIPUtil.compress(AesUtils.encrypt(JSON.toJSONString(bean)));
		AppMsgVo vo = new AppMsgVo();
		vo.setData(result);
		vo.setIsQueue(1);
		vo.setTargetCode(bean.getMachineId());
		vo.setTargetType("1");
		try {
			Map<String, String> headers = new HashMap<>();
			headers.put("MsgType", "message");
			HttpFormConnector.doPostJson(url, JSON.toJSONString(vo), headers, 5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
