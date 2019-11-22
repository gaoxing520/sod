package com.piesat.dm.rpc.mapper;

import com.piesat.common.jpa.BaseMapper;
import com.piesat.dm.entity.LogicDefineEntity;
import com.piesat.dm.rpc.dto.LogicDefineDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Service;

/**
 * 数据用途定义
 *
 * @author cwh
 * @date 2019年 11月22日 15:50:15
 */
@Service
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogicDefineMapper extends BaseMapper<LogicDefineDto, LogicDefineEntity> {
}
