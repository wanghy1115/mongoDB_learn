package com.example.mongolearn.service.impl;

import com.example.mongolearn.pojo.po.People;
import com.example.mongolearn.service.DocumentService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.TextScore;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: DocumentImpl
 * @Description: 文档操作
 * @Author: wanghuaiyu
 * @Date: 2023/11/28
 */
@Service
@Slf4j
public class DocumentImpl implements DocumentService {
    //设置集合名称
    private static final String COLLECTION_NAME = "test";

    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 插入数据
     */
    @Override
    public People createDocument(People people) {
        mongoTemplate.insert(people, COLLECTION_NAME);
        log.info("添加对象：" + people.toString());
        return people;
    }

    /**
     * 插入多条数据
     */
    @Override
    public List<People> createDocumentByList(List<People> peoples) {
        mongoTemplate.insert(peoples, COLLECTION_NAME);
        for (People people: peoples){
            log.info("添加对象：" + people.toString());
        }
        return peoples;
    }

    /**
     * 修改一条文档数据不存在时添加
     *
     * @param
     * @return
     */
    @Override
    public Long upsertDocument() {
        Criteria criteria = Criteria.where("age").gt(40);
        Query query = new Query(criteria);
        Update update = new Update().set("age", 16).set("name", "小雅");
        UpdateResult upsert = mongoTemplate.upsert(query, update, People.class, COLLECTION_NAME);
        return  upsert.getMatchedCount();
    }

    /**
     * 修改一条文档数据
     *
     * @param
     * @return
     */
    @Override
    public Long updateDocument() {
        Criteria criteria = Criteria.where("type").is("帅气");
        Query query = new Query(criteria);
        Update update = new Update().set("age", 25).set("name", "小王子");
        UpdateResult upsert = mongoTemplate.updateFirst(query, update, People.class, COLLECTION_NAME);
        return  upsert.getMatchedCount();
    }

    @Override
    public People deleteDocument(String id) {
        Criteria criteria = Criteria.where("_id").is(new ObjectId(id));
        Query query = new Query(criteria);
        People res = mongoTemplate.findById(query, People.class, COLLECTION_NAME);
//        mongoTemplate.findAndRemove(query, People.class, COLLECTION_NAME);
        return res;
    }

    @Override
    public DeleteResult removeDocumentAll(Integer age){
        Criteria criteria = Criteria.where("age").is(age);
        Query query = new Query(criteria);
        DeleteResult remove = mongoTemplate.remove(query, COLLECTION_NAME);
        return remove;
    }

    /**
     * 查询全部
     * @return
     */
    @Override
    public List<People> findAll(){
        List<People> all = mongoTemplate.findAll(People.class, COLLECTION_NAME);
        return all;
    }

    /**
     * 查找第一条
     */
    @Override
    public People findOne(String name){
        Criteria criteria = Criteria.where("name").is(name);
        Query query = new Query(criteria);
        People one = mongoTemplate.findOne(query, People.class, COLLECTION_NAME);
        return one;
    }

    /**
     * 查找并排序Sort用法
     * 还可以拼接skip（跳过）和limit（限制）
     */
    @Override
    public List<People> findAndSort(){
        Criteria criteria = Criteria.where("age").gt(18);
        Query query = new Query(criteria).with(Sort.by("age").descending())
                .skip(0L).limit(4);
        List<People> res = mongoTemplate.find(query, People.class, COLLECTION_NAME);
        return res;
    }

    @Override
    public List<People> findByExistsField(String fieldName){
        Criteria exists = Criteria.where(fieldName).exists(true);
        Query query = new Query(exists);
        List<People> peopleList = mongoTemplate.find(query, People.class, COLLECTION_NAME);
        return peopleList;
    }

    public List<People> findByMoreItems(String name, Integer age){
        Criteria criteriaName = Criteria.where("name").is(name);
        Criteria criteriaAge = Criteria.where("age").is(age);
        Criteria criteria = new Criteria().andOperator(criteriaAge, criteriaName);
        List<People> peopleList = mongoTemplate.find(new Query(criteria), People.class, COLLECTION_NAME);
        return peopleList;
    }

}
