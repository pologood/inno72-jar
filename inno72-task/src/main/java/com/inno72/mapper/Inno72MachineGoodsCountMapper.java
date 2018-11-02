package com.inno72.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.inno72.common.Mapper;
import com.inno72.model.Inno72MachineGoodsCount;

public interface Inno72MachineGoodsCountMapper extends Mapper<Inno72MachineGoodsCount> {

	List<Inno72MachineGoodsCount> selectMachineGoods(@Param("type") Integer type);

	int insertMachineGoodsCountList(@Param("list") List<Inno72MachineGoodsCount> list);

}