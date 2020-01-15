package com.piesat.sod.system.rpc.api;

import java.util.List;

import com.piesat.common.grpc.annotation.GrpcHthtService;
import com.piesat.common.grpc.constant.SerializeType;
import com.piesat.sod.system.rpc.dto.DictionaryDto;
import com.piesat.util.page.PageBean;
import com.piesat.util.page.PageForm;

/** 字典表管理
*@description
*@author wlg
*@date 2020年1月14日下午4:57:46
*
*/
@GrpcHthtService(server = "dictionary",serialization = SerializeType.PROTOSTUFF)
public interface DictionaryService {
	/**
	 *  获取分页数据
	 * @description 
	 * @author wlg
	 * @date 2020年1月14日下午5:02:17
	 * @param pageForm
	 * @return
	 * @throws Exception
	 */
	PageBean findPageData(PageForm<DictionaryDto> pageForm) throws Exception;
	/**
	 *  获取字典分组
	 * @description 
	 * @author wlg
	 * @date 2020年1月15日上午8:49:31
	 * @param menu
	 * @return
	 * @throws Exception
	 */
	List<DictionaryDto> findMenu(String menu) throws Exception;
	/**
	 *  新增字典分组
	 * @description 
	 * @author wlg
	 * @date 2020年1月15日上午9:47:10
	 * @param dictionaryDto
	 * @throws Exception
	 */
	void addType(DictionaryDto dictionaryDto) throws Exception;
	/**
	 *  主键查询
	 * @description 
	 * @author wlg
	 * @date 2020年1月15日上午10:15:38
	 * @param id
	 * @return
	 * @throws Exception
	 */
	DictionaryDto findById(String id) throws Exception;
	/**
	 *  修改
	 * @description 
	 * @author wlg
	 * @date 2020年1月15日上午10:48:27
	 * @param dictionaryDto
	 * @throws Exception
	 */
	void updateDictionary(DictionaryDto dictionaryDto) throws Exception;
	
	/**
	 *  根据id批量删除
	 * @description 
	 * @author wlg
	 * @date 2020年1月15日上午11:41:37
	 * @param ids
	 * @throws Exception
	 */
	void deleteByIds (String ids) throws Exception;

}