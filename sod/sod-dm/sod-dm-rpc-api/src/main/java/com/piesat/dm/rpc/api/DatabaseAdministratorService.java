package com.piesat.dm.rpc.api;


import com.piesat.common.grpc.annotation.GrpcHthtService;
import com.piesat.common.grpc.constant.SerializeType;
import com.piesat.dm.rpc.dto.DatabaseAdministratorDto;

import java.util.List;

/**
 * 数据库管理账户
 *
 * @author cwh
 * @date 2019年 11月20日 17:06:14
 */
@GrpcHthtService(server = "database", serialization = SerializeType.PROTOSTUFF)
public interface DatabaseAdministratorService {
    List<DatabaseAdministratorDto> getByExample(DatabaseAdministratorDto databaseAdministratorDto);
    DatabaseAdministratorDto saveDto(DatabaseAdministratorDto databaseAdministratorDto);
    DatabaseAdministratorDto getDotById(String id);
    void delete(String id);
    List<DatabaseAdministratorDto> all();
}