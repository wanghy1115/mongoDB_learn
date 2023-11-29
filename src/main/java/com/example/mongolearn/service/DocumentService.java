package com.example.mongolearn.service;

import com.example.mongolearn.pojo.po.People;
import com.mongodb.client.result.DeleteResult;

import java.util.List;

/**
 * @ClassName: DocumentService
 * @Description: 文档操作
 * @Author: wanghuaiyu
 * @Date: 2023/11/28
 */
public interface DocumentService {
    /**
     * 添加一条文档数据
     * @param people
     * @return
     */
    People createDocument(People people);

    /**
     * 修改文档数据,不存在时添加
     * @param
     * @return
     */
    Long upsertDocument();

    /**
     * 修改文档数据,不存在时添加
     * @param
     * @return
     */
    Long updateDocument();



    /**
     * 根据列表插入数据
     * @param peoples
     * @return
     */
    List<People> createDocumentByList(List<People> peoples);

    /**
     * 删除数据
     * @param
     * @return
     */
    DeleteResult removeDocumentAll(Integer age);


    /**
     * 删除文档数据(仅删除一条）
     * @param
     * @return
     */
    People deleteDocument(String id);

    /**
     * 查询全部
     */
    List<People> findAll();

    /**
     * 查找一条
     */
    People findOne(String name);

    /**
     * 查找并排序
     */
    List<People> findAndSort();

    /**
     * 根据存在的字段查询
     * @param FieldName
     * @return
     */
    List<People> findByExistsField(String FieldName);

    /**
     * 多条件查询
     * @param name
     * @param age
     * @return
     */
    List<People> findByMoreItems(String name, Integer age);
}
