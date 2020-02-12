package com.piesat.dm.rpc.mapper;

import com.piesat.common.jpa.BaseMapper;
import com.piesat.dm.entity.DataClassNormEntity;
import com.piesat.dm.rpc.dto.DataClassNormDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Service;

/**
 * 资料用途分类
 *
 * @author cwh
 * @date 2019年 11月22日 15:48:16
 */
@Service
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DataClassNormMapper extends BaseMapper<DataClassNormDto, DataClassNormEntity> {
}
