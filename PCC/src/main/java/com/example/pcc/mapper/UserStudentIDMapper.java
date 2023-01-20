package com.example.pcc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pcc.entity.UserStudentID;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @作者 钟先崟
 * @时间 2023-01-15 20:30
 * @功能
 */
@Mapper
@Repository
public interface UserStudentIDMapper extends BaseMapper<UserStudentID>
{
}
