package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.sys.entity.SysLog;

@Mapper
public interface SysLogDao extends DefaultDao<SysLog>{
	@Delete("delete from sys_logs where id=#{id}")
	int deletaObjiect(Integer id);
	/**
	 * 
	 * @param ids  删除参数
	 * @return
	 */
	int deleteObjects(@Param("ids") Integer... ids);
	
	
	

}
