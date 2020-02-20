package com.piesat.schedule.rpc.service.backup;

import com.piesat.common.grpc.annotation.GrpcHthtClient;
import com.piesat.common.jpa.BaseDao;
import com.piesat.common.jpa.BaseService;
import com.piesat.common.jpa.specification.SimpleSpecificationBuilder;
import com.piesat.common.jpa.specification.SpecificationOperator;
import com.piesat.common.utils.StringUtils;
import com.piesat.dm.rpc.api.DatabaseService;
import com.piesat.dm.rpc.api.TableForeignKeyService;
import com.piesat.dm.rpc.dto.DataTableDto;
import com.piesat.dm.rpc.dto.DatabaseDto;
import com.piesat.dm.rpc.dto.TableForeignKeyDto;
import com.piesat.schedule.dao.backup.BackupDao;
import com.piesat.schedule.entity.backup.BackupEntity;
import com.piesat.schedule.mapper.JobInfoMapper;
import com.piesat.schedule.rpc.api.JobInfoService;
import com.piesat.schedule.rpc.api.backup.BackupService;
import com.piesat.schedule.rpc.dto.backup.BackUpDto;
import com.piesat.schedule.rpc.mapstruct.backup.BackupMapstruct;
import com.piesat.schedule.rpc.service.DataBaseService;
import com.piesat.ucenter.rpc.api.system.DictDataService;
import com.piesat.ucenter.rpc.dto.system.DictDataDto;
import com.piesat.util.page.PageBean;
import com.piesat.util.page.PageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @program: sod
 * @description:
 * @author: zzj
 * @create: 2019-12-23 09:17
 **/
@Service
public class BackupServiceImpl extends BaseService<BackupEntity> implements BackupService{
    @Autowired
    private BackupDao backupDao;
    @Autowired
    private BackupMapstruct backupMapstruct;
    @Autowired
    private JobInfoService jobInfoService;
    @Autowired
    private DataBaseService dataBaseService;
    @GrpcHthtClient
    private DictDataService dictDataService;
    @Autowired
    private JobInfoMapper jobInfoMapper;
    @GrpcHthtClient
    private DatabaseService databaseService;
    @GrpcHthtClient
    private TableForeignKeyService tableForeignKeyService;
    @Override
    public BaseDao<BackupEntity> getBaseDao() {
        return backupDao;
    }
    @Override
    public PageBean selectBackupList(PageForm<BackUpDto> pageForm){
        BackupEntity backupEntity=backupMapstruct.toEntity(pageForm.getT());
        SimpleSpecificationBuilder specificationBuilder=new SimpleSpecificationBuilder();
        if(StringUtils.isNotNullString(backupEntity.getDatabaseId())){
            specificationBuilder.add("databaseId", SpecificationOperator.Operator.eq.name(),backupEntity.getDatabaseId());
        }
        if(StringUtils.isNotNullString(backupEntity.getDataClassId())){
            specificationBuilder.add("dataClassId", SpecificationOperator.Operator.likeAll.name(),backupEntity.getDataClassId());
            specificationBuilder.addOr("ddataId", SpecificationOperator.Operator.likeAll.name(),backupEntity.getDataClassId());
        }
        if(StringUtils.isNotNullString(backupEntity.getProfileName())){
            specificationBuilder.add("profileName", SpecificationOperator.Operator.likeAll.name(),backupEntity.getProfileName());
        }
        if(null!=backupEntity.getTriggerStatus()){
            specificationBuilder.add("triggerStatus",SpecificationOperator.Operator.eq.name(),backupEntity.getTriggerStatus());
        }
        if(StringUtils.isNotNullString(backupEntity.getTableName())){
            specificationBuilder.add("tableName", SpecificationOperator.Operator.likeAll.name(),backupEntity.getTableName());
        }
        if(StringUtils.isNotNullString((String) backupEntity.getParamt().get("beginTime"))){
            specificationBuilder.add("createTime",SpecificationOperator.Operator.ges.name(),(String) backupEntity.getParamt().get("beginTime"));
        }
        if(StringUtils.isNotNullString((String) backupEntity.getParamt().get("endTime"))){
            specificationBuilder.add("createTime",SpecificationOperator.Operator.les.name(),(String) backupEntity.getParamt().get("endTime"));
        }
        Sort sort=Sort.by(Sort.Direction.ASC,"createTime");
        PageBean pageBean=this.getPage(specificationBuilder.generateSpecification(),pageForm,sort);
        List<BackupEntity> backupEntities= (List<BackupEntity>) pageBean.getPageData();
        pageBean.setPageData(backupMapstruct.toDto(backupEntities));
        return pageBean;

    }

