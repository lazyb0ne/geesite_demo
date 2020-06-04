/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.gen.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.gen.entity.Project;

/**
 * 代码生成表DAO接口
 * @author lazybone
 * @version 2020-06-04
 */
@MyBatisDao
public interface ProjectDao extends CrudDao<Project> {
	
}