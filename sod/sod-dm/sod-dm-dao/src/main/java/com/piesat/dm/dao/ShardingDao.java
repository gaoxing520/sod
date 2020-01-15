package com.piesat.dm.dao;

import com.piesat.common.jpa.BaseDao;
import com.piesat.dm.entity.ShardingEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 分库分表键
 *
 * @author cwh
 * @date 2019年 12月16日 15:49:15
 */
@Repository
public interface ShardingDao extends BaseDao<ShardingEntity> {
    List<ShardingEntity> findByTableId(String tableId);
    void deleteByTableId(String tableId);
}
