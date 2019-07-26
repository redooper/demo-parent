package com.redooper.bloom.filter.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.BloomOperations;

import java.util.Arrays;

/**
 * @Auther: Jackie
 * @Date: 2019-07-26 15:31
 * @Description:
 */
@Slf4j
@SpringBootApplication
public class BloomFilterApplication implements ApplicationRunner {

    @Autowired
    private BloomOperations bloomOperations;

    public static void main(String[] args) {
        SpringApplication.run(BloomFilterApplication.class, args);
    }

    /*
        日志输出如下：
        test add result: true
        test addMulti result: [false, true]
        test exists result: true
        test existsMulti result: [true, false]
        test delete result: true
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String key = "TEST";

        // 1.创建布隆过滤器
        bloomOperations.createFilter(key, 0.01, 100);

        // 2.添加一个元素
        Boolean foo = bloomOperations.add(key, "foo");
        log.info("test add result: {}", foo);

        // 3.批量添加元素
        Boolean[] addMulti = bloomOperations.addMulti(key, "foo", "bar");
        log.info("test addMulti result: {}", Arrays.toString(addMulti));

        // 4.校验一个元素是否存在
        Boolean exists = bloomOperations.exists(key, "foo");
        log.info("test exists result: {}", exists);

        // 5.批量校验元素是否存在
        Boolean[] existsMulti = bloomOperations.existsMulti(key, "foo", "foo1");
        log.info("test existsMulti result: {}", Arrays.toString(existsMulti));

        // 6.删除布隆过滤器
        Boolean delete = bloomOperations.delete(key);
        log.info("test delete result: {}", delete);
    }
}
