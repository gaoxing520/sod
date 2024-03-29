package com.piesat.schedule.entity.syncdar;

import com.piesat.common.annotation.Excel;
import com.piesat.schedule.entity.JobInfoEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 非结构化数据交换系统同步
 * @program: sod
 * @description:
 * @author: zzj
 * @create: 2019-12-20 15:14
 **/
@Data
@Entity
@Table(name="T_SOD_JOB_SYNCDAR_INFO")
@DiscriminatorValue("SYNCDAR")
public class SyncDarEntity extends JobInfoEntity{
    @Excel(name = "物理库ID")
    @Column(name="database_id", length=50)
    private String databaseId;
    @Column(name="parent_id", length=50)
    private String parentId;
    @Excel(name = "存储编码")
    @Column(name="data_class_id", length=50)
    private String dataClassId;
    @Excel(name = "资料名称")
    @Column(name="profile_name", length=255)
    private String profileName;
    @Excel(name = "四级编码")
    @Column(name="d_data_id", length=50)
    private String ddataId;
    @Excel(name = "存储目录")
    @Column(name="save_directory", length=255)
    private String saveDirectory;
    @Excel(name = "数据库类型")
    @Column(name="database_type", length=50)
    private String databaseType;
    @Excel(name = "键表名")
    @Column(name="table_name", length=50)
    private String tableName;
    @Excel(name = "要素表名")
    @Column(name="v_table_name", length=50)
    private String vTableName;
    @Column(name="last_time")
    private Date lastTime;
    @Column(name="end_time")
    private Date endTime;
    @Column(name="buffer_time")
    private long bufferTime;
    /**
     * 时间字段
     */
    @Column(name = "time_column")
    private String timeColumn;
    /**
     * 频率
     */
    @Column(name="frequency")
    private Integer frequency;
    /**
     * 单位
     */
    @Column(name="unit")
    private String unit;

}

