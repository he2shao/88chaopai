package com.ruoyi.project.system.order.controller;

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

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.dict.domain.DictData;
import com.ruoyi.project.system.dict.service.IDictTypeService;
import com.ruoyi.project.system.order.domain.StylistOrder;
import com.ruoyi.project.system.order.service.IStylistOrderService;

/**
 * 预约订单Controller
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
@Controller
@RequestMapping("/system/order")
public class StylistOrderController extends BaseController {
	private String prefix = "system/order";

	@Autowired
	private IStylistOrderService stylistOrderService;
	@Autowired
	private IDictTypeService dictTypeService;

	@RequiresPermissions("system:order:view")
	@GetMapping()
	public String order() {
		return prefix + "/order";
	}

	/**
	 * 查询预约订单列表
	 */
	@RequiresPermissions("system:order:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(StylistOrder stylistOrder) {
		startPage();
		List<StylistOrder> list = stylistOrderService.selectStylistOrderList(stylistOrder);
		return getDataTable(list);
	}

	/**
	 * 导出预约订单列表
	 */
	@RequiresPermissions("system:order:export")
	@Log(title = "预约订单", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(StylistOrder stylistOrder) {
		List<StylistOrder> list = stylistOrderService.selectStylistOrderList(stylistOrder);
		ExcelUtil<StylistOrder> util = new ExcelUtil<StylistOrder>(StylistOrder.class);
		return util.exportExcel(list, "order");
	}

	/**
	 * 新增预约订单
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap) {

		List<DictData> projects = dictTypeService.selectDictDataByType("stylist_order_project");

		mmap.put("projects", projects);
		mmap.put("path", "/profile/avatar/2020/06/08/1796f940b7ca5aded32cf239f012a573.png");
		mmap.put("path2", "/profile/avatar/2020/06/08/1796f940b7ca5aded32cf239f012a573.png");
		return prefix + "/add";
	}

	/**
	 * 新增保存预约订单
	 */
	@RequiresPermissions("system:order:add")
	@Log(title = "预约订单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(StylistOrder stylistOrder) {
		return toAjax(stylistOrderService.insertStylistOrder(stylistOrder));
	}

	/**
	 * 修改预约订单
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap) {
		StylistOrder stylistOrder = stylistOrderService.selectStylistOrderById(id);
		mmap.put("stylistOrder", stylistOrder);
		return prefix + "/edit";
	}

	/**
	 * 修改保存预约订单
	 */
	@RequiresPermissions("system:order:edit")
	@Log(title = "预约订单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(StylistOrder stylistOrder) {
		return toAjax(stylistOrderService.updateStylistOrder(stylistOrder));
	}

	/**
	 * 删除预约订单
	 */
	@RequiresPermissions("system:order:remove")
	@Log(title = "预约订单", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(stylistOrderService.deleteStylistOrderByIds(ids));
	}
}
