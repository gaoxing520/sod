package com.piesat.dm.rpc.service.dataapply;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.piesat.common.jpa.BaseDao;
import com.piesat.common.jpa.BaseService;
import com.piesat.dm.dao.dataapply.NewdataApplyDao;
import com.piesat.dm.entity.dataapply.NewdataApplyEntity;
import com.piesat.dm.mapper.MybatisQueryMapper;
import com.piesat.dm.rpc.api.*;
import com.piesat.dm.rpc.api.dataapply.NewdataApplyService;
import com.piesat.dm.rpc.api.database.DatabaseService;
import com.piesat.dm.rpc.api.dataclass.DataClassService;
import com.piesat.dm.rpc.api.dataclass.DataLogicService;
import com.piesat.dm.rpc.api.dataclass.LogicDefineService;
import com.piesat.dm.rpc.api.datatable.DataTableService;
import com.piesat.dm.rpc.api.datatable.ShardingService;
import com.piesat.dm.rpc.api.datatable.TableColumnService;
import com.piesat.dm.rpc.dto.*;
import com.piesat.dm.rpc.dto.dataapply.NewdataApplyDto;
import com.piesat.dm.rpc.dto.database.DatabaseDto;
import com.piesat.dm.rpc.dto.dataclass.DataClassDto;
import com.piesat.dm.rpc.dto.dataclass.DataLogicDto;
import com.piesat.dm.rpc.dto.datatable.DataTableDto;
import com.piesat.dm.rpc.dto.datatable.ShardingDto;
import com.piesat.dm.rpc.dto.datatable.TableColumnDto;
import com.piesat.dm.rpc.mapper.dataapply.NewdataApplyMapper;
import com.piesat.util.ResultT;
import com.piesat.util.page.PageBean;
import com.piesat.util.page.PageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yaya
 * @description TODO
 * @date 2020/2/5 19:03
 */
@Service
public class NewdataApplyServiceImpl extends BaseService<NewdataApplyEntity> implements NewdataApplyService {
    @Autowired
    private NewdataApplyDao newdataApplyDao;

    @Autowired
    private NewdataApplyMapper newdataApplyMapper;

    @Autowired
    private DataClassService dataClassService;

    @Autowired
    private LogicDefineService logicDefineService;

    @Autowired
    private DataLogicService dataLogicService;

    @Autowired
    private StorageConfigurationService storageConfigurationService;

    @Autowired
    private DataTableService dataTableService;

    @Autowired
    private TableColumnService tableColumnService;
    @Autowired
    private DatabaseService databaseService;
    @Autowired
    private ShardingService shardingService;
    @Autowired
    private MybatisQueryMapper mybatisQueryMapper;


    @Override
    public BaseDao<NewdataApplyEntity> getBaseDao() {
        return newdataApplyDao;
    }

    @Override
    public PageBean selectPageList(PageForm<Map<String,Object>> pageForm) {
        PageHelper.startPage(pageForm.getCurrentPage(),pageForm.getPageSize());
        List<Map<String,Object>> lists = mybatisQueryMapper.selectNewdataApplyPageList(pageForm.getT());//自定义的接口
        if(lists != null && lists.size()>0){
            for(int i=0;i<lists.size();i++){
                if(lists.get(i).get("DATA_CLASS_ID") != null && !"".equals(lists.get(i).get("DATA_CLASS_ID"))){
                    List<StorageConfigurationDto> storageConfigurationDtos = storageConfigurationService.findByDataClassId((String) lists.get(i).get("DATA_CLASS_ID"));
                    lists.get(i).put("storageConfigurations",storageConfigurationDtos);
                }
            }
        }
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(lists);
        //获取当前页数据
        PageBean pageBean=new PageBean(pageInfo.getTotal(),pageInfo.getPages(),lists);
        return pageBean;
    }

    @Transactional
    @Override
    public NewdataApplyDto updateStatus(NewdataApplyDto newdataApplyDto) {
        NewdataApplyEntity newdataApplyEntity = newdataApplyMapper.toEntity(newdataApplyDto);
        //newdataApplyEntity = this.saveNotNull(newdataApplyEntity);
        newdataApplyEntity.setExamineTime(new Date());
        newdataApplyEntity.setExaminer("");
        mybatisQueryMapper.updateNewdataApplyStatus(newdataApplyEntity);
        return newdataApplyMapper.toDto(newdataApplyEntity);
    }

    @Override
    public List<Map<String, Object>> getLogicInfo() {
        return null;
    }

