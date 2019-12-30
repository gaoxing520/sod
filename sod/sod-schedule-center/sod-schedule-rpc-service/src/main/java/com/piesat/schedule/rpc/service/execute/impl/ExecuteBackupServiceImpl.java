package com.piesat.schedule.rpc.service.execute.impl;

import com.piesat.schedule.dao.backup.BackupDao;
import com.piesat.schedule.entity.JobInfoEntity;
import com.piesat.schedule.entity.backup.BackupEntity;
import com.piesat.schedule.entity.backup.BackupLogEntity;
import com.piesat.schedule.rpc.mapstruct.BackupToLogMapstruct;
import com.piesat.schedule.rpc.service.JobInfoLogService;
import com.piesat.schedule.rpc.service.execute.ExecuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: sod
 * @description:
 * @author: zzj
 * @create: 2019-12-27 16:55
 **/
@Service("executeBackupService")
public class ExecuteBackupServiceImpl implements ExecuteService {
    @Autowired
    private JobInfoLogService jobInfoLogService;
    @Autowired
    private BackupToLogMapstruct backupToLogMapstruct;
    @Autowired
    private BackupDao backupDao;

    @Override
    public void insertLog(JobInfoEntity jobInfo){
        BackupEntity backupEntity= (BackupEntity) jobInfo;
        BackupLogEntity backupLogEntity=backupToLogMapstruct.toEntity(backupEntity);
        backupLogEntity.setJobId(backupEntity.getId());
        backupLogEntity.setId(null);
        backupLogEntity.setExecutorAddress("192.168.0.12:6000");
        backupLogEntity.setHandleCode("0");
        backupLogEntity.setTriggerTime(backupEntity.getTriggerLastTime());
        backupLogEntity.setHandleTime(new Date());
        backupLogEntity.setElapsedTime(10000);
        jobInfoLogService.saveNotNull(backupLogEntity);
    }

    @Override
    public JobInfoEntity getById(String id) {
        return backupDao.findById(id).get();
    }
}

