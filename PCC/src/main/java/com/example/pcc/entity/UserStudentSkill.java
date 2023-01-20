package com.example.pcc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @作者 钟先崟
 * @时间 2023-01-16 18:54
 * @功能
 */
@TableName("userstudent_skill")
@Data
public class UserStudentSkill
{
    @TableId(type = IdType.AUTO)
    private String createtime;

    private String username;
    private String professional;
    private String headprotrait;
    private String name;
    private String introduce;
    private String url;

    private String pass;
    private String avepoint1;
    private String avepoint2;
    private String avepoint3;
    private String avepoint4;
    private String avepoint5;
    private Integer count;
    private String allpoint;
}
