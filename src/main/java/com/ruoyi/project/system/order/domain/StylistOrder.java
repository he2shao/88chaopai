package com.ruoyi.project.system.order.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 预约订单对象 stylist_order
 * 
 * @author ruoyi
 * @date 2020-06-07
 */
public class StylistOrder extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 订单号 */
	private String id;

	/** 预约人姓名 */
	@Excel(name = "预约人姓名")
	private String personName;

	/** 性别 */
	@Excel(name = "性别")
	private String sex;

	/** 年龄 */
	@Excel(name = "年龄")
	private Integer age;

	/** 手机号码 */
	@Excel(name = "手机号码")
	private String phone;

	/** 预约时间 */
	@Excel(name = "预约时间", width = 30, dateFormat = "yyyy-MM-dd")
	private Date orderTime;

	/** 预约人数 */
	@Excel(name = "预约人数")
	private Long orderNum;

	/** 预约发型师 */
	@Excel(name = "预约发型师")
	private Long orderStylist;

	/** 预约项目 */
	@Excel(name = "预约项目")
	private String orderProject;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonName() {
		return personName;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSex() {
		return sex;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge() {
		return age;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderNum(Long orderNum) {
		this.orderNum = orderNum;
	}

	public Long getOrderNum() {
		return orderNum;
	}

	public void setOrderStylist(Long orderStylist) {
		this.orderStylist = orderStylist;
	}

	public Long getOrderStylist() {
		return orderStylist;
	}

	public void setOrderProject(String orderProject) {
		this.orderProject = orderProject;
	}

	public String getOrderProject() {
		return orderProject;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", getId())
				.append("personName", getPersonName()).append("sex", getSex()).append("age", getAge())
				.append("phone", getPhone()).append("orderTime", getOrderTime()).append("orderNum", getOrderNum())
				.append("orderStylist", getOrderStylist()).append("orderProject", getOrderProject())
				.append("createTime", getCreateTime()).append("updateTime", getUpdateTime())
				.append("remark", getRemark()).toString();
	}
}