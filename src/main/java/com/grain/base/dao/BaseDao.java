package com.grain.base.dao;

import com.grain.base.bo.BaseBo;

import java.util.List;

/**
 * @author yuchen
 * @date 2014-11-27
 */
public interface BaseDao {
    /**
     * 插入
     *
     * @param obj
     * @return 返回主键
     */
    boolean insert(BaseBo obj);

    /**
     * 插入
     *
     * @param obj
     * @return 返回主键
     */
    boolean update(BaseBo obj);

    /**
     * 删除
     *
     * @param obj
     */
    void delete(BaseBo obj);

    /**
     * 保存
     *
     * @param obj
     * @return 返回主键
     */
    List<BaseBo> getList(BaseBo obj);

}
