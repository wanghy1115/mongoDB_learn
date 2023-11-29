package com.example.mongolearn;

import com.example.mongolearn.service.impl.AggregateImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: AggregationTests
 * @Description: todo
 * @Author: wanghuaiyu
 * @Date: 2023/11/28
 */
@SpringBootTest
public class AggregationTests {
    @Autowired
    AggregateImpl aggregate;
    @Test
    void countPeopleGroupByAgeTest(){
        List<Map> maps = aggregate.countPeopleGroupByAge();
        for (Map map: maps){
            System.out.println(map.get("_id")+":"+map.get("people_count"));
        }
    }
}
