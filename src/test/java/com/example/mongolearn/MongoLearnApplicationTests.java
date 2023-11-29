package com.example.mongolearn;

import com.example.mongolearn.service.impl.CollectionImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class MongoLearnApplicationTests {
    @Autowired
    CollectionImpl collection;

    @Test
    void contextLoads() {
    }
    /**
     * 创建集合
     */
    @Test
    void createCollection(){
        String res = collection.createCollection("test");
        System.out.println(res);
    }

}
