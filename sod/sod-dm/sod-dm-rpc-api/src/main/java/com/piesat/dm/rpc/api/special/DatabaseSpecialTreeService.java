package com.piesat.dm.rpc.api.special;

import com.piesat.common.grpc.annotation.GrpcHthtService;
import com.piesat.common.grpc.constant.SerializeType;
import com.piesat.util.constant.GrpcConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author yaya
 * @description TODO
 * @date 2020/3/30 9:46
 */
@GrpcHthtService(server = GrpcConstant.DM_SERVER, serialization = SerializeType.PROTOSTUFF)
public interface DatabaseSpecialTreeService {
    Map<String, Object> saveTreeData(String tdbId, HttpServletRequest request);

    Map<String, Object> updateOneRecordByTdbId(HttpServletRequest request);

    Map<String, Object> deleteRecordByTdbId(String tdbId, String typeId);

    Map<String, Object> updateTypeIdByTdbId(String tdbId, String dataClassId, String typeId);
}
