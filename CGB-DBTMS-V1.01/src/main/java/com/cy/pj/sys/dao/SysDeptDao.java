package com.cy.pj.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.sys.entity.SysDept;
@Mapper
public interface SysDeptDao {	
	List<Map<String,Object>> findObjects();
	@Select("select count(*) from sys_depts where parentId=#{id}")
	int getChildCount(Integer id);
	@Delete("delete from sys_depts where id=#{id}")
	int deleteObject(Integer id);
	
	int insertObject(SysDept entity);
	int updateObject(SysDept entity);
}
