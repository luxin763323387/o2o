package com.cn.lx.dao;

import com.cn.lx.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Steven Lu
 * @date 2018/11/9 -15:02
 */
public interface HeadLineDao  {
    /**
     * 根据传入的查询条件(查询headLine的列表)
     * @param headLineCondition
     * @return
     */
    List<HeadLine> queryHeadLineList(@Param("headLineCondition")HeadLine headLineCondition);
}
