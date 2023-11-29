package com.example.mongolearn;

import com.example.mongolearn.service.impl.CollectionImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

/**
 * @ClassName: CollectionsTests
 * @Description: 集合测试类
 * @Author: wanghuaiyu
 * @Date: 2023/11/27
 */
@SpringBootTest
public class CollectionsTests {
    @Autowired
    CollectionImpl collection;

    @Test
    void contextLoads() {
    }
    /**
     * 创建集合
     */
    @Test
    void createCollectionTest(){
        String res = collection.createCollection("test");
        System.out.println(res);
    }

    /**
     * 创建限制大小的集合
     */
    @Test
    void createCollectionFixedSizeTest(){
        String res = collection.createCollectionFixedSize("testFixed", 4000L, 20);
        System.out.println(res);
    }

    /**
     * 创建限制条件的集合
     */
    @Test
    void createCollectionValidationTest(){
        String res = collection.createCollectionValidation("testValidation",  16);
        System.out.println(res);
    }

    /**、
     * 获取所有集合名称
     */
    @Test
    void getCollectionsTest(){
        Set<String> collections = collection.getCollections();
        for (String s: collections){
            System.out.println(s);
        }
    }


}
