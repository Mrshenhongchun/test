package com.cy.pj.sys.service.impl;

import java.util.List;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.DefaultDao;
import com.cy.pj.sys.service.PageSerrvice;
import com.cy.pj.sys.service.exception.ServiceException;

public abstract  class DefaultSericeImpl<T> implements PageSerrvice<T>{
	private DefaultDao<T> defaultDao;	
	
	public DefaultSericeImpl(DefaultDao<T> defaultDao) {		
		this.defaultDao = defaultDao;
	}


	public PageObject<T> findPageObjects(String name, Integer pageCurrent) {
		// 1.参数是否合法
		if (pageCurrent == null || pageCurrent < 1) {
			throw new IllegalArgumentException("当前页码不正确");
		}
		// 2.基于条件查询记录总数
		int rowCount = defaultDao.getRowCount(name);
		if (rowCount == 0) {
			throw new ServiceException("记录不存在");
		}
		// 3.基于条件查询当前页面记录
		int pageSize = 3;
		int startIndex = (pageCurrent - 1) * pageSize;
		List<T> records = defaultDao.findPageObjects(name, startIndex, pageSize);
		// 4.对分页信息及当前信息进行封装
		PageObject<T> pageObject = new PageObject<>();
		pageObject.setPageCount((rowCount - 1) / pageSize + 1);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRecords(records);
		pageObject.setRowCount(rowCount);
		return pageObject;
	}


	
}
