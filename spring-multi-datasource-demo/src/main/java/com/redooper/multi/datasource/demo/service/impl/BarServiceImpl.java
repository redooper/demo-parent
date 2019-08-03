package com.redooper.multi.datasource.demo.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.redooper.multi.datasource.annotation.LookupKey;
import com.redooper.multi.datasource.demo.enums.LookupKeyEnums;
import com.redooper.multi.datasource.demo.model.Bar;
import com.redooper.multi.datasource.demo.repository.BarRepository;
import com.redooper.multi.datasource.demo.service.BarService;
import com.redooper.multi.datasource.demo.service.FooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: Jackie
 * @Date: 2019-08-03 19:35
 * @Description:
 */
@Slf4j
@Service
public class BarServiceImpl implements BarService {

    @Autowired
    private BarRepository barRepository;

    @Autowired
    private FooService fooService;

    @Autowired
    private BarService barService;

    @Override
    @LookupKey(LookupKeyEnums.DB_BAR)
    public Iterable<Bar> findAll() {
        return barRepository.findAll();
    }

    @Override
    @LookupKey(LookupKeyEnums.DB_BAR)
    public void findAll(int i) {
        if (i == 100) {
            return;
        }

        if (RandomUtil.randomInt(100) % 3 == 0) {
            fooService.findAll(++i);
        } else {
            barService.findAll(++i);
        }

        Iterable<Bar> bars = barRepository.findAll();
        bars.forEach(bar -> log.info("Bar: {}", JSON.toJSONString(bar)));
    }

    @Override
    @LookupKey(LookupKeyEnums.DB_BAR)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(Bar bar) {
        barRepository.save(bar);
    }
}
