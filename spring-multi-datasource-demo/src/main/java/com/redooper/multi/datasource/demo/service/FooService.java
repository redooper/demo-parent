package com.redooper.multi.datasource.demo.service;

import com.redooper.multi.datasource.demo.model.Foo;

/**
 * @Auther: Jackie
 * @Date: 2019-08-03 19:34
 * @Description:
 */
public interface FooService {

    Iterable<Foo> findAll();

    void findAll(int i);

    void save(Foo foo);

}
