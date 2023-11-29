package com.example.mongolearn;

import com.example.mongolearn.pojo.po.People;
import com.example.mongolearn.service.impl.DocumentImpl;
import com.mongodb.client.result.DeleteResult;
import jdk.nashorn.internal.ir.CallNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: DocumentTest
 * @Description: todo
 * @Author: wanghuaiyu
 * @Date: 2023/11/28
 */
@SpringBootTest
public class DocumentTest {
    @Autowired
    DocumentImpl documentService;
    /**
     * 插入数据
     */
    @Test
    public void insertDocmentTest(){
        People people = new People().setAge(16).setName("小王").setType("帅气")
                .setSex(1).setBirthday(new Date(2020,10,12));
        documentService.createDocument(people);
    }

    /**
     * 根据列表插入数据
     */
    @Test
    public void insertDocmentByListTest(){
        List<People> peopleList = new ArrayList<>();
        peopleList.add(new People().setAge(16).setName("小何").setType("邋遢")
                .setSex(1).setBirthday(new Date(2020,10,12)));
        peopleList.add(new People().setAge(12).setName("小张").setType("可爱")
                .setSex(0).setBirthday(new Date(2020,10,12)));
        peopleList.add(new People().setAge(19).setName("小杜").setType("坦然")
                .setSex(1).setBirthday(new Date(2020,10,12)));
        peopleList.add(new People().setAge(24).setName("小显").setType("灵气")
                .setSex(0).setBirthday(new Date(2020,10,12)));
        documentService.createDocumentByList(peopleList);
    }

    /**
     * 修改数据，如果数据不存在就插入一条
     */
    @Test
    public void upsertDocumentTest(){
        Long aLong = documentService.upsertDocument();
        System.out.println(aLong);
    }

    /**
     * 修改数据
     */
    @Test
    public void updateDocumentTest(){
        Long aLong = documentService.updateDocument();
        System.out.println(aLong);
    }

    /**
     * 删除所有匹配的数据
     */
    @Test
    public void removeDocumentTest(){
        DeleteResult deleteResult = documentService.removeDocumentAll(16);
        System.out.println(deleteResult.toString());
    }

    /**
     * 删除一条匹配的数据
     * 目前无法根据id删除
     * ObjectId("656548ec0d66a3704404270b")类型不知道怎么包装，匹配不到。
     */
    @Test
    public void deleteDocumentTest(){
        People res = documentService.deleteDocument("656548ec0d66a3704404270b");
        System.out.println(res.toString());
    }

    /**
     * 查询全部
     */
    @Test
    public void findAllTest(){
        List<People> all = documentService.findAll();
        for (People people : all){
            System.out.println(people.toString());
        }
    }

    /**
     * 查找一个
     */
    @Test
    public void findOneTest(){
        People people = documentService.findOne("小杜");
        System.out.println(people.toString());
    }

    /**
     * 查找并排序
     */
    @Test
    public void findAndSortTest(){
        List<People> list = documentService.findAndSort();
        for (People people: list){
            System.out.println(people.toString());
        }
    }

    /**
     * 查找存在某个字段的数据
     */
    @Test
    public void findByExistsFieldTest(){
        List<People> list = documentService.findByExistsField("sex");
        for (People people: list){
            System.out.println(people.toString());
        }
    }

    /**
     *  多条件查询
     */
    @Test
    public void findByMoreItemsTest(){
        List<People> list = documentService.findByMoreItems("小杜", 19);
        for (People people: list){
            System.out.println(people.toString());
        }
    }
}
