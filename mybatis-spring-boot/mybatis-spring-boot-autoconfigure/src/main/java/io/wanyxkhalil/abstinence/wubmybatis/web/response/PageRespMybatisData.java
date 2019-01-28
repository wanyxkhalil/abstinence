package io.wanyxkhalil.abstinence.wubmybatis.web.response;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import io.wanyxkhalil.abstinence.common.domain.response.PageRespData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
public class PageRespMybatisData extends PageRespData {

    public <T> void buildPageInfo(List<T> it) {
        PageInfo<T> page = new PageInfo<>(it);

        this.setTotal(page.getTotal());
        this.setPages(page.getPages());
        this.setPageNum(page.getPageNum());
        this.setPageSize(page.getSize());
    }

    public <T> void buildPageInfo(Page<T> page) {
        this.setTotal(page.getTotal());
        this.setPages(page.getPages());
        this.setPageNum(page.getPageNum());
        this.setPageSize(page.getPageSize());
    }
}
