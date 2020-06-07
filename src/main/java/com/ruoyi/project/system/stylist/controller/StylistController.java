package com.ruoyi.project.system.stylist.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.stylist.domain.Stylist;
import com.ruoyi.project.system.stylist.service.IStylistService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 发型师Controller
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
@Controller
@RequestMapping("/system/stylist")
public class StylistController extends BaseController {
	private String prefix = "system/stylist";

	@Autowired
	private IStylistService stylistService;

	@RequiresPermissions("system:stylist:view")
	@GetMapping()
	public String stylist() {
		return prefix + "/stylist";
	}

	@GetMapping("/{id}")
	public String stylistDetail(@PathVariable("id") String id, ModelMap mmap) {
		Stylist stylist = stylistService.selectStylistById(Long.valueOf(id));
		mmap.put("stylist", stylist);
		return prefix + "/detail";
	}

	/**
	 * 查询发型师列表
	 */
	@RequiresPermissions("system:stylist:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Stylist stylist) {
		startPage();
		List<Stylist> list = stylistService.selectStylistList(stylist);
		return getDataTable(list);
	}

	/**
	 * 导出发型师列表
	 */
	@RequiresPermissions("system:stylist:export")
	@Log(title = "发型师", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(Stylist stylist) {
		List<Stylist> list = stylistService.selectStylistList(stylist);
		ExcelUtil<Stylist> util = new ExcelUtil<Stylist>(Stylist.class);
		return util.exportExcel(list, "stylist");
	}

	/**
	 * 新增发型师
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存发型师
	 */
	@RequiresPermissions("system:stylist:add")
	@Log(title = "发型师", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Stylist stylist) {
		return toAjax(stylistService.insertStylist(stylist));
	}

	/**
	 * 修改发型师
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		Stylist stylist = stylistService.selectStylistById(id);
		mmap.put("stylist", stylist);
		return prefix + "/edit";
	}

	/**
	 * 修改保存发型师
	 */
	@RequiresPermissions("system:stylist:edit")
	@Log(title = "发型师", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Stylist stylist) {
		return toAjax(stylistService.updateStylist(stylist));
	}

	/**
	 * 删除发型师
	 */
	@RequiresPermissions("system:stylist:remove")
	@Log(title = "发型师", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(stylistService.deleteStylistByIds(ids));
	}
}
