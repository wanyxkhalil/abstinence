package io.wanyxkhalil.abstinence.wubjpa.web.response;

import io.wanyxkhalil.abstinence.common.domain.response.PageRespData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

@Getter
@Setter
@ToString(callSuper = true)
public class PageRespJpaData extends PageRespData {

    public <T> void buildPageInfo(Page<T> page) {

        this.setTotal(page.getTotalElements());
        this.setPages(page.getTotalPages());
        this.setPageNum(page.getNumber() + 1);
        this.setPageSize(page.getSize());

    }
}
