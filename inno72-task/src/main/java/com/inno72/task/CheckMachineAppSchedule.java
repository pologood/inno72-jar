package com.inno72.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.inno72.bean.SendMessageBean;
import com.inno72.common.TaskProperties;
import com.inno72.common.utils.StringUtil;
import com.inno72.job.core.biz.model.ReturnT;
import com.inno72.job.core.handle.IJobHandler;
import com.inno72.job.core.handle.annotation.JobHandler;
import com.inno72.redis.IRedisUtil;
import com.inno72.util.AesUtils;
import com.inno72.util.GZIPUtil;
import com.inno72.util.HttpFormConnector;
import com.inno72.vo.AppInstallHistory;
import com.inno72.vo.AppMsgVo;
import com.inno72.vo.AppStatus;
import com.inno72.vo.AppVersion;
import com.inno72.vo.MachineAppStatus;
import com.inno72.vo.MachineInstallAppBean;

@Component
@JobHandler("checkMachineAppTask")
public class CheckMachineAppSchedule implements IJobHandler {
	@Autowired
	private MongoOperations mongoTpl;

	@Resource
	private IRedisUtil redisUtil;

	@Resource
	private TaskProperties taskProperties;

	public void checkApp(MachineAppStatus apps) {
		if (apps != null && apps.getStatus() != null) {
			List<MachineInstallAppBean> il = new ArrayList<>();
			List<AppStatus> apps1 = apps.getStatus();
			int verisonCode = 0;

			AppInstallHistory history = new AppInstallHistory();

			history.setMachineCode(apps.getMachineId());
			history.setCreateTime(LocalDateTime.now());
			history.setStatus(0);
			history.setApps(il);
			for (AppStatus app : apps1) {
				if ("com.inno72.installer".equals(app.getAppPackageName())) {
					verisonCode = app.getVersionCode();
				}
				Query query = new Query();
				query.addCriteria(Criteria.where("appPackageName").is(app.getAppPackageName()));
				List<AppVersion> appVersions = mongoTpl.find(query, AppVersion.class, "AppVersion");
				if (appVersions != null && !appVersions.isEmpty()) {
					AppVersion appVersion = appVersions.get(0);
					if (app.getVersionCode() < appVersion.getAppVersionCode() && app.getVersionCode() != -1) {
						MachineInstallAppBean bean = new MachineInstallAppBean();
						bean.setAppPackageName(appVersion.getAppPackageName());
						bean.setUrl(appVersion.getDownloadUrl());
						bean.setVersionCode(appVersion.getAppVersionCode());
						bean.setSeq(appVersion.getSeq());
						il.add(bean);
					}
				}
			}
			if (!il.isEmpty() && (verisonCode >= 5 || verisonCode == 0)) {
				SendMessageBean msg = new SendMessageBean();
				msg.setEventType(2);
				msg.setSubEventType(2);
				msg.setMachineId(apps.getMachineId());
				msg.setData(il);
				history.setStatus(1);
				String result = GZIPUtil.compress(AesUtils.encrypt(JSON.toJSONString(msg)));

				String url = taskProperties.get("sendMsgUrl");
				AppMsgVo vo = new AppMsgVo();
				vo.setData(result);
				vo.setIsQueue(1);
				vo.setTargetCode(msg.getMachineId());
				vo.setTargetType("1");
				try {
					Map<String, String> headers = new HashMap<>();
					headers.put("MsgType", "message");
					HttpFormConnector.doPostJson(url, JSON.toJSONString(vo), headers, 5000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mongoTpl.save(history, "AppInstallHistory");
		}

	}

	@Override
	public void destroy() {

	}

	@Override
	public ReturnT<String> execute(String arg0) throws Exception {
		List<MachineAppStatus> appVersions = mongoTpl.find(new Query(), MachineAppStatus.class, "MachineAppStatus");
		List<String> machineCodes = new ArrayList<>();
		if (appVersions != null) {
			for (MachineAppStatus app : appVersions) {
				if (!StringUtil.isEmpty(app.getMachineId()) && !machineCodes.contains(app.getMachineId())) {
					checkApp(app);
					machineCodes.add(app.getMachineId());
				}
			}
		}
		return new ReturnT<>(ReturnT.SUCCESS_CODE, "ok");
	}

	@Override
	public void init() {

	}

}
