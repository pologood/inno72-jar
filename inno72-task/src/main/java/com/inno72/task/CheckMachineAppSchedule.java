package com.inno72.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.inno72.bean.SendMessageBean;
import com.inno72.common.utils.StringUtil;
import com.inno72.job.core.biz.model.ReturnT;
import com.inno72.job.core.handle.IJobHandler;
import com.inno72.job.core.handle.annotation.JobHandler;
import com.inno72.model.Inno72AppMsg;
import com.inno72.redis.IRedisUtil;
import com.inno72.service.AppMsgService;
import com.inno72.util.AesUtils;
import com.inno72.util.GZIPUtil;
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

	@Autowired
	private AppMsgService appMsgService;

	public void checkApp(MachineAppStatus apps) {
		if (apps != null && apps.getStatus() != null) {
			List<MachineInstallAppBean> il = new ArrayList<>();
			List<AppStatus> apps1 = apps.getStatus();
			int verisonCode = 0;
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

				String result = GZIPUtil.compress(AesUtils.encrypt(JSON.toJSONString(msg)));
				String machinKey = "monitor:session:" + msg.getMachineId();
				String sessionId = redisUtil.get(machinKey);
				if (!com.inno72.common.utils.StringUtil.isEmpty(sessionId)) {
					Inno72AppMsg msg1 = new Inno72AppMsg();
					msg1.setId(StringUtil.uuid());
					msg1.setCreateTime(LocalDateTime.now());
					msg1.setMachineCode(msg.getMachineId());
					msg1.setContent(result);
					msg1.setStatus(0);
					msg1.setMsgType(1);
					appMsgService.save(msg1);
				}
			}
		}

	}

	@Override
	public void destroy() {

	}

	@Override
	public ReturnT<String> execute(String arg0) throws Exception {
		System.out.println("111111111111111111111");
		return new ReturnT<>(ReturnT.SUCCESS_CODE, "ok");
	}

	@Override
	public void init() {

	}

}
