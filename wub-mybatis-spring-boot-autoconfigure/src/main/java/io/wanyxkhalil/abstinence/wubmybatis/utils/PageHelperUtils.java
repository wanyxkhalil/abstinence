package io.wanyxkhalil.abstinence.wubmybatis.utils;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.wanyxkhalil.abstinence.webdomain.request.PageReq;

public class PageHelperUtils {

    public static <T> Page<T> startPage(PageReq req) {
        return PageHelper.startPage(req.getPageNum(), req.getPageSize());
    }

    public static <T> Page<T> startPage(PageReq req, ISelect select) {
        return PageHelper.startPage(req.getPageNum(), req.getPageSize())
                .doSelectPage(select);
    }
}
