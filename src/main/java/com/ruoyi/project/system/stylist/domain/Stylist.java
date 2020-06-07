package com.ruoyi.project.system.stylist.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 发型师对象 stylist
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
public class Stylist extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String personName;

    /** 年龄 */
    @Excel(name = "年龄")
    private Integer age;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phone;

    /** 简介 */
    @Excel(name = "简介")
    private String introduction;

    /** 英文名 */
    @Excel(name = "英文名")
    private String englishName;

    /** 照片路径 */
    @Excel(name = "照片路径")
    private String picturePath;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPersonName(String personName) 
    {
        this.personName = personName;
    }

    public String getPersonName() 
    {
        return personName;
    }
    public void setAge(Integer age) 
    {
        this.age = age;
    }

    public Integer getAge() 
    {
        return age;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setIntroduction(String introduction) 
    {
        this.introduction = introduction;
    }

    public String getIntroduction() 
    {
        return introduction;
    }
    public void setEnglishName(String englishName) 
    {
        this.englishName = englishName;
    }

    public String getEnglishName() 
    {
        return englishName;
    }
    public void setPicturePath(String picturePath) 
    {
        this.picturePath = picturePath;
    }

    public String getPicturePath() 
    {
        return picturePath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("personName", getPersonName())
            .append("age", getAge())
            .append("phone", getPhone())
            .append("introduction", getIntroduction())
            .append("englishName", getEnglishName())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("picturePath", getPicturePath())
            .append("remark", getRemark())
            .toString();
    }
}
