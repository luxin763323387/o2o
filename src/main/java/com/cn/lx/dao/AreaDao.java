package com.cn.lx.dao;

import com.cn.lx.entity.Area;

import java.util.List;

/**
 * @author Steven Lu
 * @date 2018/10/11 -14:27
 */
public interface AreaDao {
    /**
     * 列出区域列表
     * @return areaList
     */
    List<Area> queryArea();
}
