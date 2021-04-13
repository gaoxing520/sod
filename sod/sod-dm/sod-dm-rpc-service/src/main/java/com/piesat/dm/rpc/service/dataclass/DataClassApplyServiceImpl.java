package com.piesat.dm.rpc.service.dataclass;

import com.piesat.common.jpa.BaseDao;
import com.piesat.common.jpa.BaseService;
import com.piesat.common.jpa.specification.SimpleSpecificationBuilder;
import com.piesat.common.jpa.specification.SpecificationOperator;
import com.piesat.common.utils.DateUtils;
import com.piesat.common.utils.StringUtils;
import com.piesat.dm.common.constants.ConstantsMsg;
import com.piesat.dm.common.enums.StatusEnum;
import com.piesat.dm.dao.dataclass.DataClassApplyDao;
import com.piesat.dm.dao.dataclass.DataClassInfoDao;
import com.piesat.dm.entity.dataclass.DataClassApplyEntity;
import com.piesat.dm.entity.dataclass.DataClassInfoEntity;
import com.piesat.dm.rpc.api.ReviewLogService;
import com.piesat.dm.rpc.api.database.SchemaService;
import com.piesat.dm.rpc.api.dataclass.*;
import com.piesat.dm.rpc.api.datatable.*;
import com.piesat.dm.rpc.dto.ReviewLogDto;
import com.piesat.dm.rpc.dto.database.SchemaDto;
import com.piesat.dm.rpc.dto.dataclass.*;
import com.piesat.dm.rpc.dto.datatable.*;
import com.piesat.dm.rpc.mapper.dataclass.DataClassApplyMapper;
import com.piesat.util.ResultT;
import com.piesat.util.page.PageBean;
import com.piesat.util.page.PageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 资料申请
 *
 * @author cwh
 * @date 2019年 11月22日 16:31:15
 */
@Service
public class DataClassApplyServiceImpl extends BaseService<DataClassApplyEntity> implements DataClassApplyService {

    private final String MLDT = "MLDT.";
    private final String FORMAT = "yyyy.MMdd";

    @Autowired
    private DataClassApplyDao dataClassApplyDao;
    @Autowired
    private DataClassService dataClassService;
    @Autowired
    private DataClassInfoService dataClassInfoService;
    @Autowired
    private DataClassApplyMapper dataClassApplyMapper;
    @Autowired
    private TableColumnService tableColumnService;
    @Autowired
    private TableIndexService tableIndexService;
    @Autowired
    private ShardingService shardingService;
    @Autowired
    private ReviewLogService reviewLogService;
    @Autowired
    private DataLogicService dataLogicService;
    @Autowired
    private DataTableService dataTableService;
    @Autowired
    private SchemaService schemaService;
    @Autowired
    private DataClassInfoDao dataClassInfoDao;
    @Autowired
    private DataClassLabelService dataClassLabelService;
    @Autowired
    private DataClassLabelDefService dataClassLabelDefService;
    @Autowired
    private DataTableApplyService dataTableApplyService;

