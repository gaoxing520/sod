package com.piesat.dm.rpc.api;

import com.piesat.common.grpc.annotation.GrpcHthtService;
import com.piesat.common.grpc.constant.SerializeType;
import com.piesat.dm.rpc.dto.DatabaseDefineDto;
import com.piesat.util.constant.GrpcConstant;
import com.piesat.util.page.PageBean;

import java.util.List;

/**
 * 数据库类型定义
 *
 * @author cwh
 * @date 2019年 11月22日 15:30:47
 */
@GrpcHthtService(server = GrpcConstant.DM_SERVER, serialization = SerializeType.PROTOSTUFF)
public interface DatabaseDefineService {
    DatabaseDefineDto saveDto(DatabaseDefineDto databaseDefineDto);

    DatabaseDefineDto getDotById(String id);

    void delete(String id);

    List<DatabaseDefineDto> all();

    List<DatabaseDefineDto> findByType(String databaseType);

    PageBean getPage(DatabaseDefineDto databaseDefineDto,int pageNum,int pageSize);
}
