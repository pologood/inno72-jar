package com.inno72.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.inno72.common.Mapper;
import com.inno72.model.Inno72MachineGoodsCount;

public interface Inno72MachineGoodsCountMapper extends Mapper<Inno72MachineGoodsCount> {

	List<Inno72MachineGoodsCount> selectMachineGoods(Map<String, Object> pm);

	int insertMachineGoodsCountList(@Param("list") List<Inno72MachineGoodsCount> list);

}