    public BackUpDto selectBackupByParam(String databaseId,String dataClassId){
        PageForm pageForm=new PageForm(1,1);
        SimpleSpecificationBuilder specificationBuilder=new SimpleSpecificationBuilder();
        if(StringUtils.isNotNullString(databaseId)){
            specificationBuilder.add("databaseId", SpecificationOperator.Operator.eq.name(),databaseId);
        }
        if(StringUtils.isNotNullString(dataClassId)){
            specificationBuilder.add("dataClassId", SpecificationOperator.Operator.likeAll.name(),dataClassId);
            specificationBuilder.addOr("ddataId", SpecificationOperator.Operator.likeAll.name(),dataClassId);
        }
        PageBean pageBean=this.getPage(specificationBuilder.generateSpecification(),pageForm,Sort.unsorted());
        List<BackupEntity> backupEntities= (List<BackupEntity>) pageBean.getPageData();
        if(null!=backupEntities&&!backupEntities.isEmpty()){
            return backupMapstruct.toDto(backupEntities.get(0));
        }
        return null;
    }
    @Override
    public BackUpDto findBackupById(String backupId){
        BackupEntity backupEntity=this.getById(backupId);
        return backupMapstruct.toDto(backupEntity);

    }
    @Override
    public void saveBackup(BackUpDto backUpDto){
        BackupEntity backupEntity=backupMapstruct.toEntity(backUpDto);
        backupEntity=this.saveNotNull(backupEntity);
        jobInfoService.start(backupMapstruct.toDto(backupEntity));

    }
    @Override
    public void updateBackup(BackUpDto backUpDto){
        BackupEntity backupEntity=backupMapstruct.toEntity(backUpDto);
        this.saveNotNull(backupEntity);
        jobInfoService.start(backUpDto);
    }
    @Override
    public void deleteBackupByIds(String[] backupIds){
        this.deleteByIds(Arrays.asList(backupIds));
        jobInfoService.stopByIds(Arrays.asList(backupIds));
    }

    public List<Map<String,Object>> findDatabase(){
        List<DictDataDto> dictDataDtos=dictDataService.selectDictDataByType("backup_database");
        List<String> dicts=new ArrayList<>();
        for(DictDataDto dictDataDto:dictDataDtos) {
            dicts.add(dictDataDto.getDictValue());
        }

        List<Map<String,Object>> databaseDtos=new ArrayList<>();
        List<DatabaseDto> databaseListAll= dataBaseService.findAllDataBase();
        for(DatabaseDto databaseDto:databaseListAll){
                String databaseName=databaseDto.getDatabaseDefine().getDatabaseName()+"_"+databaseDto.getDatabaseName();
                String parentId=databaseDto.getDatabaseDefine().getId();
                if(dicts.contains(parentId.toUpperCase())) {
                    LinkedHashMap<String,Object> map=new LinkedHashMap<>();
                    map.put("KEY",databaseDto.getId());
                    map.put("VALUE",databaseName);
                    databaseDtos.add(map);
                }

            }

        return databaseDtos;

    }

    public List<Map<String,Object>> findDataClassId(String dataBaseId,String dataClassId){

        List<Map<String,Object>> dataClassIds=new ArrayList<>();

        List<Map<String, Object>> mapList=dataBaseService.getByDatabaseId(dataBaseId);
        List<String> isHave=jobInfoMapper.selectBackupDataClassId();
        for(Map<String, Object> map:mapList){
            String dataClass= (String) map.get("DATA_CLASS_ID");
            String className= (String) map.get("CLASS_NAME");
            String ddataId=(String)map.get("D_DATA_ID");
            if(!isHave.contains(dataClass)||dataClass.equals(dataClassId)){
                LinkedHashMap<String,Object> dataClassIdMap=new LinkedHashMap<>();
                dataClassIdMap.put("KEY",dataClass);
                dataClassIdMap.put("VALUE",className);
                dataClassIdMap.put("D_DATA_ID",ddataId);
                dataClassIds.add(dataClassIdMap);
            }
        }

        return dataClassIds;

    }

    public void getDataBaseAndClassId(BackupEntity backupEntity){
        DatabaseDto databaseDto= databaseService.getDotById(backupEntity.getDatabaseId());
        Map<String,String> map =dataBaseService.getByDatabaseIdAndClassId(backupEntity.getDatabaseId(),backupEntity.getDataClassId());
        String vTableName=map.get("vTableName");
        String logicId=map.get("logicId");
        String tableId=map.get("tableId");
        if(StringUtils.isNotNullString(vTableName)){
            backupEntity.setVTableName(databaseDto.getSchemaName()+"."+vTableName);
            List<TableForeignKeyDto> tableForeignKeyDtos=tableForeignKeyService.findByClassLogicId(logicId);
        }

    }
}

