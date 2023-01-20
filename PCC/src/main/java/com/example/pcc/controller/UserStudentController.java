package com.example.pcc.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.pcc.common.Result;

import com.example.pcc.entity.*;

import com.example.pcc.mapper.*;
import com.example.pcc.utils.JwtUitls;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @作者 钟先崟
 * @时间 2023-01-12 14:29
 * @功能
 */
@RestController
@RequestMapping("")
public class UserStudentController
{
    //    private static final String url = "";
    private static final String url = "https://www.zxypro.online:888/";
    @Resource
    public UserStudentMapper userStudentMapper;
    @Resource
    public UserStudentIDMapper userStudentIDMapper;
    @Resource
    public UserStudentCourseMapper userStudentCourseMapper;
    @Resource
    public UserStudentSkillMapper userStudentSkillMapper;
    @Resource
    public UserStudentSkillHelpMapper userStudentSkillHelpMapper;

    @PostMapping("/register")
    public Result<?> register(@RequestBody UserStudent userStudent)
    {
        UserStudent user1 = userStudentMapper.selectOne(Wrappers.<UserStudent>lambdaQuery().eq(UserStudent::getUsername, userStudent.getUsername()));
        if (user1 != null)
            return Result.error("账号重复", "");
        userStudentMapper.insert(userStudent);
        return Result.success("注册成功", "");
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody UserStudent userStudent)
    {
        UserStudent user = userStudentMapper.selectById(userStudent.getUsername());
        if (user == null)
            return Result.error("账号不存在", "");
        if (!user.getPassword().equals(userStudent.getPassword()))
            return Result.error("密码错误", "");
        JSONObject data = new JSONObject();
        data.set("token", new JwtUitls().createToken(userStudent.getUsername()));
        data.set("user", user);
        return Result.success("密码正确", data);
    }

    @PostMapping("/upload_person_base")
    public Result<?> UploadPersonalInformation(@RequestBody UserStudent userStudent)
    {
        UserStudent user = userStudentMapper.selectById(userStudent.getUsername());
        if (user == null)
            return Result.error("账号不存在", "");
        userStudentMapper.updateById(userStudent);
        return Result.success("上传成功", "");
    }


    @PostMapping("/newpassword")
    public Result<?> changePassWord(@RequestBody UserStudent userStudent)
    {
        userStudentMapper.updateById(userStudent);
        return Result.success("修改成功", "");
    }

    @PostMapping("/inform/upload/gr_rz/post")
    public Result<?> realNameAuthentication(@RequestBody UserStudentID userStudentID)
    {
        UserStudentID userStudentID1 = userStudentIDMapper.selectById(userStudentID.getUsername());
        if (userStudentID1 != null)
            return Result.error("请勿重复实名认证", "");
        userStudentIDMapper.insert(userStudentID);
        return Result.success("实名认证成功", "");
    }

    @GetMapping("/inform/upload/gr_rz/get")
    public Result<?> updateRealNameAuthenticationData(@RequestParam("username") String username)
    {
        UserStudentID userStudentID = userStudentIDMapper.selectById(username);
        if (userStudentID == null)
            return Result.error("更新失败", "不存在该文件");
        return Result.success("更新成功", userStudentID);
    }

    @PostMapping("/inform/upload/jn_rz/post")
    public Result<?> skillAuthentication(@RequestBody UserStudentSkill userStudentSkill)
    {
        UserStudentSkill userStudentSkill1 = userStudentSkillMapper.selectById(userStudentSkill.getCreatetime());
        if (userStudentSkill1 != null)
            return Result.error("请勿重复提交", "");
        userStudentSkillMapper.insert(userStudentSkill);
        return Result.success("提交成功", "");
    }

    @PostMapping("/inform/del/jn_rz/post")
    public Result<?> delSkillAuthentication(@RequestBody UserStudentSkill userStudentSkill)
    {
        UserStudentSkill userStudentSkill1 = userStudentSkillMapper.selectById(userStudentSkill.getCreatetime());
        if (userStudentSkill1 == null)
            return Result.error("删除失败", "文件不存在");
        userStudentSkillMapper.deleteById(userStudentSkill.getCreatetime());
        return Result.success("删除成功", "");
    }

    @GetMapping("/inform/upload/jn_rz/get")
    public Result<?> getSkillProve(@RequestParam("username") String username)
    {
        return Result.success("获取成功", userStudentSkillMapper
                .selectList(Wrappers.<UserStudentSkill>lambdaQuery()
                        .eq(UserStudentSkill::getUsername, username)));
    }

