package com.example.pcc.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Key;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T>
{
    public static final String CODE_SUCCESS = "success";
    public static final String CODE_ERROR = "error";
    private String code;
    private String msg;
    private T data;

    public static Result success()
    {
        Result result = new Result<>();
        result.setCode(CODE_SUCCESS);
        return result;
    }

    public static <T> Result<T> success(String msg,T data)
    {
        Result<T> result = new Result<>();
        result.setCode(CODE_SUCCESS);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(String msg,String key,T value)
    {
        Result<T> result = new Result<>();
        result.setCode(CODE_SUCCESS);
        result.setMsg(msg);
        result.setData((T) new HashMap<>().put(key,value));
        return result;
    }

    public static<T> Result<T> error(String msg,T data)
    {
        Result result = new Result<>();
        result.setCode(CODE_ERROR);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }


}

