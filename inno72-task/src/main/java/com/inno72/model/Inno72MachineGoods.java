package com.inno72.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "inno72_machine_goods")
public class Inno72MachineGoods {
	/**
	 * uuid
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	/**
	 * 机器ID
	 */
	@Column(name = "machine_id")
	private String machineId;

	/**
	 * 商品ID
	 */
	@Column(name = "goods_id")
	private String goodsId;

	/**
	 * 商品名称
	 */
	@Column(name = "goods_name")
	private String goodsName;

	/**
	 * 活动名称
	 */
	@Column(name = "activity_name")
	private String activityName;
	/**
	 * 互动计划，互派活动ID
	 */
	@Column(name = "activity_id")
	private String activityId;

	/**
	 * 活动类型：0互动计划，1互派活动
	 */
	@Column(name = "activity_type")
	private Integer activityType;

	/**
	 * 生成时间
	 */
	@Column(name = "submit_time")
	private Date submitTime;

	/**
	 * 获取uuid
	 *
	 * @return id - uuid
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置uuid
	 *
	 * @param id
	 *            uuid
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取机器ID
	 *
	 * @return machine_id - 机器ID
	 */
	public String getMachineId() {
		return machineId;
	}

	/**
	 * 设置机器ID
	 *
	 * @param machineId
	 *            机器ID
	 */
	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	/**
	 * 获取商品ID
	 *
	 * @return goods_id - 商品ID
	 */
	public String getGoodsId() {
		return goodsId;
	}

	/**
	 * 设置商品ID
	 *
	 * @param goodsId
	 *            商品ID
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 * 获取商品名称
	 *
	 * @return goods_name - 商品名称
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * 设置商品名称
	 *
	 * @param goodsName
	 *            商品名称
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	/**
	 * 获取互动计划，互派活动ID
	 *
	 * @return activity_id - 互动计划，互派活动ID
	 */
	public String getActivityId() {
		return activityId;
	}

	/**
	 * 设置互动计划，互派活动ID
	 *
	 * @param activityId
	 *            互动计划，互派活动ID
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	/**
	 * 获取活动类型：0互动计划，1互派活动
	 *
	 * @return activity_type - 活动类型：0互动计划，1互派活动
	 */
	public Integer getActivityType() {
		return activityType;
	}

	/**
	 * 设置活动类型：0互动计划，1互派活动
	 *
	 * @param activityType
	 *            活动类型：0互动计划，1互派活动
	 */
	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	/**
	 * 获取生成时间
	 *
	 * @return submit_time - 生成时间
	 */
	public Date getSubmitTime() {
		return submitTime;
	}

	/**
	 * 设置生成时间
	 *
	 * @param submitTime
	 *            生成时间
	 */
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
}