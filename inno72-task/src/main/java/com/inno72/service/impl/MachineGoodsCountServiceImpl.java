package com.inno72.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inno72.common.AbstractService;
import com.inno72.mapper.Inno72MachineGoodsCountMapper;
import com.inno72.model.Inno72MachineGoodsCount;
import com.inno72.service.MachineGoodsCountService;

/**
 * Created by CodeGenerator on 2018/10/30.
 */
@Service
@Transactional
public class MachineGoodsCountServiceImpl extends AbstractService<Inno72MachineGoodsCount>
		implements MachineGoodsCountService {
	@Resource
	private Inno72MachineGoodsCountMapper inno72MachineGoodsCountMapper;

	@Override
	public int saveMachineGoodsCount(Integer type) {
		// type:0 23点跑当天记录（删除当天临时记录）；1，下午五点跑当天临时记录
		if (type == 0) {
			// 删除当天临时记录
			Inno72MachineGoodsCount temp = new Inno72MachineGoodsCount();
			temp.setType(1);
			inno72MachineGoodsCountMapper.delete(temp);
		}
		List<Inno72MachineGoodsCount> list = inno72MachineGoodsCountMapper.selectMachineGoods(type);

		int n = 0;
		if (list.size() > 0) {
			n = inno72MachineGoodsCountMapper.insertMachineGoodsCountList(list);
		}
		return n;

	}

}
