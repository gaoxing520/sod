package com.piesat.dm.entity;

import com.piesat.common.jpa.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * @author yaya
 * @description TODO
 * @date 2020/2/14 10:26
 */
@Data
@Table(name = "T_SOD_CONSISTENCY_CHECK")
@Entity
public class ConsistencyCheckEntity extends BaseEntity {

    /**
     * 与数据库定义关联id
     * database_id
     */
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "database_id")
    private DatabaseEntity databaseEntity;

    /**
     * 数据库名称
     */
    @Column( name = "database_name", length = 50)
    private String databaseName;
}
