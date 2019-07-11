package com.cy.pj.sys.service;

import com.cy.pj.common.vo.PageObject;

public interface PageSerrvice<T> {
	PageObject<T> findPageObjects(String name,Integer pageCurre);
}
