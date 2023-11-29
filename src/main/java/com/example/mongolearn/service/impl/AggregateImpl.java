package com.example.mongolearn.service.impl;

import com.example.mongolearn.service.AggregateService;
import com.mongodb.internal.operation.AggregateOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: AggregateImp
 * @Description: 聚合操作
 * @Author: wanghuaiyu
 * @Date: 2023/11/28
 */
@Slf4j
@Service
public class AggregateImpl implements AggregateService {
    private static final String COLLECTION_NAME = "test";
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Map> countPeopleGroupByAge() {
        GroupOperation group = Aggregation.group("age").count()
                .as("people_count");
        Aggregation aggregation = Aggregation.newAggregation(group);
        AggregationResults<Map> aggregate = mongoTemplate.aggregate(aggregation, COLLECTION_NAME, Map.class);
        List<Map> mappedResults = aggregate.getMappedResults();
        return mappedResults;
    }
}
