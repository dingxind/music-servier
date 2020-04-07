package com.example.demo.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Rank implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long songListId;

    private Long consumerId;

    private Integer score;
}
