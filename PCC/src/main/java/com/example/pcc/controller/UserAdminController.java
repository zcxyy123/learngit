package com.example.pcc.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.pcc.common.Result;
import com.example.pcc.entity.UserAdmin;
import com.example.pcc.entity.UserStudentID;
import com.example.pcc.entity.UserStudentSkill;
import com.example.pcc.mapper.UserAdminMapper;
import com.example.pcc.mapper.UserStudentIDMapper;
import com.example.pcc.mapper.UserStudentSkillMapper;
import com.example.pcc.utils.JwtUitls;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @作者 钟先崟
 * @时间 2023-01-15 17:28
 * @功能
 */
@RestController
@RequestMapping("/admin")
public class UserAdminController
{
    @Resource
    public UserAdminMapper userAdminMapper;
    @Resource
    public UserStudentIDMapper userStudentIDMapper;
    @Resource
    public UserStudentSkillMapper userStudentSkillMapper;

    @PostMapping("/login")
    public Result<?> login(@RequestBody UserAdmin userAdmin)
    {
        UserAdmin user = userAdminMapper.selectById(userAdmin.getUsername());
        if (user == null)
            return Result.error("账号不存在", "");
        if (!user.getPassword().equals(userAdmin.getPassword()))
            return Result.error("密码错误", "");
        JSONObject data = new JSONObject();
        data.set("token", new JwtUitls().createToken(userAdmin.getUsername()));
        data.set("user", user);
        return Result.success("密码正确", data);
    }

    @GetMapping("/new")
    public Result<?> updateUnCheckRealNameData()
    {
        return Result.success("获取成功", userStudentIDMapper
                .selectList(Wrappers.<UserStudentID>lambdaQuery()
                        .eq(UserStudentID::getPass, "0")));
    }

    @PostMapping("/pass")
    public Result<?> passRealName(@RequestBody UserStudentID userStudentID)
    {
        UserStudentID user = userStudentIDMapper.selectById(userStudentID.getUsername());
        if (user == null)
            return Result.error("通过失败", "实名认证文件不存在");
        user.setPass("1");
        userStudentIDMapper.updateById(user);
        return Result.success("通过成功", "");
    }

    @PostMapping("/nopass")
    public Result<?> unPassRealName(@RequestBody UserStudentID userStudentID)
    {
        UserStudentID user = userStudentIDMapper.selectById(userStudentID.getUsername());
        if (user == null)
            return Result.error("不通过失败", "实名认证文件不存在");
        user.setPass("2");
        userStudentIDMapper.updateById(user);
        return Result.success("不通过成功", "");
    }

    @GetMapping("/new/jn_rz")
    public Result<?> updateUnCheckSkillData()
    {
        return Result.success("获取成功", userStudentSkillMapper
                .selectList(Wrappers.<UserStudentSkill>lambdaQuery()
                        .eq(UserStudentSkill::getPass, "0")));
    }

    @PostMapping("/jn/pass")
    public Result<?> passSkillProve(@RequestBody UserStudentSkill user)
    {
        UserStudentSkill user1 = userStudentSkillMapper.selectById(user.getCreatetime());
        if (user1 == null)
            return Result.error("通过失败", "该文件不存在");
        user1.setPass("1");
        userStudentSkillMapper.updateById(user1);
        return Result.success("通过成功", "");
    }

    @PostMapping("/jn/nopass")
    public Result<?> unpassSkillProve(@RequestBody UserStudentSkill user)
    {
        UserStudentSkill user1 = userStudentSkillMapper.selectById(user.getCreatetime());
        if (user1 == null)
            return Result.error("不通过失败", "该文件不存在");
        user1.setPass("2");
        userStudentSkillMapper.updateById(user1);
        return Result.success("不通过成功", "");
    }

}
