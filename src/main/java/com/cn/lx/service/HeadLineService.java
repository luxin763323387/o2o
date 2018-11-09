package com.cn.lx.service;

import com.cn.lx.entity.HeadLine;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

/**
 * @author Steven Lu
 * @date 2018/11/9 -15:53
 */
public interface HeadLineService {

    List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException;
}
