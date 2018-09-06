package com.inno72.service;

import com.inno72.common.Service;
import com.inno72.model.Inno72Task;

/**
 * Created by CodeGenerator on 2018/08/24.
 */
public interface TaskService extends Service<Inno72Task> {
	void executeTask(String taskId);
}
