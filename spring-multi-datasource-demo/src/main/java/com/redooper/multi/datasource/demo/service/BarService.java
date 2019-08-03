package com.redooper.multi.datasource.demo.service;

import com.redooper.multi.datasource.demo.model.Bar;

/**
 * @Auther: Jackie
 * @Date: 2019-08-03 19:34
 * @Description:
 */
public interface BarService {

    Iterable<Bar> findAll();

    void findAll(int i);

    void save(Bar bar);

}
