package com.example.mongolearn.service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: AggregateService
 * @Description: 文档聚合接口
 * @Author: wanghuaiyu
 * @Date: 2023/11/28
 */
public interface AggregateService {
    List<Map> countPeopleGroupByAge();
}
