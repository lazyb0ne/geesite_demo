/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.gen.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.gen.entity.Project;
import com.jeesite.modules.gen.service.ProjectService;

/**
 * 代码生成表Controller
 * @author lazybone
 * @version 2020-06-04
 */
@Controller
@RequestMapping(value = "${adminPath}/gen/project")
public class ProjectController extends BaseController {

	@Autowired
	private ProjectService projectService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Project get(String tableName, boolean isNewRecord) {
		return projectService.get(tableName, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("gen:project:view")
	@RequestMapping(value = {"list", ""})
	public String list(Project project, Model model) {
		model.addAttribute("project", project);
		return "modules/gen/projectList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("gen:project:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Project> listData(Project project, HttpServletRequest request, HttpServletResponse response) {
		project.setPage(new Page<>(request, response));
		Page<Project> page = projectService.findPage(project);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("gen:project:view")
	@RequestMapping(value = "form")
	public String form(Project project, Model model) {
		model.addAttribute("project", project);
		return "modules/gen/projectForm";
	}

	/**
	 * 保存代码生成表
	 */
	@RequiresPermissions("gen:project:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Project project) {
		projectService.save(project);
		return renderResult(Global.TRUE, text("保存代码生成表成功！"));
	}
	
	/**
	 * 删除代码生成表
	 */
	@RequiresPermissions("gen:project:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Project project) {
		projectService.delete(project);
		return renderResult(Global.TRUE, text("删除代码生成表成功！"));
	}
	
}