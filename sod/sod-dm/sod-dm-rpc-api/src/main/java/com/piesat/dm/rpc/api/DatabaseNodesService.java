package com.piesat.dm.rpc.api;

import com.piesat.common.grpc.annotation.GrpcHthtService;
import com.piesat.common.grpc.constant.SerializeType;
import com.piesat.dm.rpc.dto.DatabaseNodesDto;

import java.util.List;

/**
 * 数据库节点信息
 *
 * @author cwh
 * @date 2019年 11月22日 15:32:15
 */
@GrpcHthtService(server = "database",serialization = SerializeType.PROTOSTUFF)
public interface DatabaseNodesService {
    DatabaseNodesDto saveDto(DatabaseNodesDto databaseNodesDto);
    DatabaseNodesDto getDotById(String id);
    void delete(String id);
    List<DatabaseNodesDto> all();
}