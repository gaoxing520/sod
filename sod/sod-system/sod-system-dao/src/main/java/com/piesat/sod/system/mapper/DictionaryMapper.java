package com.piesat.sod.system.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.piesat.sod.system.entity.DictionaryEntity;

/** 字典表 Mybatis
*@description
*@author wlg
*@date 2020年1月14日下午5:11:47
*
*/
@Component
public interface DictionaryMapper {

	/**
	 *  条件查询
	 * @description 
	 * @author wlg
	 * @date 2020年1月14日下午5:13:11
	 * @param dictionaryEntity
	 * @return
	 */
	public List<DictionaryEntity> selectList(DictionaryEntity dictionaryEntity);
	
	/**
	 *  获得最大type
	 * @description 
	 * @author wlg
	 * @date 2020年1月15日上午9:50:50
	 * @return
	 */
	public Integer selectMaxType();

	/**
	 * 根据type查询字典数据
	 * @param type
	 * @return
	 */
	public List<DictionaryEntity> findByType(Integer type);
}