    @Override
    public BaseDao<DataClassApplyEntity> getBaseDao() {
        return dataClassApplyDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultT saveDto(DataClassApplyDto dataClassApplyDto) {
        try {
            String dataClassId = dataClassApplyDto.getDataClassId();

            //生成中间数据的编码
            if (StringUtils.isEmpty(dataClassId)) {
                String sf = MLDT + DateUtils.dateTimeNow(FORMAT);
                List<DataClassInfoEntity> list = this.dataClassInfoDao.findByDataTypeOrderByDataClassIdDesc(1);
                if (list.size() > 0) {
                    String dataClassId1 = list.get(0).getDataClassId();
                    String su = dataClassId1.substring(0, 14);
                    String s1 = dataClassId1.substring(15, 19);
                    if (sf.equals(su)) {
                        int i = Integer.valueOf(s1) + 1;
                        DecimalFormat df = new DecimalFormat("0000");
                        dataClassId = sf + "." + df.format(i);
                    } else {
                        dataClassId = sf + ".0001";
                    }
                }
                dataClassApplyDto.setDataClassId(dataClassId);
            }
            //保存标签信息
            List<DataClassLabelDto> dataClassLabelList = dataClassApplyDto.getDataClassLabelList();
            for (DataClassLabelDto dataClassLabelDto : dataClassLabelList) {
                String labelKey = dataClassLabelDto.getLabelKey();
                if (StringUtils.isEmpty(labelKey)) {
                    DataClassLabelDefDto dl = new DataClassLabelDefDto();
                    dl.setLabelName(dataClassLabelDto.getLabelName());
                    dl.setRemark(dataClassLabelDto.getRemark());
                    dl.setStatus(1);
                    dl.setUserId(dataClassApplyDto.getUserId());
                    DataClassLabelDefDto dataClassLabelDefDto = this.dataClassLabelDefService.saveDto(dl);
                    dataClassLabelDto.setLabelKey(dataClassLabelDefDto.getId());
                }
                if (StringUtils.isEmpty(dataClassLabelDto.getDataClassId())) {
                    dataClassLabelDto.setDataClassId(dataClassId);
                }
                this.dataClassLabelService.saveDto(dataClassLabelDto);
            }
            List<DataClassLogicDto> dataClassLogicDtos = dataClassApplyDto.getDataClassLogicList();
            List<DataClassLogicDto> dataClassLogicList = dataClassLogicDtos == null
                    ? new ArrayList<>() : dataClassLogicDtos;
            List<DataTableApplyDto> dataTableApplyDtoList = dataClassApplyDto.getDataTableApplyDtoList();
            //保存申请表信息
            Optional.ofNullable(dataTableApplyDtoList).ifPresent(d -> d.forEach(e -> {
                TablePartDto tablePartDto = e.getTablePartDto();
                e.setApplyId(dataClassApplyDto.getId());
                e.setStatus(StatusEnum.待审核.getCode());
                DataTableApplyDto dataTableApplyDto = this.dataTableApplyService.saveDto(e);
                DataClassLogicDto dcl = new DataClassLogicDto();
                dcl.setDataClassId(dataClassApplyDto.getDataClassId());
                dcl.setTableId(dataTableApplyDto.getId());
                dataClassLogicList.add(dcl);
                if (tablePartDto != null) {
                    tablePartDto.setId(dataTableApplyDto.getId());
                    this.shardingService.saveDto(tablePartDto);
                }
            }));
            //保存关联信息
            for (DataClassLogicDto dataClassLogicDto : dataClassLogicList) {
                if (StringUtils.isEmpty(dataClassLogicDto.getDataClassId())) {
                    dataClassLogicDto.setDataClassId(dataClassId);
                }
            }
            this.dataLogicService.saveList(dataClassLogicList);

            dataClassApplyDto.setStatus(1);
            this.save(this.dataClassApplyMapper.toEntity(dataClassApplyDto));
        } catch (Exception e) {
            return ResultT.failed(e.getMessage());
        }
        return ResultT.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultT review(DataClassApplyDto dca) {
        DataClassApplyEntity dca_ = this.dataClassApplyMapper.toEntity(dca);
        if (StatusEnum.match(dca_.getStatus()) == StatusEnum.审核未通过) {
            dca_ = this.save(dca_);
        } else if (StatusEnum.match(dca_.getStatus()) == StatusEnum.审核通过) {
            DataClassInfoDto dataClassInfo = dca.getDataClassInfo();
            dataClassInfo.setStatus(StatusEnum.审核通过.getCode());
            this.dataClassInfoService.saveDto(dataClassInfo);
            List<DataTableApplyDto> dataTableApplyDtoList = dca.getDataTableApplyDtoList();
            dataTableApplyDtoList.forEach(e -> {
                e.setStatus(StatusEnum.审核通过.getCode());
                this.dataTableApplyService.review(e);
            });
        }
        return ResultT.success(dca_);
    }

    @Override
    public ResultT list(PageForm<DataClassApplyDto> pageForm) {
        DataClassApplyDto t = pageForm.getT();
        SimpleSpecificationBuilder specificationBuilder = new SimpleSpecificationBuilder();
        if (!StringUtils.isEmpty(t.getUserId())) {
            specificationBuilder.add("userId", SpecificationOperator.Operator.eq.name(), t.getUserId());
        }
        if (!StringUtils.isEmpty(t.getDataName())) {
            specificationBuilder.add("dataName", SpecificationOperator.Operator.likeAll.name(), t.getDataName());
        }
        if (!StringUtils.isEmpty(t.getDataClassId())) {
            specificationBuilder.add("dataClassId", SpecificationOperator.Operator.likeAll.name(), t.getDataClassId());
        }
        if (t.getDataType() != null) {
            specificationBuilder.add("dataType", SpecificationOperator.Operator.eq.name(), t.getDataType());
        }

        Sort sort = Sort.by(Sort.Direction.DESC, "createTime", "reviewTime");
        PageBean pageBean = this.getPage(specificationBuilder.generateSpecification(), pageForm, sort);
        List<DataClassApplyEntity> list = (List<DataClassApplyEntity>) pageBean.getPageData();
        List<DataClassApplyDto> dataClassApplyDtos = this.dataClassApplyMapper.toDto(list);

        dataClassApplyDtos.forEach(e -> {
            if (e.getDataType().equals(1)) {
                List<DataClassLogicDto> dataClassLogicDtoList = this.dataLogicService.findByDataClassId(e.getDataClassId());
                e.setDataClassLogicList(dataClassLogicDtoList);
                List<DataTableApplyDto> l = new ArrayList<>();
                dataClassLogicDtoList.forEach(d -> {
                    String tableId = d.getTableId();
                    DataTableApplyDto dataTableApplyDto = this.dataTableApplyService.getDotById(tableId);
                    l.add(dataTableApplyDto);
                });
                e.setDataTableApplyDtoList(l);
            }
        });
        pageBean.setPageData(dataClassApplyDtos);
        return ResultT.success(pageBean);
    }

    @Override
    public DataClassApplyDto getDotById(String id) {
        DataClassApplyDto dataClassApplyDto = this.dataClassApplyMapper.toDto(this.getById(id));
        List<DataClassLabelDto> dataClassLabelDtoList = this.dataClassLabelService.findByDataClassId(dataClassApplyDto.getDataClassId());
        List<DataClassLogicDto> dataClassLogicDtoList = this.dataLogicService.findByDataClassId(dataClassApplyDto.getDataClassId());
        if (dataClassLogicDtoList.size() > 0) {
            String tableId = dataClassLogicDtoList.get(0).getTableId();
            DataTableInfoDto dataTableInfoDto = this.dataTableService.getDotById(tableId);
            dataClassApplyDto.setTableName(dataTableInfoDto.getTableName());
        }
        List<DataTableApplyDto> dataTableApplyDtoList = this.dataTableApplyService.findByApplyId(id);
        dataTableApplyDtoList.forEach(e -> {
            TablePartDto tablePartDto = this.shardingService.getDotById(e.getId());
            if (tablePartDto != null) {
                e.setTablePartDto(tablePartDto);
            }
        });
        dataClassApplyDto.setDataClassLabelList(dataClassLabelDtoList);
        dataClassApplyDto.setDataClassLogicList(dataClassLogicDtoList);
        dataClassApplyDto.setDataTableApplyDtoList(dataTableApplyDtoList);
        return dataClassApplyDto;
    }

    @Override
    public List<DataClassApplyDto> all() {
        return this.dataClassApplyMapper.toDto(this.getAll());
    }

}
