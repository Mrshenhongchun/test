package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.vo.SysUserDeptVo;

@Mapper
public interface SysUserDao {
	@Select("select count(*) from sys_users where deptId=#{deptId}")
	int getUserCountByDeptId(Integer DeptId);

	@Select("select * from sys_users where username=#{username}")
	SysUser findUserByUserName(String username);

	List<SysUserDeptVo> findPageObjects(
			@Param("username") String username, 
			@Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);

	int getRowCount(@Param("username") String username);
	
	int validById(
			@Param("id")Integer id,
			@Param("valid")Integer valid,
			@Param("modifiedUser")String modifiedUser);
	
	int insertObject(SysUser entity);	
	
	@Select("select * from sys_users where id=#{id}")
	SysUserDeptVo findObjectById(Integer id);
	
	int updateObject(SysUser entity);
	
	
	
	
	/**
	 * 基于用户id修改用户的密码
	 * @param password 新的密码
	 * @param salt 密码加密时使用的盐值
	 * @param id 用户id
	 * @return
	 */
	int updatePassword(
			@Param("password")String password,
			@Param("salt")String salt,
			@Param("id")Integer id);
	
	
	
	




}
