package com.xindong.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

@Data
public class ListSong implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private Integer songId;

    private Integer songListId;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
