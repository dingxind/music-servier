package com.xindong.entities;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@TableName("`rank`")
public class Rank implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("song_list_id")
    private Long songListId;

    @TableField("consumer_id")
    private Long consumerId;

    private Integer score;

    public String toString(){
        return JSON.toJSONString(this);
    }
}
