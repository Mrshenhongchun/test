package com.cy.pj.sys.service.impl;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;
import com.cy.pj.sys.service.exception.ServiceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SysLogServiceImpl extends DefaultSericeImpl<SysLog> implements SysLogService {

	private SysLogDao sysLogDao;

	@Autowired
	public SysLogServiceImpl(SysLogDao sysLogDao) {
		super(sysLogDao);
		this.sysLogDao = sysLogDao;
	}

	//@Async("asuncThread")
	@Async
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void saveObjiet(SysLog entity) {
		log.info("log.current.thread" + Thread.currentThread().hashCode());
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}
		sysLogDao.insertObject(entity);

	}

	@RequiresPermissions("sys:log:delete")
	@RequiredLog
	@Override
	public int deleteObjects(Integer... ids) {
		// 1.验证数据是否合法
		if (ids == null || ids.length < 1) {
			throw new IllegalArgumentException("请选择一个");
		}
		// 2.执行删除操作
		int rows;
		try {
			rows = sysLogDao.deleteObjects(ids);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统异常，维护中....");
		}

		// 3.对结果进行验证
		if (rows == 0) {
			throw new ServiceException("记录可能已经不存在了");
		}
		// 4.返回结果
		return rows;
	}

}
