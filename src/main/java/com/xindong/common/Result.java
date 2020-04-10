package com.xindong.common;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;

@Data
@ApiModel(value = "全局统一返回结果")
public class Result {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String msg;

    @ApiModelProperty(value = "返回地址")
    private String avator;

    @ApiModelProperty(value = "返回数据")
    private Object data ;

    private Result(){}

    public static Result ok(){
        Result result = new Result();
        result.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMsg(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    public static Result error(){
        Result result = new Result();
        result.setSuccess(ResultCodeEnum.UNKNOWN_REASON.getSuccess());
        result.setCode(ResultCodeEnum.UNKNOWN_REASON.getCode());
        result.setMsg(ResultCodeEnum.UNKNOWN_REASON.getMessage());
        return result;
    }

    public static Result setResult(ResultCodeEnum resultCodeEnum){
        Result result = new Result();
        result.setSuccess(resultCodeEnum.getSuccess());
        result.setCode(resultCodeEnum.getCode());
        result.setMsg(resultCodeEnum.getMessage());
        return result;
    }

    public Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public Result msg(String message){
        this.setMsg(message);
        return this;
    }

    public Result avator(String avator){
        this.setAvator(avator);
        return this;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result data(Object t){
        this.setData(t);
        return this;
    }

    public Result data(String key,Object value){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(key,value);
        this.setData(hashMap);
        return this;
    }
}
