package com.ruoyi.project.system.order;

/**
 * 订单号生成
 * 
 * @author mac
 *
 */
public class OrderNoBuilder {

	private OrderNoBuilder() {
	}

	/**
	 * 创建订单号
	 * 
	 * @return
	 */
	public static String createOrderNo() {
		return String.valueOf(System.currentTimeMillis());
	}
}
