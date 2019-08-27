package com.cy.pj.sys.service;



import java.util.List;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.sys.entity.SysRole;

public interface SysRoleService extends PageSerrvice<SysRole>{
	
	int deleteObject(Integer id);
	int saveObject(SysRole entity,Integer[] menuIds);
	 int updateObject(SysRole entity,Integer[] menuIds);
	 
	 List<CheckBox> findObjects();

}
