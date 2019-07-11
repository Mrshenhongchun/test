package com.cy;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.entity.SysLog;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysLogDaoTests {
	@Autowired
	private SysLogDao sysLogDao;
	
	@Test
	public void testfindPageObjects() {
		List<SysLog> list = sysLogDao.findPageObjects("admin", 0, 10);
		for(SysLog s:list) {
			System.out.println(s);
		}
	}
	@Test
	public void testgetRowCount() {
		int rows = sysLogDao.getRowCount("admin");
		System.out.println(rows);
	}

	@Test
	public void testDataSource() throws Exception {
		int rows = sysLogDao.deletaObjiect(20);
		System.out.println("rows=" + rows);

	}

	@Test
	public void testSysLogDao() {
		int rows = sysLogDao.deleteObjects(17, 18);
		System.out.println("rows=" + rows);
	}
}
