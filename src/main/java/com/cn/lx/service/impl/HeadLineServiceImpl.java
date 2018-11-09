package com.cn.lx.service.impl;

import com.cn.lx.dao.HeadLineDao;
import com.cn.lx.entity.HeadLine;
import com.cn.lx.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author Steven Lu
 * @date 2018/11/9 -15:55
 */
@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Autowired
    private HeadLineDao headLineDao;

    @Override
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
        return headLineDao.queryHeadLineList(headLineCondition);
    }
}
