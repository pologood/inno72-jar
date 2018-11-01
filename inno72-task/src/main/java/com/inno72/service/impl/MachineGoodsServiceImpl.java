package com.inno72.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inno72.common.AbstractService;
import com.inno72.mapper.Inno72MachineGoodsMapper;
import com.inno72.model.Inno72MachineGoods;
import com.inno72.service.MachineGoodsService;

/**
 * Created by CodeGenerator on 2018/10/31.
 */
@Service
@Transactional
public class MachineGoodsServiceImpl extends AbstractService<Inno72MachineGoods> implements MachineGoodsService {
	@Resource
	private Inno72MachineGoodsMapper inno72MachineGoodsMapper;

	@Override
	public int saveMachineGoods() {
		inno72MachineGoodsMapper.delete(null);
		List<Inno72MachineGoods> list = inno72MachineGoodsMapper.selectMachineGoods(null);

		int n = 0;
		if (list.size() > 0) {
			n = inno72MachineGoodsMapper.insertMachineGoodsList(list);
		}
		return n;

	}

}
