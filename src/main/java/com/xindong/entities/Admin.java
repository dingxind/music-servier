package com.xindong.entities;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class Admin  implements Serializable  {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}