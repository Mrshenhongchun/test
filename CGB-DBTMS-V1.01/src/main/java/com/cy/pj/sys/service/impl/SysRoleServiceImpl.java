package com.cy.pj.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.service.SysRoleService;
import com.cy.pj.sys.service.exception.ServiceException;

@Service
public class SysRoleServiceImpl extends DefaultSericeImpl<SysRole> implements SysRoleService {
	private SysRoleDao sysRoleDao;
	private SysRoleMenuDao sysRoleMenuDao;
	private SysUserRoleDao sysUserRoleDao;

	@Autowired
	public SysRoleServiceImpl(SysRoleDao sysRoleDao, SysRoleMenuDao sysRoleMenuDao, SysUserRoleDao sysUserRoleDao) {
		super(sysRoleDao);
		this.sysRoleDao = sysRoleDao;
		this.sysRoleMenuDao = sysRoleMenuDao;
		this.sysUserRoleDao = sysUserRoleDao;
	}

	@Override
	public int deleteObject(Integer id) {
		if (id == null || id < 1) {
			throw new ServiceException("id值不正确，id" + id);
		}
		sysRoleMenuDao.deleteObjectsByRoleId(id);
		sysUserRoleDao.deleteObjectsByRoleId(id);
		int rows = sysRoleDao.deleteObject(id);
		if (rows == 0) {
			throw new ServiceException("记录可能已经删除");
		}

		return rows;
	}

	@Override
	public int saveObject(SysRole entity, Integer[] menuIds) {
		// 1.合法性验证
		if (entity == null)
			throw new ServiceException("保存数据不能为空");
		if (StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("角色名不能为空");
		if (menuIds == null || menuIds.length == 0)
			throw new ServiceException("必须为角色赋予权限");
		// 2.保存数据
		int rows = sysRoleDao.insertObject(entity);
		sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
		// 3.返回结果
		return rows;

	}

	@Override
	public int updateObject(SysRole entity, Integer[] menuIds) {
		// 1.合法性验证
		if (entity == null)
			throw new ServiceException("更新的对象不能为空");
		if (entity.getId() == null)
			throw new ServiceException("id的值不能为空");

		if (StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("角色名不能为空");
		if (menuIds == null || menuIds.length == 0)
			throw new ServiceException("必须为角色指定一个权限");

		// 2.更新数据
		int rows = sysRoleDao.updateObject(entity);
		if (rows == 0)
			throw new ServiceException("对象可能已经不存在");
		sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());
		sysRoleMenuDao.insertObjects(entity.getId(), menuIds);

		// 3.返回结果
		return rows;
	}

}
