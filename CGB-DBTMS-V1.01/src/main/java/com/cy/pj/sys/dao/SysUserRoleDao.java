package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.sys.entity.SysUser;

@Mapper
public interface SysUserRoleDao {
	int deleteObjectsByRoleId(Integer roleId);

	@Select("select role_id from sys_user_roles where user_id=#{id}")
	List<Integer> findRoleIdsByUserId(Integer id);

	int insertObjects(
			@Param("userId")Integer userId,
			@Param("roleIds")Integer[]roleIds);


	int deleteObjectsByUserId(Integer userId);
	
	
	
}
