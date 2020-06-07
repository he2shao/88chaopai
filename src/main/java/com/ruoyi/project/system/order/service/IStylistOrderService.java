package com.ruoyi.project.system.order.service;

import java.util.List;
import com.ruoyi.project.system.order.domain.StylistOrder;

/**
 * 预约订单Service接口
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
public interface IStylistOrderService 
{
    /**
     * 查询预约订单
     * 
     * @param id 预约订单ID
     * @return 预约订单
     */
    public StylistOrder selectStylistOrderById(String id);

    /**
     * 查询预约订单列表
     * 
     * @param stylistOrder 预约订单
     * @return 预约订单集合
     */
    public List<StylistOrder> selectStylistOrderList(StylistOrder stylistOrder);

    /**
     * 新增预约订单
     * 
     * @param stylistOrder 预约订单
     * @return 结果
     */
    public int insertStylistOrder(StylistOrder stylistOrder);

    /**
     * 修改预约订单
     * 
     * @param stylistOrder 预约订单
     * @return 结果
     */
    public int updateStylistOrder(StylistOrder stylistOrder);

    /**
     * 批量删除预约订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStylistOrderByIds(String ids);

    /**
     * 删除预约订单信息
     * 
     * @param id 预约订单ID
     * @return 结果
     */
    public int deleteStylistOrderById(String id);
}
