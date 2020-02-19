package com.piesat.dm.rpc.service;

import com.piesat.common.jpa.BaseDao;
import com.piesat.common.jpa.BaseService;
import com.piesat.common.jpa.specification.SimpleSpecificationBuilder;
import com.piesat.common.utils.StringUtils;
import com.piesat.dm.core.api.impl.Gbase8a;
import com.piesat.dm.core.api.impl.Xugu;
import com.piesat.dm.dao.ConsistencyCheckDao;
import com.piesat.dm.entity.ConsistencyCheckEntity;
import com.piesat.dm.rpc.api.ConsistencyCheckService;
import com.piesat.dm.rpc.api.DataTableService;
import com.piesat.dm.rpc.api.DatabaseService;
import com.piesat.dm.rpc.dto.*;
import com.piesat.dm.rpc.mapper.ConsistencyCheckMapper;
import com.piesat.util.page.PageBean;
import com.piesat.util.page.PageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author yaya
 * @description TODO
 * @date 2020/2/14 10:43
 */
@Service
public class ConsistencyCheckServiceImpl extends BaseService<ConsistencyCheckEntity> implements ConsistencyCheckService {

    @Autowired
    private ConsistencyCheckDao consistencyCheckDao;
    @Autowired
    private ConsistencyCheckMapper consistencyCheckMapper;
    @Autowired
    private DatabaseService databaseService;
    @Autowired
    private DataTableService dataTableService;


    @Override
    public BaseDao<ConsistencyCheckEntity> getBaseDao() {
        return this.consistencyCheckDao;
    }

    @Override
    public PageBean selectPageList(PageForm<ConsistencyCheckDto> pageForm) {
        ConsistencyCheckEntity consistencyCheckEntity=consistencyCheckMapper.toEntity(pageForm.getT());
        SimpleSpecificationBuilder specificationBuilder=new SimpleSpecificationBuilder();
        PageBean pageBean=this.getPage(specificationBuilder.generateSpecification(),pageForm,null);
        List<ConsistencyCheckEntity> consistencyCheckEntities= (List<ConsistencyCheckEntity>) pageBean.getPageData();
        pageBean.setPageData(consistencyCheckMapper.toDto(consistencyCheckEntities));
        return pageBean;
    }

    @Override
    public ConsistencyCheckDto saveDto(ConsistencyCheckDto consistencyCheckDto) {
        ConsistencyCheckEntity consistencyCheckEntity=consistencyCheckMapper.toEntity(consistencyCheckDto);
        consistencyCheckEntity = this.saveNotNull(consistencyCheckEntity);
        return consistencyCheckMapper.toDto(consistencyCheckEntity);
    }

    @Override
    public void deleteById(String id) {
        this.delete(id);
    }

    @Override
    public Map<String, List<List<String>>> downloadDfcheckFile(String databaseId) {
        //获取数据库详细信息
        DatabaseDto databaseDto = databaseService.getDotById(databaseId);
        String driver = databaseDto.getDatabaseDefine().getDriverClassName();
        String url = databaseDto.getDatabaseDefine().getDatabaseUrl();
        String username = "";
        String password = "";
        Iterator<DatabaseAdministratorDto> databaseAdministrators = databaseDto.getDatabaseDefine().getDatabaseAdministratorList().iterator();
        while(databaseAdministrators.hasNext()){
            DatabaseAdministratorDto databaseAdministrator = databaseAdministrators.next();
            username = databaseAdministrator.getUserName();
            password = databaseAdministrator.getPassWord();
            break;
        }
        List<String> tableList = null;
        Map<String,List<List<String>>> compileResult = new HashMap<String,List<List<String>>>();
        compileResult.put("columnResult",new ArrayList<List<String>>());
        compileResult.put("indexResult",new ArrayList<List<String>>());
        compileResult.put("shardingResult",new ArrayList<List<String>>());
        try{
            if("Gbase8a".equals(databaseDto.getDatabaseDefine().getDatabaseType())){
                Gbase8a gbase8a = new Gbase8a(url, username, password);
                //获取数据库中所有的表名
                tableList = (List<String>)gbase8a.queryAllTableName(databaseDto.getSchemaName());

                for(String tableName:tableList){
                    //物理库表字段信息
                    Map<String,Map<String,Object>> columnInfos = (Map<String,Map<String,Object>>)gbase8a.queryAllColumnInfo(databaseDto.getSchemaName(), tableName);
                    //物理库表索引和分库分表信息
                    Map<String,Map<String,String>> indexAndShardings = (Map<String,Map<String,String>>)gbase8a.queryAllIndexAndShardingInfo(databaseDto.getSchemaName(), tableName);
                    compareDifferences(databaseId,tableName,columnInfos,indexAndShardings,compileResult);

                }
            }else{
                Xugu xugu = new Xugu(url, username, password);
                //获取数据库中所有的表名
                tableList = (List<String>)xugu.queryAllTableName(databaseDto.getSchemaName());

                for(String tableName:tableList){

                    //表字段信息
                    Map<String,Map<String,Object>> columnInfos = (Map<String,Map<String,Object>>)xugu.queryAllColumnInfo(databaseDto.getSchemaName(),tableName.toUpperCase());
                    //表索引和分开分表信息
                    Map<String,Map<String,String>> indexAndShardings = (( Map<String,Map<String,String>>)xugu.queryAllIndexAndShardingInfo(databaseDto.getSchemaName(), tableName));
                    compareDifferences(databaseId,tableName,columnInfos,indexAndShardings,compileResult);
                }
            }


        }catch(Exception e){
            e.printStackTrace();
        }finally{
        }

        return compileResult;
    }

