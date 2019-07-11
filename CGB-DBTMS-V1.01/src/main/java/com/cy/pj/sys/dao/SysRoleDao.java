package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.cy.pj.sys.entity.SysRole;

@Mapper
public interface SysRoleDao extends DefaultDao<SysRole> {

	int deleteObject(Integer id);

	int insertObject(SysRole entity);
	int updateObject(SysRole entity);
}
