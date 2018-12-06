package io.wanyxkhalil.abstinence.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true)
public class PageRespData extends BaseResp.BaseData {

    private Long total;
    private Integer pages;
    private Integer pageNum;
    private Integer pageSize;
}
