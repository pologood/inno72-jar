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
	public int saveMachineGoodsCoun() {

		List<Inno72MachineGoodsCount> list = inno72MachineGoodsCountMapper.selectMachineGoods(null);

		int n = 0;
		if (list.size() > 0) {
			n = inno72MachineGoodsCountMapper.insertMachineGoodsCountList(list);
		}
		return n;

	}

}
