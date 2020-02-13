package com.piesat.dm.dao;

import com.piesat.common.jpa.BaseDao;
import com.piesat.dm.entity.DatabaseSpecialAuthorityEntity;
import com.piesat.dm.entity.DatabaseSpecialEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 专题库权限
 */
@Repository
public interface DatabaseSpecialAuthorityDao extends BaseDao<DatabaseSpecialAuthorityEntity> {

    /**
     *
     * @param sdbId
     * @return
     */
    List<DatabaseSpecialAuthorityEntity> findBySdbId(String sdbId);

    /**
     * 根据专题库ID删除
     * @param sdbId
     */
    void deleteBySdbId(String sdbId);
}
