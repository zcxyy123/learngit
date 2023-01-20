package com.example.pcc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @作者 钟先崟
 * @时间 2023-01-15 20:27
 * @功能
 */
@TableName("userstudent_id")
@Data
public class UserStudentID
{
    @TableId(type = IdType.AUTO)
    private String username;

    private String name;
    private String id;
    private String url;
    private String pass;
}
