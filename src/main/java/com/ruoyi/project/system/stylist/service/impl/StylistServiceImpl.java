package com.ruoyi.project.system.stylist.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.stylist.mapper.StylistMapper;
import com.ruoyi.project.system.stylist.domain.Stylist;
import com.ruoyi.project.system.stylist.service.IStylistService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 发型师Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
@Service
public class StylistServiceImpl implements IStylistService 
{
    @Autowired
    private StylistMapper stylistMapper;

    /**
     * 查询发型师
     * 
     * @param id 发型师ID
     * @return 发型师
     */
    @Override
    public Stylist selectStylistById(Long id)
    {
        return stylistMapper.selectStylistById(id);
    }

    /**
     * 查询发型师列表
     * 
     * @param stylist 发型师
     * @return 发型师
     */
    @Override
    public List<Stylist> selectStylistList(Stylist stylist)
    {
        return stylistMapper.selectStylistList(stylist);
    }

    /**
     * 新增发型师
     * 
     * @param stylist 发型师
     * @return 结果
     */
    @Override
    public int insertStylist(Stylist stylist)
    {
        stylist.setCreateTime(DateUtils.getNowDate());
        return stylistMapper.insertStylist(stylist);
    }

    /**
     * 修改发型师
     * 
     * @param stylist 发型师
     * @return 结果
     */
    @Override
    public int updateStylist(Stylist stylist)
    {
        stylist.setUpdateTime(DateUtils.getNowDate());
        return stylistMapper.updateStylist(stylist);
    }

    /**
     * 删除发型师对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteStylistByIds(String ids)
    {
        return stylistMapper.deleteStylistByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除发型师信息
     * 
     * @param id 发型师ID
     * @return 结果
     */
    @Override
    public int deleteStylistById(Long id)
    {
        return stylistMapper.deleteStylistById(id);
    }
}
