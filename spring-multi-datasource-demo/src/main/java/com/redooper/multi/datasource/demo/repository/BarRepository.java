package com.redooper.multi.datasource.demo.repository;

import com.redooper.multi.datasource.demo.model.Bar;
import org.springframework.data.repository.CrudRepository;

/**
 * @Auther: Jackie
 * @Date: 2019-08-02 12:49
 * @Description:
 */
public interface BarRepository extends CrudRepository<Bar, Long> {
}
