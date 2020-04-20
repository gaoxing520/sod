package com.piesat.dm.rpc.dto.dataapply;

import com.piesat.util.BaseDto;
import lombok.Data;

/**
 * @author yaya
 * @description TODO
 * @date 2020/4/18 21:01
 */
@Data
public class NewdataTableColumnDto extends BaseDto {
    /**
     * 申请记录id
     */
    private String applyId;

    /**
     * 字段名称
     * c_element_code
     */
    private String cElementCode;

    /**
     * 中文名称
     * ele_name
     */
    private String eleName;

    /**
     * 字段类型
     * type
     */
    private String type;

    /**
     * 字段精度
     * accuracy
     */
    private String accuracy;

    /**
     * 英文单位
     * unit
     */
    private String unit;

    /**
     * 是否可为空
     * is_null
     */
    private Boolean isNull;

    /**
     * 是否主键
     * is_primary_key
     */
    private Boolean isPrimaryKey;

    /**
     * 序号
     * serial_number
     */
    private Integer serialNumber;
}
