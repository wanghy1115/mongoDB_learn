package com.example.mongolearn.service.impl;

import com.example.mongolearn.service.CollectionService;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.validation.Validator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 *@ClassName: CollectionImpl
 *@Description: todo
 *@Author: wanghuaiyu
 *@Date: 2023/11/27
*/
@Service
public class CollectionImpl implements CollectionService {
    @Resource
    private MongoTemplate mongoTemplate;
    /**
     * 创建集合
     * @return
     */
    @Override
    public String createCollection(String name) {
        MongoCollection<Document> collection = mongoTemplate.createCollection(name);
        //判断集合是否存在
        return mongoTemplate.collectionExists(name)?"创建成功":"创建失败";
    }
    /**
     * 创建限制大小的集合，size限制大小单位字节，max限制条数
     * @return
     */
    public String createCollectionFixedSize(String name, Long size, Integer max){
        CollectionOptions collectionOptions = CollectionOptions.empty().capped().size(size).maxDocuments(max);
        mongoTemplate.createCollection(name, collectionOptions);
        return mongoTemplate.collectionExists(name)?"创建成功":"创建失败";
    }
    /**
     * 创建带验证的集合
     * Criteria标准
     * validator验证器
     */
    public String createCollectionValidation(String name, Integer minAge){
        //定义标准
        Criteria ageCriteria = Criteria.where("age").gt(minAge);
        CollectionOptions collectionOptions = CollectionOptions.empty()
                .validator(Validator.criteria(ageCriteria))
                //设置校验级别
                .strictValidation()
                .failOnValidationError();
        mongoTemplate.createCollection(name, collectionOptions);
        return mongoTemplate.collectionExists(name)?"创建成功":"创建失败";
    }
    /**
     * 查询集合名称
     */
    public Set<String> getCollections(){
        Set<String> collectionNames = mongoTemplate.getCollectionNames();
        return collectionNames;
    }
}