    @Override
    public Map<String, Object> queryCheckByApplyId(String id) {
        List<Map<String, Object>> maps = mybatisQueryMapper.queryNewdataApplyByApplyId(id);
        return maps == null ? null : maps.get(0);
    }

    @Override
    public ResultT<String> addGroup(DataClassDto dataClassDto, NewdataApplyDto newdataApplyDto) {
        //判断存储编码是否重复
        DataClassDto dataClassResult = dataClassService.findByDataClassId(dataClassDto.getDataClassId());
        if(dataClassResult != null){
            return ResultT.failed("存储编码重复，请检查重新添加！");
        }
        //添加资料分类
        dataClassDto = dataClassService.saveDto(dataClassDto);

        if(dataClassDto != null && dataClassDto.getType() == 2){
            List<DataLogicDto> dataLogicDtoList = newdataApplyDto.getDataLogicDtoList();
            for(DataLogicDto dataLogicDto : dataLogicDtoList){
                //添加资料用途分类
                dataLogicService.saveDto(dataLogicDto);
                //添加存储策略
                StorageConfigurationDto storageConfigurationDto = new StorageConfigurationDto();
                storageConfigurationDto.setLogicId(dataLogicDto.getLogicFlag());
                storageConfigurationDto.setDatabaseId(dataLogicDto.getDatabaseId());
                storageConfigurationDto.setDataClassId(dataLogicDto.getDataClassId());
                storageConfigurationDto.setStorageDefineIdentifier(2);
                storageConfigurationDto.setSyncIdentifier(2);
                storageConfigurationDto.setMovecleanIdentifier(2);
                storageConfigurationDto.setBackupIdentifier(2);
                storageConfigurationDto.setArchivingIdentifier(2);
                storageConfigurationService.saveDto(storageConfigurationDto);
            }
        }
        //修改申请表的d_data_id和data_class_id
        newdataApplyDao.updateDDateIdAndDataClassId(newdataApplyDto.getDDataId(), newdataApplyDto.getDataClassId(), newdataApplyDto.getId());
        return ResultT.success();
    }

    @Override
    public ResultT<String> updateGroup(DataClassDto dataClassDto, NewdataApplyDto newdataApplyDto, String old_data_class_id) {
        //判断存储编码是否有改动
        if(!dataClassDto.getDataClassId().equals(old_data_class_id)){
            DataClassDto dataClassResult = dataClassService.findByDataClassId(dataClassDto.getDataClassId());
            if(dataClassResult != null){
                return ResultT.failed("存储编码重复，请检查重新添加！");
            }
            //删除修改前的资料分类,会同时删掉表结构相关数据
            dataClassService.deleteByDataClassId(old_data_class_id);
            //添加新的资料分类
            dataClassDto = dataClassService.saveDto(dataClassDto);
        }
        //删除修改前的资料用途分类
        dataLogicService.deleteByDataClassId(old_data_class_id);
        //添加修改后的资料用途分类
        List<DataLogicDto> dataLogicDtoList = newdataApplyDto.getDataLogicDtoList();
        for(DataLogicDto dataLogicDto : dataLogicDtoList){
            //添加资料用途分类
            dataLogicService.saveDto(dataLogicDto);

            //删除修改前的存储策略,如果一条策略的logic_id  database_id  dataclass_id均为改动，则不删除
            //storageConfigurationService.
            //添加修改后的存储策略
            StorageConfigurationDto storageConfigurationDto = new StorageConfigurationDto();
            storageConfigurationDto.setLogicId(dataLogicDto.getLogicFlag());
            storageConfigurationDto.setDatabaseId(dataLogicDto.getDatabaseId());
            storageConfigurationDto.setDataClassId(dataLogicDto.getDataClassId());
            storageConfigurationDto.setStorageDefineIdentifier(2);
            storageConfigurationDto.setSyncIdentifier(2);
            storageConfigurationDto.setMovecleanIdentifier(2);
            storageConfigurationDto.setBackupIdentifier(2);
            storageConfigurationDto.setArchivingIdentifier(2);
            storageConfigurationService.saveDto(storageConfigurationDto);
        }

        //修改申请表的d_data_id和data_class_id
        newdataApplyDao.updateDDateIdAndDataClassId(newdataApplyDto.getDDataId(), newdataApplyDto.getDataClassId(), newdataApplyDto.getId());
        return ResultT.success();
    }

