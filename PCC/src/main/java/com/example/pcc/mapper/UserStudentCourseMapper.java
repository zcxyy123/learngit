package com.example.pcc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pcc.entity.UserStudentCourse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @作者 钟先崟
 * @时间 2023-01-15 20:57
 * @功能
 */
@Mapper
public interface UserStudentCourseMapper extends BaseMapper<UserStudentCourse>
{
}
