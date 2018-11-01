package com.inno72.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.inno72.common.Mapper;
import com.inno72.model.Inno72MachineGoods;

public interface Inno72MachineGoodsMapper extends Mapper<Inno72MachineGoods> {

	List<Inno72MachineGoods> selectMachineGoods(Map<String, Object> pm);

	int insertMachineGoodsList(@Param("list") List<Inno72MachineGoods> list);
}