    public void compareDifferences(String databaseId, String tableName, Map<String,Map<String,Object>> columnInfos, Map<String,Map<String,String>> indexAndShardings, Map<String,List<List<String>>> compileResult){
        //物理库数据表名	  存储编码	资料名称	  存储元数据中该表是否存在	存储元数据多余的字段   存储元数据缺失的字段  存储元数据类型需要修改的字段   存储元数据精度需要修改的字段   存储元数据和物理库非空设置不一致的字段   存储元数据和物理库主键设置不一致的字段
        //物理库数据表名   存储编码  资料名称  存储元数据中该表是否存在   存储元数据多余的索引   存储元数据缺失的索引  存储元数据需要修改的索引
        List<List<String>> columnResults = compileResult.get("columnResult");
        List<List<String>> indexResults = compileResult.get("indexResult");
        List<List<String>> shardingResults = compileResult.get("shardingResult");
        List<String> columnResult = null;
        List<String> indexResult = null;
        List<String> shardingResult = null;
        //元数据表信息
        List<Map<String, Object>> dataTableList = dataTableService.getByDatabaseIdAndTableName(databaseId, tableName);
        if(dataTableList == null || dataTableList.size() == 0){
            //元数据表缺失
            columnResult = new ArrayList<String>();
            columnResult.add(tableName);
            columnResult.add(3,"不存在");
            columnResults.add(columnResult);
            indexResult = new ArrayList<String>();
            indexResult.add(tableName);
            indexResult.add(3,"不存在");
            indexResults.add(indexResult);
            shardingResult = new ArrayList<String>();
            shardingResult.add(tableName);
            shardingResult.add(3,"不存在");
            shardingResults.add(shardingResult);
           return;
        }
        for(Map<String, Object> dataTable : dataTableList) {
            DataTableDto dataTableDto = dataTableService.getDotById((String) dataTable.get("id"));
            columnResult = new ArrayList<String>();
            columnResult.add(tableName);
            columnResult.add(dataTableDto.getDataServiceId());
            columnResult.add(dataTableDto.getNameCn());
            columnResult.add("存在");
            //以元数据库字段为准，遍历元数据字段
            Iterator<TableColumnDto> columnIterator = dataTableDto.getColumns().iterator();
            while(columnIterator.hasNext()) {
                TableColumnDto columnDto = columnIterator.next();
                Map<String, Object> columnOneInfo = columnInfos.get(columnDto.getDbEleCode().toUpperCase());
                //存储元数据多余字段
                if(columnOneInfo == null){
                    if(StringUtils.isNotNullString(columnResult.get(4))) {
                        columnResult.set(4,columnResult.get(4) + ";" + columnDto.getDbEleCode());
                    }else{
                        columnResult.set(4,columnDto.getDbEleCode());
                    }
                    continue;
                }
                //判断类型和长度
                String dbColumnType = ((String) columnOneInfo.get("column_type")).replace(",",".");// decimal(4,0)  varchar(200)  date
                String dbType = "";
                String dbAcc = "";
                if(dbColumnType.indexOf("(") != -1){
                    dbType = dbColumnType.substring(0,dbColumnType.indexOf("("));
                    dbAcc = dbColumnType.substring(dbColumnType.indexOf("(") + 1, dbColumnType.length()-1);
                }else{
                    dbType = dbColumnType;
                }
                if(!dbType.equalsIgnoreCase(columnDto.getType())){
                    if(!("decimal".equalsIgnoreCase(columnDto.getType()) && "NUMERIC".equalsIgnoreCase(dbType)) &&
                            !("int".equalsIgnoreCase(columnDto.getType()) && "INTEGER".equalsIgnoreCase(dbType))){
                        if(StringUtils.isNotNullString(columnResult.get(6))){
                            columnResult.set(6,columnResult.get(6)+";"+columnDto.getDbEleCode()+":"+columnDto.getType()+"=>"+dbType);
                        }else{
                            columnResult.set(6,columnDto.getDbEleCode()+":"+columnDto.getType()+"=>"+dbType);
                        }
                    }
                }
                if(!dbAcc.equalsIgnoreCase(columnDto.getAccuracy())){
                    if(StringUtils.isNotNullString(columnResult.get(7))){
                        columnResult.set(7,columnResult.get(7)+";"+columnDto.getDbEleCode()+":"+columnDto.getAccuracy()+"=>"+dbAcc);
                    }else{
                        columnResult.set(7,columnDto.getDbEleCode()+":"+columnDto.getAccuracy()+"=>"+dbAcc);
                    }

                }
                //判断为空设置是否一致
                if((columnDto.getIsNull() && (Integer)columnOneInfo.get("is_nullable") == 0) || (!columnDto.getIsNull() && (Integer)columnOneInfo.get("is_nullable") == 1)){
                    if(StringUtils.isNotNullString(columnResult.get(8))){
                        columnResult.set(8,columnResult.get(8)+";"+columnDto.getDbEleCode());
                    }else{
                        columnResult.set(8,columnDto.getDbEleCode());
                    }
                }
                //以物理库为基础，查找元数据库缺失的字段
                for(String codeName : columnInfos.keySet()){
                    boolean flag = false;
                    while(columnIterator.hasNext()) {
                        TableColumnDto columnDto1 = columnIterator.next();
                        if(codeName.equalsIgnoreCase(columnDto1.getDbEleCode())){
                            flag = true;
                            break;
                        }
                    }
                    if(!flag){
                        if(StringUtils.isNotNullString(columnResult.get(5))) {
                            columnResult.set(5,columnResult.get(5) + ";" + codeName);
                        }else{
                            columnResult.set(5,codeName);
                        }
                    }
                }
            }
            columnResults.add(columnResult);


            indexResult = new ArrayList<String>();
            indexResult.add(tableName);
            indexResult.add(dataTableDto.getDataServiceId());
            indexResult.add(dataTableDto.getNameCn());
            indexResult.add("存在");
            //以元数据库索引为准，遍历元数据索引
            Iterator<TableIndexDto> indexIterator = dataTableDto.getTableIndexList().iterator();
            Map<String, String> dbIndexs = indexAndShardings.get("indexs");
            while(indexIterator.hasNext()) {
                TableIndexDto indexDto = indexIterator.next();
                String indexColumn = dbIndexs.get(indexDto.getIndexName());
                //元数据多余的索引
                if(!StringUtils.isNotNullString(indexColumn)){
                    if(StringUtils.isNotNullString(indexResult.get(4))){
                        indexResult.set(4,indexResult.get(4) +";"+indexDto.getIndexName());
                    }else{
                        indexResult.set(4,indexDto.getIndexName());
                    }
                    continue;
                }
                //判断索引字段
                if(!indexColumn.equalsIgnoreCase(indexDto.getIndexColumn())){
                    if(StringUtils.isNotNullString(indexResult.get(6))){
                        indexResult.set(6,indexResult.get(6) +";"+indexDto.getIndexName());
                    }else{
                        indexResult.set(6,indexDto.getIndexName());
                    }
                }
                //以物理库为基础，查找元数据库缺失的索引
                for(String indexName : dbIndexs.keySet()){
                    boolean flag = false;
                    while(indexIterator.hasNext()) {
                        TableIndexDto indexDto1 = indexIterator.next();
                        if(indexName.equalsIgnoreCase(indexDto1.getIndexName())){
                            flag = true;
                            break;
                        }
                    }
                    if(!flag){
                        if(StringUtils.isNotNullString(indexResult.get(5))) {
                            indexResult.set(5,indexResult.get(5) + ";" + indexName);
                        }else{
                            indexResult.set(5,indexName);
                        }
                    }
                }
            }
            indexResults.add(indexResult);
        }
    }


}