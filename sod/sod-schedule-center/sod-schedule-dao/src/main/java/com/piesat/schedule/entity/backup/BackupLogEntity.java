package com.piesat.schedule.entity.backup;

import com.piesat.schedule.entity.JobInfoEntity;
import com.piesat.schedule.entity.JobInfoLogEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @program: sod
 * @description:
 * @author: zzj
 * @create: 2019-12-27 15:00
 **/
@Data
@Entity
@Table(name="T_SOD_JOB_BACKUP_INFO_LOG")
@DiscriminatorValue("BACKUP")
public class BackupLogEntity extends JobInfoLogEntity{
    @Column(name="database_id", length=50)
    private String databaseId;
    @Column(name="data_class_id", length=50)
    private String dataClassId;
    @Column(name="profile_name", length=255)
    private String profileName;
    @Column(name="d_data_id", length=50)
    private String ddataId;
    @Column(name="conditions", length=255)
    private String conditions;
    @Column(name="second_conditions", length=255)
    private String secondConditions;
    @Column(name="storage_directory", length=255)
    private String storageDirectory;
    @Column(name="table_name", length=50)
    private String tableName;
    @Column(name="v_table_name", length=50)
    private String vTableName;
}
