package com.xindong.entities;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Consumer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @NotBlank(message = "账号不能为空")
    private String username;

//    @Max(value = 5,message = "密码长度最少不能低于5位")
//    @Min(value = 2,message = "密码长度最多不能高于3位")

    @Length(min=3, max=10,message = "密码长度必须在3~10位之间" )
    private String password;

    private Byte sex;

    private String phoneNum;

    @Email
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birth;

    private String introduction;

    private String location;

    private String avator;

    private Date createTime;

    private Date updateTime;

    public String toString() {
        return JSON.toJSONString(this);
    }
}
