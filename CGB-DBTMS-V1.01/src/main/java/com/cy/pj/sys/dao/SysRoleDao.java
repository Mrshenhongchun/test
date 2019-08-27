package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.sys.entity.SysRole;

@Mapper
public interface SysRoleDao extends DefaultDao<SysRole> {

	int deleteObject(Integer id);

	int insertObject(SysRole entity);
	int updateObject(SysRole entity);
	@Select("select id,name from sys_roles")
	List<CheckBox> findObjects();
}
