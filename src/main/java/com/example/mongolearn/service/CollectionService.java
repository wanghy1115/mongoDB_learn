package com.example.mongolearn.service;

import java.util.Set;

/**
 * @ClassName: CollectionService
 * @Description: 集合操作
 * @Author: wanghuaiyu
 * @Date: 2023/11/27
 */
public interface CollectionService {
    /**
     * 创建集合
     * @return
     */
    String createCollection(String name);
    /**
     * 创建限制大小的集合，size限制大小单位字节，max限制条数
     * @return
     */
    String createCollectionFixedSize(String name, Long size, Integer max);
    /**
     * 创建带验证的集合
     */
    String createCollectionValidation(String name, Integer minAge);
    /**
     * 查询集合名称
     */
    Set<String> getCollections();
}
