package com.piesat.dm.dao.special;

import com.piesat.common.jpa.BaseDao;
import com.piesat.dm.entity.special.DatabaseSpecialAuthorityEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    void deleteBySdbId(String sdbId);
}
