package com.ruoyi.project.system.order.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.order.mapper.StylistOrderMapper;
import com.ruoyi.project.system.order.OrderNoBuilder;
import com.ruoyi.project.system.order.domain.StylistOrder;
import com.ruoyi.project.system.order.service.IStylistOrderService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 预约订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
@Service
public class StylistOrderServiceImpl implements IStylistOrderService {
	@Autowired
	private StylistOrderMapper stylistOrderMapper;

	/**
	 * 查询预约订单
	 * 
	 * @param id 预约订单ID
	 * @return 预约订单
	 */
	@Override
	public StylistOrder selectStylistOrderById(String id) {
		return stylistOrderMapper.selectStylistOrderById(id);
	}

	/**
	 * 查询预约订单列表
	 * 
	 * @param stylistOrder 预约订单
	 * @return 预约订单
	 */
	@Override
	public List<StylistOrder> selectStylistOrderList(StylistOrder stylistOrder) {
		return stylistOrderMapper.selectStylistOrderList(stylistOrder);
	}

	/**
	 * 新增预约订单
	 * 
	 * @param stylistOrder 预约订单
	 * @return 结果
	 */
	@Override
	public int insertStylistOrder(StylistOrder stylistOrder) {
		stylistOrder.setId(OrderNoBuilder.createOrderNo());
		stylistOrder.setCreateTime(DateUtils.getNowDate());
		return stylistOrderMapper.insertStylistOrder(stylistOrder);
	}

	/**
	 * 修改预约订单
	 * 
	 * @param stylistOrder 预约订单
	 * @return 结果
	 */
	@Override
	public int updateStylistOrder(StylistOrder stylistOrder) {
		stylistOrder.setUpdateTime(DateUtils.getNowDate());
		return stylistOrderMapper.updateStylistOrder(stylistOrder);
	}

	/**
	 * 删除预约订单对象
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteStylistOrderByIds(String ids) {
		return stylistOrderMapper.deleteStylistOrderByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除预约订单信息
	 * 
	 * @param id 预约订单ID
	 * @return 结果
	 */
	@Override
	public int deleteStylistOrderById(String id) {
		return stylistOrderMapper.deleteStylistOrderById(id);
	}
}
