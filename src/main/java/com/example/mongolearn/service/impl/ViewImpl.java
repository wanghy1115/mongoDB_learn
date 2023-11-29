package com.example.mongolearn.service.impl;

import com.example.mongolearn.service.ViewService;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ViewService
 * @Description: todo
 * @Author: wanghuaiyu
 * @Date: 2023/11/28
 */
@Service
public class ViewImpl implements ViewService {
    @Autowired
    MongoTemplate mongoTemplate;
    /**
     * 创建视图
     */
    public String createView(String viewName){
        String collectionName = "testCollection";
        //定义管道，用来设置筛选条件
        List<Bson> pipline= new ArrayList<>();
        pipline.add(Document.parse("{\"$match\":{\"sex\":\"0\"}}"));
        mongoTemplate.getDb().createView(viewName, collectionName, pipline);
        return mongoTemplate.collectionExists(viewName)?"创建成功":"创建失败";
    }

    /**
     * 删除视图
     */
    public String deleteView(String viewName){
        if(mongoTemplate.collectionExists(viewName)){
            mongoTemplate.getDb().getCollection(viewName).drop();
            return "删除成功";
        }
        return "删除成功（视图不存在）";
    }
}
