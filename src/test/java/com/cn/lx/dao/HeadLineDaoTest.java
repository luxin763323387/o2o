package com.cn.lx.dao;

import com.cn.lx.BaseTest;
import com.cn.lx.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Steven Lu
 * @date 2018/11/9 -15:16
 */
public class HeadLineDaoTest extends BaseTest {
    @Autowired
    private HeadLineDao headLineDao;

    @Test
    //根据传入的查询条件(查询headLine的列表)测试
    public void testHeadLineDao(){
        List<HeadLine> headLineList = headLineDao.queryHeadLineList(new HeadLine());
        assertEquals(1,headLineList.size());
    }
}
