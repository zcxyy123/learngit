package com.example.pcc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @作者 钟先崟
 * @时间 2023-01-15 20:54
 * @功能
 */
@TableName("userstudent_course")
@Data
public class UserStudentCourse
{
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;
    private String coursename;
    private String courseurl;
    private String mainphoto;
    private String introduce;
}
