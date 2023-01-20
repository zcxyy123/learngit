package com.example.pcc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pcc.entity.UserStudent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @作者 钟先崟
 * @时间 2022-12-29 14:22
 * @功能
 */
@Mapper
@Repository
public interface UserStudentMapper extends BaseMapper<UserStudent>
{
}
