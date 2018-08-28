package com.inno72.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inno72.bean.SendMessageTaskBean;
import com.inno72.common.AbstractService;
import com.inno72.mapper.Inno72TaskMachineMapper;
import com.inno72.mapper.Inno72TaskMapper;
import com.inno72.model.Inno72Task;
import com.inno72.model.Inno72TaskMachine;
import com.inno72.service.AppMsgService;
import com.inno72.service.TaskMachineService;
import com.inno72.service.TaskService;

import tk.mybatis.mapper.entity.Condition;

/**
 * Created by CodeGenerator on 2018/08/24.
 */
@Service
@Transactional
public class TaskServiceImpl extends AbstractService<Inno72Task> implements TaskService {
	@Resource
	private Inno72TaskMapper inno72TaskMapper;
	@Resource
	private TaskMachineService taskMachineService;
	@Resource
	private AppMsgService appMsgService;
	@Resource
	private Inno72TaskMachineMapper inno72MachineMapper;

	@Override
	public void executeTask(String taskId) {
		Inno72Task task = inno72TaskMapper.selectByPrimaryKey(taskId);
		Condition condition = new Condition(Inno72TaskMachine.class);
		condition.createCriteria().andEqualTo("taskId", task.getId()).andNotEqualTo("doStatus", 1);
		List<Inno72TaskMachine> taskMachines = taskMachineService.findByCondition(condition);
		if (taskMachines != null) {
			for (Inno72TaskMachine machine : taskMachines) {
				machine.setDoType(task.getDoType());
				machine.setDoTime(LocalDateTime.now());
				taskMachineService.update(machine);
				SendMessageTaskBean msg = buildSendMsg(machine, task);
				if (task.getDoType() == 1) {
					appMsgService.sendSocketMsg(msg);
				} else if (task.getDoType() == 2) {
					appMsgService.sendPushMsg(msg);
				}
			}
		}
		task.setStatus(2);
		task.setUpdateTime(LocalDateTime.now());
		update(task);

	}

	private SendMessageTaskBean buildSendMsg(Inno72TaskMachine taskMachine, Inno72Task task) {
		SendMessageTaskBean bean = new SendMessageTaskBean();
		bean.setMachineId(taskMachine.getMachineCode());
		bean.setEventType(task.getStatus());
		bean.setDoType(task.getDoType());
		bean.setTaskId(taskMachine.getId());
		Map<String, Object> map = new HashMap<>();
		map.put("appPackageName", task.getApp());
		map.put("versionCode", task.getAppVersion());
		map.put("url", task.getAppUrl());
		map.put("channelCode", task.getChannelCode());
		bean.setData(map);
		return bean;
	}

}
