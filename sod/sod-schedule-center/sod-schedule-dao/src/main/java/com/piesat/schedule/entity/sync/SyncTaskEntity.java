package com.piesat.schedule.entity.sync;

import com.piesat.common.jpa.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author yaya
 * @description TODO
 * @date 2020/1/13 15:28
 */
@Data
@Entity
@Table(name = "T_SOD_JOB_SYNCTASK_INFO")
@DiscriminatorValue("SYNCTASK")
public class SyncTaskEntity extends BaseEntity {

    /**
     * 任务名称
     */
    @Column(name="task_name", length = 255)
    private String taskName;

    /**
     * 同步类型
     * 1：定时任务同步
     * 2：数据触发同步
     * 3：数据库日志同步
     */
    @Column(name="sync_type", length = 50)
    private Integer syncType;

    /**
     * 数据来源标识 有CTS、DPL、MUSIC
     */
    @Column(name="data_source_id", length = 20)
    private String dataSourceId;


    /**
     *  数据流向标识 有大数据平台、公共云、备份中心、文件目录
     */
    @Column(name="data_flow_direction_id", length = 20)
    private String dataFlowDirectionId;

    /**
     *  源库ID 缓存库BFDB
     */
    @Column(name="source_database_id", length = 50)
    private String sourceDatabaseId;

    /**
     * 目标库ID 服务库STDB、分析库HADB
     */
    @Column(name="target_database_id", length = 50)
    private String targetDatabaseId;

    /**
     * 源和目标同步映射关系主键
     * 对应syncMapping表的id
     */
    @Column(name="source_table", length = 50)
    private String sourceTable;

    /**
     * 源数据表时间戳对应字段名
     */
    @Column(name="source_table_datecolumn", length = 50)
    private String sourceTableDatecolumn;

    /**
     * 每批次数据条数
     */
    @Column(name="batch_amount", length = 11)
    private Integer  batchAmount;

    /**
     * 同步任务查询的开始时间
     */
    @Column(name="begin_time")
    private Date beginTime;

    /**
     * 同步任务最后一次成功同步的时间
     */
    @Column(name="last_success_time")
    private Date lastSuccessTime;

    /**
     * 是否有V_BBB字段，键值表默认配置为没有 0表示没有，1表示有
     */
    @Column(name="has_modify", length = 10)
    private String hasModify;

    /**
     * 值表映射关系主键
     * 键值表同步时有此字段
     * 对应syncMapping表的id
     */
    @Column(name="slave_tables", length = 50)
    private String slaveTables;

    /**
     * 外键信息
     * 键表与值表之间的外键信息，沿用xml的格式：<值表名称>外键列名1，外键列名2</值表名称> （键表和值表中当作外键关联的字段，字段名称必须相同）
     */
    @Column(name="link_key", columnDefinition="TEXT",nullable=true)//columnDefinition="clob null"
    private String linkKey;

    /**
     * 同步频次
     * 表示同步任务多长时间启动一次
     */
    @Column(name="sync_period", length = 11)
    private Integer syncPeriod;

    /**
     * DI发送标识
     */
    @Column(name="di_off", length = 10)
    private String diOff;

    /**
     * 重复数据丢弃标识
     */
    @Column(name="discard_on_duplicate", length = 10)
    private String discardOnDuplicate;

    /**
     * 同步任务执行主机IP
     */
    @Column(name="exec_ip", length = 50)
    private String execIp;

    /**
     * 执行主机端口号
     */
    @Column(name="exec_port", length = 10)
    private Integer execPort;

    /**
     * 运行状态
     * true---------运行中
     * false|xxx----xxx为空为停止中，xxx不为空为出错且xxx为出错原因
     * error--------未启动
     */
    @Transient
    private String runState;

    /**
     * 目标表ids
     */
    @Transient
    private String[] targetTableIds;
}
