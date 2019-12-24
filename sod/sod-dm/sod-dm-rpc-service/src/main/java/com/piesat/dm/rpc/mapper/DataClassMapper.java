package com.piesat.dm.rpc.mapper;

import com.piesat.common.jpa.BaseMapper;
import com.piesat.dm.entity.DataClassEntity;
import com.piesat.dm.rpc.dto.DataClassDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Service;

/**
 * 资料分类
 *
 * @author cwh
 * @date 2019年 11月22日 15:47:19
 */
@Service
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DataClassMapper extends BaseMapper<DataClassDto, DataClassEntity> {
}