package com.xindong.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Getter
@Setter
public class SongList {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private String pic;

    private String style;

    private String introduction;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
