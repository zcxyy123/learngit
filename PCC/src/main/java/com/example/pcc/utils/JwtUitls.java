package com.example.pcc.utils;
import com.example.pcc.entity.UserStudent;
import com.example.pcc.mapper.UserStudentMapper;
import io.jsonwebtoken.*;

import jdk.nashorn.internal.runtime.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerResponse;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @作者 钟先崟
 * @时间 2023-01-12 19:02
 * @功能 token生成与验证
 */
@Component
public class JwtUitls {

    @Autowired
    static UserStudentMapper userStudentMapper;

    /**
     * 过期时间5分钟
     */
    private static final long EXPIRE_TIME=1000*5*60*1000;
    /**
     * 加密密钥
     */
    private static final String KEY = "zhongxianyin";

    /**
     * 生成token
     * @param userName    用户id
     * @param userName  用户名
     * @return
     */
    public String createToken(String userName){
        Map<String,Object> header = new HashMap();
        header.put("typ","JWT");
        header.put("alg","HS256");
        //setID:用户ID
        //setExpiration:token过期时间  当前时间+有效时间
        //setSubject:用户名
        //setIssuedAt:token创建时间
        //signWith:加密方式
        JwtBuilder builder = Jwts.builder().setHeader(header)
                .setId(userName)
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_TIME))
                .setSubject(userName)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,KEY);
        return builder.compact();
    }

    /**
     * 验证token是否有效
     * @param token  请求头中携带的token
     * @return  token验证结果  2-token过期；1-token认证通过；0-token认证失败
     */
    public int verify(String token){
        Claims claims = null;
        try {
            //token过期后，会抛出ExpiredJwtException 异常，通过这个来判定token过期，
            claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            return 2;
        }
        //从token中获取用户id，查询该Id的用户是否存在，存在则token验证通过
        String id = claims.getId();
        if(id.equals("admin"))
            return 1;
        UserStudent userStudent = userStudentMapper.selectById(id);
        if(userStudent != null){
            return 1;
        }else{
            return 0;
        }
    }

}

