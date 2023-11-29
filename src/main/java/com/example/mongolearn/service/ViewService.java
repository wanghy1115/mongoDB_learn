package com.example.mongolearn.service;

/**
 * @ClassName: ViewService
 * @Description: todo
 * @Author: wanghuaiyu
 * @Date: 2023/11/28
 */

/**
 * 视图操作
 */
public interface ViewService {
    /**
     * 创建视图
     */
    String createView(String viewName);
    /**
     * 删除视图
     */
    String deleteView(String viewName);
}
