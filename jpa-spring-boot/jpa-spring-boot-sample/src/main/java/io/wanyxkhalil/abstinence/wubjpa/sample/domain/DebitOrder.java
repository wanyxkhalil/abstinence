package io.wanyxkhalil.abstinence.wubjpa.sample.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class DebitOrder {

    @Id
    private Integer id;

    private String userName;
    private LocalDateTime basicTime;
}
