package com.piesat.dm.rpc.service.dataclass;

import com.piesat.common.jpa.BaseDao;
import com.piesat.common.jpa.BaseService;
import com.piesat.dm.dao.dataclass.DataClassBaseInfoDao;
import com.piesat.dm.entity.dataclass.DataClassBaseInfoEntity;
import com.piesat.dm.mapper.MybatisQueryMapper;
import com.piesat.dm.rpc.api.dataclass.DataClassBaseInfoService;
import com.piesat.dm.rpc.dto.dataclass.DataClassBaseInfoDto;
import com.piesat.dm.rpc.mapper.dataclass.DataClassBaseInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资料基础信息
 *
 * @author cwh
 * @date 2020年 04月02日 09:11:19
 */
@Service
public class DataClassBaseInfoServiceImpl extends BaseService<DataClassBaseInfoEntity> implements DataClassBaseInfoService {
    @Autowired
    private DataClassBaseInfoDao dataClassBaseInfoDao;
    @Autowired
    private DataClassBaseInfoMapper dataClassBaseInfoMapper;
    @Autowired
    private MybatisQueryMapper mybatisQueryMapper;


    @Override
    public BaseDao<DataClassBaseInfoEntity> getBaseDao() {
        return this.dataClassBaseInfoDao;
    }

    @Override
    public DataClassBaseInfoDto saveDto(DataClassBaseInfoDto dataClassBaseInfoDto) {
        DataClassBaseInfoEntity dataClassBaseInfoEntity = this.dataClassBaseInfoMapper.toEntity(dataClassBaseInfoDto);
        dataClassBaseInfoEntity = this.saveNotNull(dataClassBaseInfoEntity);
        return this.dataClassBaseInfoMapper.toDto(dataClassBaseInfoEntity);
    }

    @Override
    public DataClassBaseInfoDto getDotById(String id) {
        DataClassBaseInfoEntity dataClassBaseInfoEntity = this.getById(id);
        return this.dataClassBaseInfoMapper.toDto(dataClassBaseInfoEntity);
    }

    @Override
    public List<DataClassBaseInfoDto> all() {
        List<DataClassBaseInfoEntity> all = this.getAll();
        return this.dataClassBaseInfoMapper.toDto(all);
    }

    @Override
    public DataClassBaseInfoDto getDataClassBaseInfo(String id) {
        DataClassBaseInfoEntity dataClassBaseInfo = mybatisQueryMapper.getDataClassBaseInfo(id);
        DataClassBaseInfoDto dataClassBaseInfoDto = this.dataClassBaseInfoMapper.toDto(dataClassBaseInfo);
        DataClassBaseInfoEntity SodDataClassBaseInfo = dataClassBaseInfoDao.findByDataClassId(id);
        DataClassBaseInfoDto sodDataClassBaseInfoDto = this.dataClassBaseInfoMapper.toDto(SodDataClassBaseInfo);
        dataClassBaseInfoDto = dataClassBaseInfoDto.combineSydwCore(sodDataClassBaseInfoDto,dataClassBaseInfoDto);
        return dataClassBaseInfoDto;
    }

    @Override
    public DataClassBaseInfoDto saveDataClassBaseInfo(DataClassBaseInfoDto dataClassBaseInfoDto) {
        DataClassBaseInfoEntity sodDataClassBaseInfo = dataClassBaseInfoDao.findByDataClassId(dataClassBaseInfoDto.getDataClassId());
        DataClassBaseInfoEntity dataClassBaseInfo = mybatisQueryMapper.getDataClassBaseInfo(dataClassBaseInfoDto.getDataClassId());
        DataClassBaseInfoDto BaseInfo = this.dataClassBaseInfoMapper.toDto(dataClassBaseInfo);
        DataClassBaseInfoDto diffInfo = dataClassBaseInfoDto.getDiffInfo(dataClassBaseInfoDto, BaseInfo);
        DataClassBaseInfoDto dto = this.saveDto(diffInfo);
        this.delete(sodDataClassBaseInfo.getId());
        return dto;
    }
}