    @GetMapping("/inform/others_get/jn_rz")
    public Result<?> getOtherSkillAuthenticationData(@RequestParam("your_createtime") String your_createtime)
    {
        UserStudentSkill user = userStudentSkillMapper.selectById(your_createtime);
        if (user == null)
            return Result.error("获取失败", "文件不存在");
        return Result.success("获取成功", user);
    }

    @PostMapping("/inform/others_help/jn_rz")
    public Result<?> UploadAssistedAuthenticationData(@RequestBody UserStudentSkillHelp user)
    {
        UserStudentSkillHelp user1 = userStudentSkillHelpMapper.selectById(user.getHelptime());
        if (user1 != null)
            return Result.error("协助失败", "请勿重复协助");
        userStudentSkillHelpMapper.insert(user);
        return Result.success("协助成功", "");
    }

    @PostMapping("/inform/count/jn_rz")
    public Result<?> UploadAssistedTotalPointsData(@RequestBody UserStudentSkill user)
    {
        UserStudentSkill user1 = userStudentSkillMapper.selectById(user.getCreatetime());
        if (user1 == null)
            return Result.error("上传失败", "该用户不存在");
        user1.setAvepoint1(user.getAvepoint1());
        user1.setAvepoint2(user.getAvepoint2());
        user1.setAvepoint3(user.getAvepoint3());
        user1.setAvepoint4(user.getAvepoint4());
        user1.setAvepoint5(user.getAvepoint5());
        user1.setCount(user.getCount());
        userStudentSkillMapper.updateById(user1);
        return Result.success("上传成功", "");
    }


    @PostMapping("/course/post")
    public Result<?> collectCourse(@RequestBody UserStudentCourse userStudentCourse)
    {
        UserStudentCourse userStudentCourse1 = userStudentCourseMapper
                .selectOne(Wrappers.<UserStudentCourse>lambdaQuery()
                        .eq(UserStudentCourse::getCoursename, userStudentCourse.getCoursename())
                        .eq(UserStudentCourse::getUsername, userStudentCourse.getUsername()));
        if (userStudentCourse1 == null)
        {
            userStudentCourseMapper.insert(userStudentCourse);
            return Result.success("收藏成功", "");
        } else
        {
//            userStudentCourseMapper.deleteById(userStudentCourse.getUsername());
            return Result.error("请勿重复收藏", "");
        }
    }

    @PostMapping("/nocourse/post")
    public Result<?> nocollectCourse(@RequestBody UserStudentCourse userStudentCourse)
    {
        UserStudentCourse userStudentCourse1 = userStudentCourseMapper
                .selectOne(Wrappers.<UserStudentCourse>lambdaQuery()
                        .eq(UserStudentCourse::getCoursename, userStudentCourse.getCoursename())
                        .eq(UserStudentCourse::getUsername, userStudentCourse.getUsername()));
        if (userStudentCourse1 == null)
        {
            return Result.error("取消收藏失败", "不存在该收藏");
        } else
        {
            userStudentCourseMapper.deleteById(userStudentCourse.getId());
            return Result.success("取消收藏成功", "");
        }
    }

    @GetMapping("/course/get")
    public Result<?> getCollectCourse(@RequestParam("username") String username)
    {
        return Result.success("获取成功", userStudentCourseMapper
                .selectList(Wrappers.<UserStudentCourse>lambdaQuery()
                        .eq(UserStudentCourse::getUsername, username)));
    }

    @PostMapping("/file1")
    public Result<?> PostFile(@RequestParam("file") MultipartFile file)
    {
        if (!file.isEmpty())
        {
            String fileName = null;
            OutputStream out = null;
            //获取文件字节数组
            try
            {
                byte[] bytes = file.getBytes();
                fileName = System.currentTimeMillis() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

                //文件存储路径(/fileupload1/ 这样会在根目录下创建问价夹)
                File pfile = new File("/app/outfile", fileName);
//                File pfile = new File("C:/Users/钟先崟/Desktop", fileName);
                //写入指定文件夹
                out = new FileOutputStream(pfile);
                out.write(bytes);
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
            finally
            {
                if (out != null)
                {
                    try
                    {
                        out.close();
                    }
                    catch (IOException e)
                    {
                        throw new RuntimeException(e);
                    }
                }
            }
            Map<String, String> fileUrl = new HashMap<>();
            fileUrl.put("fileUrl", url + fileName);
            return Result.success("上传成功", fileUrl);
        }
        return Result.error("上传失败", "文件为空");
    }


    @GetMapping("/get")
    public Result<List<UserStudent>> get()
    {
        return Result.success("查询成功", userStudentMapper.selectList(null));
    }
}
