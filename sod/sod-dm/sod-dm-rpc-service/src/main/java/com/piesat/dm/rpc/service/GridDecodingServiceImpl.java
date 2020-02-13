package com.piesat.dm.rpc.service;

import com.piesat.common.jpa.BaseDao;
import com.piesat.common.jpa.BaseService;
import com.piesat.common.jpa.specification.SimpleSpecificationBuilder;
import com.piesat.dm.dao.GridDecodingDao;
import com.piesat.dm.entity.GridDecodingEntity;
import com.piesat.dm.rpc.api.GridDecodingService;
import com.piesat.dm.rpc.dto.GridDecodingDto;
import com.piesat.dm.rpc.mapper.GridDecodingMapper;
import com.piesat.util.page.PageBean;
import com.piesat.util.page.PageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 编码配置
 *
 * @author cwh
 * @date 2020年 02月12日 14:47:50
 */
@Service
public class GridDecodingServiceImpl extends BaseService<GridDecodingEntity> implements GridDecodingService {

    @Autowired
    private GridDecodingDao gridDecodingDao;
    @Autowired
    private GridDecodingMapper gridDecodingMapper;


    @Override
    public BaseDao<GridDecodingEntity> getBaseDao() {
        return this.gridDecodingDao;
    }

    @Override
    public GridDecodingDto saveDto(GridDecodingDto gridDecodingDto) {
        GridDecodingEntity gridDecodingEntity = this.gridDecodingMapper.toEntity(gridDecodingDto);
        gridDecodingEntity = this.save(gridDecodingEntity);
        return this.gridDecodingMapper.toDto(gridDecodingEntity);
    }

    @Override
    public GridDecodingDto getDotById(String id) {
        GridDecodingEntity gridDecodingEntity = this.getById(id);
        return this.gridDecodingMapper.toDto(gridDecodingEntity);
    }

    @Override
    public List<GridDecodingDto> all() {
        List<GridDecodingEntity> all = this.getAll();
        return this.gridDecodingMapper.toDto(all);
    }

    @Override
    public PageBean list(PageForm pageForm) {
        SimpleSpecificationBuilder ssb = new SimpleSpecificationBuilder();
        Sort sort = Sort.by(Sort.Direction.ASC, "createTime");
        PageBean page = this.getPage(ssb.generateSpecification(), pageForm, sort);
        List<GridDecodingEntity> pageData = (List<GridDecodingEntity>)page.getPageData();
        page.setPageData(this.gridDecodingMapper.toDto(pageData));
        return page;
    }
}
