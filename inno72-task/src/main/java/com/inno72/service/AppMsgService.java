package com.inno72.service;

import com.inno72.bean.SendMessageBean;
import com.inno72.bean.SendMessageTaskBean;

/**
 * Created by CodeGenerator on 2018/08/16.
 */
public interface AppMsgService {

	void sendSocketMsg(SendMessageTaskBean bean);

	void sendSocketMsg(SendMessageBean bean);

	void sendPushMsg(SendMessageTaskBean bean);
}
