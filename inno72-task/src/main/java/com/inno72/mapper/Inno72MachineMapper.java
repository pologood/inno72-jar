package com.inno72.mapper;

import java.util.List;

import com.inno72.common.Mapper;
import com.inno72.model.Inno72Machine;

public interface Inno72MachineMapper extends Mapper<Inno72Machine> {
	int updateNetStatus(List<String> machineIds);
}