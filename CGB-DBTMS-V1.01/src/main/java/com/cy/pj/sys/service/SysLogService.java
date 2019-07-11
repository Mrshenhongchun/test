package com.cy.pj.sys.service;



import com.cy.pj.sys.entity.SysLog;

public interface SysLogService extends PageSerrvice<SysLog>{
	
	int deleteObjects(Integer... ids);
}
