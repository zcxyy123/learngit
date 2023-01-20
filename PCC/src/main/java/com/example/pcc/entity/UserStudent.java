package com.example.pcc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @作者 钟先崟
 * @时间 2023-01-12 14:31
 * @功能
 */
@TableName("userstudent")
@Data
public class UserStudent
{
    @TableId(type = IdType.AUTO)
    private String username;
    private String password;
    private String nickname;
    private String sex;
    private Integer age;
    private String nativeplace;
    private String headportrait;
    private String personalprofile;
    private String occuption;
    private String birthday;
    private String email;

    public UserStudent(String username, String password, String nickname, String sex, Integer age, String nativeplace, String headportrait, String personalprofile, String occuption, String birthday, String email)
    {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.sex = sex;
        this.age = age;
        this.nativeplace = nativeplace;
        this.headportrait = headportrait;
        this.personalprofile = personalprofile;
        this.occuption = occuption;
        this.birthday = birthday;
        this.email = email;
    }
}
