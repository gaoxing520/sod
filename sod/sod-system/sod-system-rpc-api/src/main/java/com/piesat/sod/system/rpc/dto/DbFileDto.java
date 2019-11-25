package com.piesat.sod.system.rpc.dto;

import java.io.Serializable;

import lombok.Data;

/** 数据库文件表信息DTO
*@description
*@author wlg
*@date 2019年11月20日上午11:39:26
*
*/
@Data
public class DbFileDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 707151325787729909L;
	/**
	 * id
	 */
	private String recordId;
	/**
	 * 文件类型
	 */
	private String fileType;
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 存储名称
	 */
	private String fileStorName;
	/**
	 * 存储路径
	 */
	private String fileStorPath;
	/**
	 * 更新时间
	 */
	private String updateTime;
	/**
	 * 是否是图片
	 */
	private String filePictrue;
	/**
	 * 文档后缀
	 */
	private String fileSuffix;
}