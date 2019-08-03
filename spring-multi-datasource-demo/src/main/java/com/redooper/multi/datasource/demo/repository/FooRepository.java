package com.redooper.multi.datasource.demo.repository;

import com.redooper.multi.datasource.demo.model.Foo;
import org.springframework.data.repository.CrudRepository;

/**
 * @Auther: Jackie
 * @Date: 2019-08-02 12:49
 * @Description:
 */
public interface FooRepository extends CrudRepository<Foo, Long> {
}
