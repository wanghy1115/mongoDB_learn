package com.example.mongolearn.pojo.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

/**
 * @ClassName: People
 * @Description: todo
 * @Author: wanghuaiyu
 * @Date: 2023/11/27
 */
@Data
@ToString
@Accessors(chain = true )//可以使用链式方式创建对象
public class People {
    @MongoId
    private String id;
    private String name;
    private String  type;
    private Integer sex;
    private Integer age;
    @JsonFormat( pattern ="yyyy-MM-dd", timezone ="GMT+8")
    private Date birthday;
}