    @Override
    public ResultT<String> addOrUpdateDataTable(DataTableDto dataTableDto) {
        dataTableDto.setTableName(dataTableDto.getTableName().toUpperCase());
        //为新增资料申请用户
        //dataTableDto.setUserId();

        //查表信息
        String databaseId = dataTableDto.getClassLogic().getDatabaseId();
        String dataClassId = dataTableDto.getClassLogic().getDataClassId();
        //最后返回一条或两条(KV)
        List<DataTableDto> dataTableDtos = dataTableService.getByDatabaseIdAndClassId(databaseId, dataClassId);
        if (dataTableDtos != null && dataTableDtos.size() > 0) {
            //修改,需要有id
            for(DataTableDto tableDto : dataTableDtos){
                dataTableService.saveDto(tableDto);
            }
        }else{
            //添加//如果是kv表呢？
            dataTableDto = dataTableService.saveDto(dataTableDto);
            //根据物理库id查父id
            DatabaseDto dotById = databaseService.getDotById(databaseId);
            String parent_id = dotById.getDatabaseDefine().getId();
            //添加分库分表记录
            if ("STDB".equals(parent_id)) {
                ShardingDto sharding = new ShardingDto();
                sharding.setTableId(dataTableDto.getId());
                sharding.setColumnName("D_DATETIME");
                sharding.setShardingType(0);
                shardingService.saveDto(sharding);
            } else if ("HADB".equals(parent_id)) {
                ShardingDto sharding = new ShardingDto();
                sharding.setTableId(dataTableDto.getId());
                sharding.setColumnName("V01301");
                sharding.setShardingType(0);
                shardingService.saveDto(sharding);
            }
        }
        //进行物理库表读写权限授权
        return ResultT.success();
    }

    @Override
    public List<DataTableDto> getDataTableByType(DataLogicDto dataLogicDto) {
        List<DataTableDto> byDatabaseIdAndClassId = dataTableService.getByDatabaseIdAndClassId(dataLogicDto.getDatabaseId(), dataLogicDto.getDataClassId());
        return byDatabaseIdAndClassId;
    }

    @Override
    public List<TableColumnDto> getNewdataTableField(String id) {
        List<TableColumnDto> tableField = tableColumnService.findByTableId(id);
        return tableField;
    }

    @Override
    public ResultT<String> addDataStructure(TableColumnDto tableColumnDto) {
        TableColumnDto tableColumnDto1 = tableColumnService.saveDto(tableColumnDto);
        return ResultT.success();
    }

    @Override
    public ResultT<String> updateDataStructure(TableColumnDto tableColumnDto) {
        tableColumnService.updateDto(tableColumnDto);
        return ResultT.success();
    }

    @Override
    public PageBean getTableDataInfo(String tableId, String databaseId, int pageNum, int pageSize) {
        String sql = "select *  from t_sod_table_collect_info where table_id='" + tableId + "' and database_id = '" +databaseId+ "'";
        PageBean page = this.queryByNativeSQLPageMap(sql,null, new PageForm(pageNum, pageSize));
        return page;
    }

    @Override
    public Map<String, Object> getArchiveInfo(String c_datum_code) {
        String sql = "SELECT C.* FROM (SELECT A.C_DATUM_CODE,A.C_SOURSDATUM_CODE,LEVEL L FROM T_SOD_DATA_DATUMTYPEINFO A " +
                "START WITH A.C_DATUM_CODE = '" + c_datum_code + "'" +
                "CONNECT BY PRIOR A.C_SOURSDATUM_CODE = A.C_DATUM_CODE) B,T_SOD_ARCHIVE_INFO C  " +
                "WHERE L = 2 AND B.C_SOURSDATUM_CODE = C.PRODUCTIONCODE";
        List<Map<String, Object>> list = this.queryByNativeSQL(sql);
        return list == null ? null:list.get(0);
    }

    @Override
    public List<Map<String, Object>> getByUserIdAndApplyId(NewdataApplyDto newdataApplyDto) {
        NewdataApplyEntity newdataApplyEntity = newdataApplyMapper.toEntity(newdataApplyDto);
        return mybatisQueryMapper.getByUserIdAndApplyId(newdataApplyEntity);
    }

    @Override
    public List<Map<String, Object>> getColumnByIdAndDDataId(NewdataApplyDto newdataApplyDto) {
        NewdataApplyEntity newdataApplyEntity = newdataApplyMapper.toEntity(newdataApplyDto);
        return mybatisQueryMapper.getColumnByIdAndDDataId(newdataApplyEntity);
    }

    @Override
    public void deleteById(String id) {

    }

}