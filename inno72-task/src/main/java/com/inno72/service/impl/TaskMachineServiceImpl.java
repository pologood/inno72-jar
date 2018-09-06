package com.inno72.service.impl;

import com.inno72.mapper.Inno72TaskMachineMapper;
import com.inno72.model.Inno72TaskMachine;
import com.inno72.service.TaskMachineService;
import com.inno72.common.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/08/24.
 */
@Service
@Transactional
public class TaskMachineServiceImpl extends AbstractService<Inno72TaskMachine> implements TaskMachineService {
    @Resource
    private Inno72TaskMachineMapper inno72TaskMachineMapper;

}
