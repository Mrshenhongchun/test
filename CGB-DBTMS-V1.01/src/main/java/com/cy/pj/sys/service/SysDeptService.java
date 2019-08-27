package com.cy.pj.sys.service;

import java.util.List;
import java.util.Map;

import com.cy.pj.sys.entity.SysDept;

public interface SysDeptService {
	List<Map<String,Object>> findObjects();
	int deleteObject(Integer id);
	
	int saveObject(SysDept entity);
	int updateObject(SysDept entity);
}
