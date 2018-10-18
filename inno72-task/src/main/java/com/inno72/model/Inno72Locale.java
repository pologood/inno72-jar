package com.inno72.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.inno72.common.datetime.CustomLocalDateTimeSerializer;

@Table(name = "inno72_locale")
public class Inno72Locale {
	/**
	 * 点位ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@NotBlank(message = "请填写点位名称")
	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private Integer type;

	@NotBlank(message = "请选择区域")
	@Column(name = "area_code")
	private String areaCode;

	/**
	 * 商场
	 */
	@Column(name = "mall")
	private String mall;

	/**
	 * 运营人员
	 */
	@NotBlank(message = "请填写运营人员")
	@Column(name = "manager")
	private String manager;

	/**
	 * 运营人员手机
	 */
	@NotBlank(message = "请填写运营人手机号")
	@Pattern(regexp = "^(1[0-9])\\d{9}$", message = "手机格式不正确")
	@Column(name = "mobile")
	private String mobile;

	/**
	 * 监控：0开启，1不开启
	 */
	@Column(name = "monitor")
	private Integer monitor;

	/**
	 * 监控开始时间
	 */
	@Column(name = "monitor_start")
	private String monitorStart;

	/**
	 * 监控结束时间
	 */
	@Column(name = "monitor_end")
	private String monitorEnd;

	/**
	 * 标签
	 */
	@Column(name = "tag")
	private Object tag;

	/**
	 * 状态：0正常，1停止
	 */
	@Column(name = "is_delete")
	private Integer isDelete;

	/**
	 * 备注描述
	 */
	@Column(name = "remark")
	private String remark;

	/**
	 * 创建人
	 */
	@Column(name = "create_id")
	private String createId;

	/**
	 * 创建时间
	 */
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
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
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@Column(name = "update_time")
	private LocalDateTime updateTime;

	/**
	 * 获取点位ID
	 *
	 * @return id - 点位ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置点位ID
	 *
	 * @param id
	 *            点位ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * 获取商场
	 *
	 * @return mail - 商场
	 */
	public String getMall() {
		return mall;
	}

	/**
	 * 设置商场
	 *
	 * @param mail
	 *            商场
	 */
	public void setMall(String mall) {
		this.mall = mall;
	}

	/**
	 * 获取运营人员
	 *
	 * @return manager - 运营人员
	 */
	public String getManager() {
		return manager;
	}

	/**
	 * 设置运营人员
	 *
	 * @param manager
	 *            运营人员
	 */
	public void setManager(String manager) {
		this.manager = manager;
	}

	/**
	 * 获取运营人员手机
	 *
	 * @return mobile - 运营人员手机
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置运营人员手机
	 *
	 * @param mobile
	 *            运营人员手机
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getMonitor() {
		return monitor;
	}

	public void setMonitor(Integer monitor) {
		this.monitor = monitor;
	}

	public String getMonitorStart() {
		return monitorStart;
	}

	public void setMonitorStart(String monitorStart) {
		this.monitorStart = monitorStart;
	}

	public String getMonitorEnd() {
		return monitorEnd;
	}

	public void setMonitorEnd(String monitorEnd) {
		this.monitorEnd = monitorEnd;
	}

	public Object getTag() {

		return tag;
	}

	public void setTag(Object tag) {
		this.tag = tag;
	}

	/**
	 * 获取状态：0正常，1停止
	 *
	 * @return is_delete - 状态：0正常，1停止
	 */
	public Integer getIsDelete() {
		return isDelete;
	}

	/**
	 * 设置状态：0正常，1停止
	 *
	 * @param isDelete
	 *            状态：0正常，1停止
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * 获取备注描述
	 *
	 * @return remark - 备注描述
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注描述
	 *
	 * @param remark
	 *            备注描述
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
}