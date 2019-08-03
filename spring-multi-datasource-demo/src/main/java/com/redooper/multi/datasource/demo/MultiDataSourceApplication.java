package com.redooper.multi.datasource.demo;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.redooper.multi.datasource.demo.model.Bar;
import com.redooper.multi.datasource.demo.model.Foo;
import com.redooper.multi.datasource.demo.service.BarService;
import com.redooper.multi.datasource.demo.service.FooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: Jackie
 * @Date: 2019-08-03 19:19
 * @Description:
 */
@Slf4j
@SpringBootApplication
public class MultiDataSourceApplication implements ApplicationRunner {

    @Autowired
    private FooService fooService;

    @Autowired
    private BarService barService;

    public static void main(String[] args) {
        SpringApplication.run(MultiDataSourceApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 1.测试LookupKey注解于类上
        Iterable<Foo> foos = fooService.findAll();
        foos.forEach(foo -> log.info("Foo: {}", JSON.toJSONString(foo)));

        // 2.测试LookupKey注解于方法上
        Iterable<Bar> bars = barService.findAll();
        bars.forEach(bar -> log.info("Bar: {}", JSON.toJSONString(bar)));

        // 3.测试LookupKey嵌套使用
        fooService.findAll(0);

        // 4.测试事务
        Snowflake snowflake = IdUtil.getSnowflake(0, 0);
        Foo foo = Foo.builder()
                .id(snowflake.nextId())
                .name("Jackie")
                .age(28)
                .tableName("t_foo")
                .build();
        fooService.save(foo);
    }
}
