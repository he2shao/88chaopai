package com.ruoyi.project.system.stylist.mapper;

import java.util.List;
import com.ruoyi.project.system.stylist.domain.Stylist;

/**
 * 发型师Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
public interface StylistMapper 
{
    /**
     * 查询发型师
     * 
     * @param id 发型师ID
     * @return 发型师
     */
    public Stylist selectStylistById(Long id);

    /**
     * 查询发型师列表
     * 
     * @param stylist 发型师
     * @return 发型师集合
     */
    public List<Stylist> selectStylistList(Stylist stylist);

    /**
     * 新增发型师
     * 
     * @param stylist 发型师
     * @return 结果
     */
    public int insertStylist(Stylist stylist);

    /**
     * 修改发型师
     * 
     * @param stylist 发型师
     * @return 结果
     */
    public int updateStylist(Stylist stylist);

    /**
     * 删除发型师
     * 
     * @param id 发型师ID
     * @return 结果
     */
    public int deleteStylistById(Long id);

    /**
     * 批量删除发型师
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStylistByIds(String[] ids);
}
