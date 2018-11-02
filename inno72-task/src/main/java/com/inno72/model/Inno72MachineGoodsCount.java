package com.inno72.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.inno72.common.CustomLocalDateTimeSerializer;

@Table(name = "inno72_machine_goods_count")
public class Inno72MachineGoodsCount {
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
	 * 补货前数量
	 */
	@Column(name = "before_num")
	private Integer beforeNum;

	/**
	 * 补货数量
	 */
	@Column(name = "add_num")
	private Integer addNum;

	/**
	 * 剩余数量
	 */
	private Integer num;

	/**
	 * 
	 */
	private Integer type;

	/**
	 * 统计时间
	 */
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "submit_time")
	private LocalDateTime submitTime;

	/**
	 * 创建时间
	 */
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "create_time")
	private LocalDateTime createTime;

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

	public Integer getBeforeNum() {
		return beforeNum;
	}

	public void setBeforeNum(Integer beforeNum) {
		this.beforeNum = beforeNum;
	}

	public Integer getAddNum() {
		return addNum;
	}

	public void setAddNum(Integer addNum) {
		this.addNum = addNum;
	}

	/**
	 * 获取商品数量
	 *
	 * @return num - 商品数量
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * 设置商品数量
	 *
	 * @param num
	 *            商品数量
	 */
	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 获取生成时间
	 *
	 * @return submit_time - 生成时间
	 */
	public LocalDateTime getSubmitTime() {
		return submitTime;
	}

	/**
	 * 设置生成时间
	 *
	 * @param submitTime
	 *            生成时间
	 */
	public void setSubmitTime(LocalDateTime submitTime) {
		this.submitTime = submitTime;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

}