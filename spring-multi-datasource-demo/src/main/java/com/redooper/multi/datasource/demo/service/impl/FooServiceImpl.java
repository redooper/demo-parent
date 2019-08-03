package com.redooper.multi.datasource.demo.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.redooper.multi.datasource.annotation.LookupKey;
import com.redooper.multi.datasource.demo.enums.LookupKeyEnums;
import com.redooper.multi.datasource.demo.model.Bar;
import com.redooper.multi.datasource.demo.model.Foo;
import com.redooper.multi.datasource.demo.repository.FooRepository;
import com.redooper.multi.datasource.demo.service.BarService;
import com.redooper.multi.datasource.demo.service.FooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: Jackie
 * @Date: 2019-08-03 19:35
 * @Description:
 */
@Slf4j
@Service
@LookupKey(LookupKeyEnums.DB_FOO)
public class FooServiceImpl implements FooService {

    @Autowired
    private FooRepository fooRepository;

    @Autowired
    private FooService fooService;

    @Autowired
    private BarService barService;

    @Override
    public Iterable<Foo> findAll() {
        return fooRepository.findAll();
    }

    @Override
    public void findAll(int i) {
        if (i == 100) {
            return;
        }

        if (RandomUtil.randomInt(100) % 3 == 0) {
            fooService.findAll(++i);
        } else {
            barService.findAll(++i);
        }

        Iterable<Foo> foos = fooRepository.findAll();
        foos.forEach(foo -> log.info("Foo: {}", JSON.toJSONString(foo)));
    }

    @Override
    @Transactional
    public void save(Foo foo) {
        fooRepository.save(foo);

        Snowflake snowflake = IdUtil.getSnowflake(0, 0);
        Bar bar = Bar.builder()
                .id(snowflake.nextId())
                .name("Jackie")
                .age(28)
                .tableName("t_bar")
                .build();
        barService.save(bar);

//        throw new RuntimeException("Rollback current tx!");
    }
}
