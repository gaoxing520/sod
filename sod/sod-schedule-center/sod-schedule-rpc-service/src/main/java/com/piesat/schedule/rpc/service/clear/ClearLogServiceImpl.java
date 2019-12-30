package com.piesat.schedule.rpc.service.clear;

import com.piesat.common.jpa.BaseDao;
import com.piesat.common.jpa.BaseService;
import com.piesat.common.jpa.specification.SimpleSpecificationBuilder;
import com.piesat.common.jpa.specification.SpecificationOperator;
import com.piesat.common.utils.StringUtils;
import com.piesat.schedule.dao.clear.ClearLogDao;
import com.piesat.schedule.entity.clear.ClearLogEntity;
import com.piesat.schedule.entity.clear.ClearLogEntity;
import com.piesat.schedule.rpc.api.clear.ClearLogService;
import com.piesat.schedule.rpc.dto.clear.ClearLogDto;
import com.piesat.schedule.rpc.mapstruct.clear.ClearLogMapstruct;
import com.piesat.schedule.rpc.mapstruct.clear.ClearMapstruct;
import com.piesat.util.page.PageBean;
import com.piesat.util.page.PageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @program: sod
 * @description:
 * @author: zzj
 * @create: 2019-12-29 19:28
 **/
@Service
public class ClearLogServiceImpl extends BaseService<ClearLogEntity> implements ClearLogService{
    @Autowired
    private ClearLogDao clearLogDao;
    @Autowired
    private ClearLogMapstruct clearLogMapstruct;
    @Override
    public BaseDao<ClearLogEntity> getBaseDao() {
        return clearLogDao;
    }
    @Override
    public PageBean selectClearLogList(PageForm<ClearLogDto> pageForm){
        ClearLogEntity clearLogEntity=clearLogMapstruct.toEntity(pageForm.getT());
        SimpleSpecificationBuilder specificationBuilder=new SimpleSpecificationBuilder();
        if(StringUtils.isNotNullString(clearLogEntity.getDatabaseId())){
            specificationBuilder.add("databaseId", SpecificationOperator.Operator.eq.name(),clearLogEntity.getDatabaseId());
        }
        if(StringUtils.isNotNullString(clearLogEntity.getDataClassId())){
            specificationBuilder.add("dataClassId", SpecificationOperator.Operator.likeAll.name(),clearLogEntity.getDataClassId());
            specificationBuilder.addOr("ddataId", SpecificationOperator.Operator.likeAll.name(),clearLogEntity.getDataClassId());
        }
        if(StringUtils.isNotNullString(clearLogEntity.getProfileName())){
            specificationBuilder.add("profileName", SpecificationOperator.Operator.likeAll.name(),clearLogEntity.getProfileName());
        }
        if(null!=clearLogEntity.getHandleCode()){
            specificationBuilder.add("handleCode",SpecificationOperator.Operator.eq.name(),clearLogEntity.getHandleCode());
        }
        if(StringUtils.isNotNullString(clearLogEntity.getTableName())){
            specificationBuilder.add("tableName", SpecificationOperator.Operator.likeAll.name(),clearLogEntity.getTableName());
        }
        if(StringUtils.isNotNullString((String) clearLogEntity.getParamt().get("beginTime"))){
            specificationBuilder.add("createTime",SpecificationOperator.Operator.ges.name(),(String) clearLogEntity.getParamt().get("beginTime"));
        }
        if(StringUtils.isNotNullString((String) clearLogEntity.getParamt().get("endTime"))){
            specificationBuilder.add("createTime",SpecificationOperator.Operator.les.name(),(String) clearLogEntity.getParamt().get("endTime"));
        }
        Sort sort=Sort.by(Sort.Direction.ASC,"createTime");
        PageBean pageBean=this.getPage(specificationBuilder.generateSpecification(),pageForm,sort);
        List<ClearLogEntity> clearLogEntities= (List<ClearLogEntity>) pageBean.getPageData();
        pageBean.setPageData(clearLogMapstruct.toDto(clearLogEntities));
        return pageBean;

    }
    @Override
    public ClearLogDto findClearLogById(String clearLogId){
        ClearLogEntity clearLogEntity=this.getById(clearLogId);
        return clearLogMapstruct.toDto(clearLogEntity);

    }

    @Override
    public void deleteClearLogByIds(String[] clearLogIds){
        this.deleteByIds(Arrays.asList(clearLogIds));
    }
}

