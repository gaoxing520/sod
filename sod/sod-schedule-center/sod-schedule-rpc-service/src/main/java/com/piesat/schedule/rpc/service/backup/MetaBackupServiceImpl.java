package com.piesat.schedule.rpc.service.backup;

import com.piesat.common.jpa.BaseDao;
import com.piesat.common.jpa.BaseService;
import com.piesat.common.jpa.specification.SimpleSpecificationBuilder;
import com.piesat.common.jpa.specification.SpecificationOperator;
import com.piesat.common.utils.StringUtils;
import com.piesat.schedule.dao.backup.MetaBackupDao;
import com.piesat.schedule.entity.backup.MetaBackupEntity;
import com.piesat.schedule.rpc.api.JobInfoService;
import com.piesat.schedule.rpc.api.backup.MetaBackupService;
import com.piesat.schedule.rpc.dto.backup.MetaBackupDto;
import com.piesat.schedule.rpc.mapstruct.backup.MetaBackupMapstruct;
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
 * @create: 2020-02-26 15:59
 **/
@Service
public class MetaBackupServiceImpl extends BaseService<MetaBackupEntity> implements MetaBackupService{
    @Autowired
    private MetaBackupDao metaBackupDao;
    @Autowired
    private MetaBackupMapstruct metaBackupMapstruct;
    @Autowired
    private JobInfoService jobInfoService;
    @Override
    public BaseDao<MetaBackupEntity> getBaseDao() {
        return metaBackupDao;
    }
    @Override
    public PageBean selectBackupList(PageForm<MetaBackupDto> pageForm){
        MetaBackupEntity metaBackupEntity=metaBackupMapstruct.toEntity(pageForm.getT());
        SimpleSpecificationBuilder specificationBuilder=new SimpleSpecificationBuilder();
        if(StringUtils.isNotNullString(metaBackupEntity.getDatabaseName())){
            specificationBuilder.add("databaseName", SpecificationOperator.Operator.likeAll.name(),metaBackupEntity.getDatabaseName());
        }
        if(null!=metaBackupEntity.getTriggerStatus()){
            specificationBuilder.add("triggerStatus",SpecificationOperator.Operator.eq.name(),metaBackupEntity.getTriggerStatus());
        }
        if(StringUtils.isNotNullString((String) metaBackupEntity.getParamt().get("beginTime"))){
            specificationBuilder.add("createTime",SpecificationOperator.Operator.ges.name(),(String) metaBackupEntity.getParamt().get("beginTime"));
        }
        if(StringUtils.isNotNullString((String) metaBackupEntity.getParamt().get("endTime"))){
            specificationBuilder.add("createTime",SpecificationOperator.Operator.les.name(),(String) metaBackupEntity.getParamt().get("endTime"));
        }
        Sort sort=Sort.by(Sort.Direction.ASC,"createTime");
        PageBean pageBean=this.getPage(specificationBuilder.generateSpecification(),pageForm,sort);
        List<MetaBackupEntity> metaBackupEntities= (List<MetaBackupEntity>) pageBean.getPageData();
        pageBean.setPageData(metaBackupMapstruct.toDto(metaBackupEntities));
        return pageBean;

    }
    @Override
    public MetaBackupDto findBackupById(String metaBackupId){
        MetaBackupEntity metaBackupEntity=this.getById(metaBackupId);
        return metaBackupMapstruct.toDto(metaBackupEntity);

    }
    @Override
    public void saveBackup(MetaBackupDto metaBackupDto){
        MetaBackupEntity metaBackupEntity=metaBackupMapstruct.toEntity(metaBackupDto);
        metaBackupEntity=this.saveNotNull(metaBackupEntity);
        jobInfoService.start(metaBackupMapstruct.toDto(metaBackupEntity));

    }
    @Override
    public void updateBackup(MetaBackupDto metaBackupDto){
        MetaBackupEntity metaBackupEntity=metaBackupMapstruct.toEntity(metaBackupDto);
        this.saveNotNull(metaBackupEntity);
        jobInfoService.start(metaBackupDto);
    }
    @Override
    public void deleteBackupByIds(String[] metaBackupIds){
        this.deleteByIds(Arrays.asList(metaBackupIds));
        jobInfoService.stopByIds(Arrays.asList(metaBackupIds));
    }

}

