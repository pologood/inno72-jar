package com.inno72.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "inno72_task")
public class Inno72Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	/**
	 * 任务类型 1 升级 2卸载 3合并 4拆分
	 */
	private Integer type;

	/**
	 * 任务状态 0未执行 1待执行 2已执行 3继续执行
	 */
	private Integer status;

	/**
	 * 执行类型 1socket 2push
	 */
	@Column(name = "do_type")
	private Integer doType;

	/**
	 * 执行时间
	 */
	@Column(name = "do_time")
	private LocalDateTime doTime;

	/**
	 * 操作app
	 */
	private String app;

	/**
	 * app地址
	 */
	@Column(name = "app_url")
	private String appUrl;

	/**
	 * app版本号
	 */
	@Column(name = "app_version")
	private String appVersion;

	@Column(name = "channel_code")
	private String channelCode;

	/**
	 * 创建人
	 */
	@Column(name = "create_id")
	private String createId;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private LocalDateTime createTime;

	/**
	 * 更新人
	 */
	@Column(name = "update_id")
	private String updateId;

	/**
	 * 更新时间
	 */
	@Column(name = "update_time")
	private LocalDateTime updateTime;

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取任务类型
	 *
	 * @return type - 任务类型
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 设置任务类型
	 *
	 * @param type
	 *            任务类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 获取任务状态
	 *
	 * @return status - 任务状态
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置任务状态
	 *
	 * @param status
	 *            任务状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取执行类型
	 *
	 * @return do_type - 执行类型
	 */
	public Integer getDoType() {
		return doType;
	}

	/**
	 * 设置执行类型
	 *
	 * @param doType
	 *            执行类型
	 */
	public void setDoType(Integer doType) {
		this.doType = doType;
	}

	/**
	 * 获取执行时间
	 *
	 * @return do_time - 执行时间
	 */
	public LocalDateTime getDoTime() {
		return doTime;
	}

	/**
	 * 设置执行时间
	 *
	 * @param doTime
	 *            执行时间
	 */
	public void setDoTime(LocalDateTime doTime) {
		this.doTime = doTime;
	}

	/**
	 * 获取操作app
	 *
	 * @return app - 操作app
	 */
	public String getApp() {
		return app;
	}

	/**
	 * 设置操作app
	 *
	 * @param app
	 *            操作app
	 */
	public void setApp(String app) {
		this.app = app;
	}

	/**
	 * 获取app地址
	 *
	 * @return app_url - app地址
	 */
	public String getAppUrl() {
		return appUrl;
	}

	/**
	 * 设置app地址
	 *
	 * @param appUrl
	 *            app地址
	 */
	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	/**
	 * 获取app版本号
	 *
	 * @return app_version - app版本号
	 */
	public String getAppVersion() {
		return appVersion;
	}

	/**
	 * 设置app版本号
	 *
	 * @param appVersion
	 *            app版本号
	 */
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	/**
	 * 获取创建人
	 *
	 * @return create_id - 创建人
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * 设置创建人
	 *
	 * @param createId
	 *            创建人
	 */
	public void setCreateId(String createId) {
		this.createId = createId;
	}

	/**
	 * 获取创建时间
	 *
	 * @return create_time - 创建时间
	 */
	public LocalDateTime getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 *
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取更新人
	 *
	 * @return update_id - 更新人
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * 设置更新人
	 *
	 * @param updateId
	 *            更新人
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	/**
	 * 获取更新时间
	 *
	 * @return update_time - 更新时间
	 */
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置更新时间
	 *
	 * @param updateTime
	 *            更新时间
	 */
	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

}