package com.piesat.dm.rpc.api;

import com.piesat.common.grpc.annotation.GrpcHthtService;
import com.piesat.common.grpc.constant.SerializeType;
import com.piesat.dm.rpc.dto.DatabaseDto;

import java.util.List;

/**
 * 数据库基础库专题库
 *
 * @author cwh
 * @date 2019年 11月22日 15:31:30
 */
@GrpcHthtService(server = "database",serialization = SerializeType.PROTOSTUFF)
public interface DatabaseService {
    DatabaseDto saveDto(DatabaseDto databaseDto);
    DatabaseDto getDotById(String id);
    void delete(String id);
    List<DatabaseDto> all();
}