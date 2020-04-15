package com.xindong.entities;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

@Data
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer songId;

    private Integer songListId;

    private String content;

    private Date createTime;

    private Byte type;

    private Integer up;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}