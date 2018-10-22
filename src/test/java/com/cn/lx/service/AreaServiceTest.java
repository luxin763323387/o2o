package com.cn.lx.service;

import com.cn.lx.BaseTest;
import com.cn.lx.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Steven Lu
 * @date 2018/10/11 -15:46
 */
public class AreaServiceTest extends BaseTest{
    @Autowired
    private AreaService areaService;
    @Test
    public void testGetAreaList(){
        List<Area> areaList = areaService.getAreaList();
        assertEquals("西院",areaList.get(0).getAreaName());
    }
}
