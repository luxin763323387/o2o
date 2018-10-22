package com.cn.lx.service.impl;

import com.cn.lx.dao.AreaDao;
import com.cn.lx.entity.Area;
import com.cn.lx.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Steven Lu
 * @date 2018/10/11 -15:39
 */
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}
