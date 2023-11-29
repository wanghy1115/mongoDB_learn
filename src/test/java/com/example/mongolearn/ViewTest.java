package com.example.mongolearn;

import com.example.mongolearn.service.ViewService;
import com.example.mongolearn.service.impl.ViewImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName: ViewTest
 * @Description: todo
 * @Author: wanghuaiyu
 * @Date: 2023/11/28
 */
@SpringBootTest
public class ViewTest {
    @Autowired
    ViewImpl viewService;

    /**
     * 创建视图
     */
    @Test
    public void createView(){
        String testview = viewService.createView("testview");
        System.out.println(testview);
    }

    /**
     * 删除视图
     */
    @Test
    public void deleteView(){
        String testview = viewService.deleteView("testview");
        System.out.println(testview);
    }
}
