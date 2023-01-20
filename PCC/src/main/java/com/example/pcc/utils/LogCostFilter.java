package com.example.pcc.utils;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.pcc.common.Result;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @作者 钟先崟
 * @时间 2023-01-12 19:52
 * @功能 过滤器判断token
 */
@Component
//@WebFilter(urlPatterns = "/*", filterName = "logFilter")
public class LogCostFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        JwtUitls jwtUitls = new JwtUitls();
        Result result = new Result();
        String url = ((HttpServletRequest) servletRequest).getRequestURI();
        if (url != null)
        {
            //登录请求直接放行
            if ("/login".equals(url) || "/register".equals(url) || "/admin/login".equals(url))
            {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } else
            {
                //其他请求验证token
                String token = ((HttpServletRequest) servletRequest).getHeader("token");
                if (StringUtils.isNotBlank(token))
                {
                    //token验证结果
                    int verify = jwtUitls.verify(token);
                    if (verify != 1)
                    {
                        //验证失败
                        if (verify == 2)
                        {
                            //token过期
                            result.setCode(Result.CODE_ERROR);
                            result.setMsg("token已过期");
                            result.setData("");
                        } else if (verify == 0)
                        {
                            //token错误
                            result.setCode(Result.CODE_ERROR);
                            result.setMsg("token验证失败");
                            result.setData("");
                        }
                    } else if (verify == 1)
                    {
                        //验证成功，放行
                        filterChain.doFilter(servletRequest, servletResponse);
                        return;
                    }
                } else
                {
                    //token为空的返回
                    result.setCode(Result.CODE_ERROR);
                    result.setMsg("未携带token信息");
                    result.setData("");
                }
            }
            JSONObject jsonObject = new JSONObject(result);
            servletResponse.setContentType("application/json");
            //设置响应的编码
            servletResponse.setCharacterEncoding("utf-8");
            //响应
            PrintWriter pw = servletResponse.getWriter();
            pw.write(jsonObject.toString());
            pw.flush();
            pw.close();
        }
    }

    @Override
    public void destroy()
    {

    }


}
