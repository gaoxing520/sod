package com.piesat.dm.dao.dataclass;

import com.piesat.common.jpa.BaseDao;
import com.piesat.dm.entity.dataclass.DataClassEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataClassDao extends BaseDao<DataClassEntity> {

    void deleteByDataClassId(String dataClassId);

    DataClassEntity findByDataClassId(String dataClassId);

    List<DataClassEntity> findByDDataId(String dDataId);

    List<DataClassEntity> findByParentIdOrderByDataClassIdDesc(String parentId);
}