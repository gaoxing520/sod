package com.piesat.dm.rpc.mapper;

import com.piesat.common.jpa.BaseMapper;
import com.piesat.dm.entity.DataResourceApplyEntity;
import com.piesat.dm.rpc.dto.DataResourceApplyDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Service;

/**
 * @author yaya
 * @description TODO
 * @date 2020/3/30 13:23
 */
@Service
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DataResourceApplyMapper extends BaseMapper<DataResourceApplyDto,DataResourceApplyEntity> {
}
