package com.xindong.entities;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Song implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer singerId;

    private String name;

    private String introduction;

    private Date createTime;

    private Date updateTime;

    private String pic;

    private String lyric;

    private String url;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
