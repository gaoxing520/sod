package com.piesat.dm.rpc.dto.dataclass;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.piesat.dm.rpc.dto.datatable.DataTableInfoDto;
import com.piesat.dm.rpc.dto.datatable.TablePartDto;
import com.piesat.dm.rpc.dto.datatable.TableColumnDto;
import com.piesat.dm.rpc.dto.datatable.TableIndexDto;
import com.piesat.util.BaseDto;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

/**
 * @author cwh
 * @program: sod
 * @description: 资料申请
 * @date 2021年 01月18日 18:30:51
 */
@Data
public class DataClassApplyDto extends BaseDto {

    /**
     * 四级编码
     */
    private String dDataId;

    /**
     * 存储编码
     */
    private String dataClassId;


    /**
     * 父节点id
     */
    private String parentId;


    /**
     * 序号
     */
    private Integer serial;

    /**
     * 名称
     */
    private String className;


    /**
     * 表英文名称
     */
    private String tableName;


    /**
     * 数据库ID
     */
    private String databaseId;

    /**
     * 数据库名称
     */
    private String databaseName;

    /**
     * 表描述
     */
    private String tableDesc;

    /**
     * 是否公开
     */
    private Integer isAccess;

    /**
     * 提交类型（1.关联表，2.新增表）
     */
    private Integer subType;

    /**
     * 审核状态
     * 1-待审核 ,2-审核通过 ,3-审核不通过，4-删除申请中，5-已删除
     */
    private Integer status;

    /**
     * 申请人
     */
    private String userId;

    /**
     * 审核人
     */
    private String reviewer;

    /**
     * 审核信息
     */
    private String reviewNotes;

    /**
     * 审核时间
     */
    private Date reviewTime;

    /**
     * 申请备注
     */
    private String remark;


    private List<TableColumnDto> columns;
    private List<TableIndexDto> tableIndexList;
    private TablePartDto tablePart;
    private List<DataClassLogicDto> dataLogicList;

    public DataClassDto getDataClass() {
        DataClassDto d = new DataClassDto();
        BeanUtils.copyProperties(this, d);
        return d;
    }

    public DataTableInfoDto getTableInfo() {
        DataTableInfoDto t = new DataTableInfoDto();
        BeanUtils.copyProperties(this, t);
        return t;
    }
}
