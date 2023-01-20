package com.example.pcc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @作者 钟先崟
 * @时间 2023-01-15 17:28
 * @功能
 */
@TableName("useradmin")
@Data
public class UserAdmin
{
    @TableId(type = IdType.AUTO)
    private String username;
    private String password;
}
