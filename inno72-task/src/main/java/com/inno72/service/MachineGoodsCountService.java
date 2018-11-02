package com.inno72.service;

import com.inno72.common.Service;
import com.inno72.model.Inno72MachineGoodsCount;

/**
 * Created by CodeGenerator on 2018/10/30.
 */
public interface MachineGoodsCountService extends Service<Inno72MachineGoodsCount> {

	int saveMachineGoodsCount(Integer type);

}
