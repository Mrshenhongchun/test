package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface DefaultDao<T> {
	List<T> findPageObjects(
			@Param("name") String name, 
			@Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);
	int getRowCount(@Param("name")String name);
}
