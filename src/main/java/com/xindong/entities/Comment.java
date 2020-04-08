package com.xindong.entities;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

@Data
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
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