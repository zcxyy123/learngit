package com.example.pcc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @作者 钟先崟
 * @时间 2023-01-16 21:14
 * @功能
 */
@TableName("userstudent_skill_help")
@Data
public class UserStudentSkillHelp
{

    private String createtime;

    private String helpusername;
    @TableId(type = IdType.AUTO)
    private String helptime;
    private String point1;
    private String point2;
    private String point3;
    private String point4;
    private String point5;
}
