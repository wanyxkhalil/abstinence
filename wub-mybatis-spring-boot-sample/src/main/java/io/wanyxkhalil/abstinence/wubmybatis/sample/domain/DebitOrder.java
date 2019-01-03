package io.wanyxkhalil.abstinence.wubmybatis.sample.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DebitOrder {

    private Integer id;
    private String userName;
    private LocalDateTime basicTime;
}
