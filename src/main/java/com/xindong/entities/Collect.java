package com.xindong.entities;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@TableName(value = "collect")
public class Collect implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private String id;

    private Integer userId;

    private Byte type;

    private Integer songId;

    private Integer songListId;

    private Date createTime;


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}