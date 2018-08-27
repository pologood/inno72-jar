package com.inno72.service;

import com.inno72.bean.SendMessageTaskBean;
import com.inno72.common.Service;
import com.inno72.model.Inno72AppMsg;

/**
 * Created by CodeGenerator on 2018/08/16.
 */
public interface AppMsgService extends Service<Inno72AppMsg> {

	void sendSocketMsg(SendMessageTaskBean bean);

	void sendPushMsg(SendMessageTaskBean bean);
}
