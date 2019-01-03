package io.wanyxkhalil.abstinence.wubjpa.utils;

import io.wanyxkhalil.abstinence.webdomain.request.PageReq;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * Web请求工具类
 */
public class WebRequestSortJpaUtils {

    /**
     * 倒序获取分页信息
     *
     * @param req      分页请求
     * @param property 排序字段
     * @return 分页信息
     */
    public static PageRequest getPageInfoDesc(PageReq req, String property) {
        return getPageInfo(req, Sort.Direction.DESC, property);
    }

    /**
     * 正序获取分页信息
     *
     * @param req      分页请求
     * @param property 排序字段
     * @return 分页信息
     */
    public static PageRequest getPageInfoAsc(PageReq req, String property) {
        return getPageInfo(req, Sort.Direction.ASC, property);
    }

    /**
     * 获取分页信息
     *
     * @param req        分页请求
     * @param dir        排序定义
     * @param properties 排序字段组
     * @return 分页信息
     */
    public static PageRequest getPageInfo(PageReq req, Sort.Direction dir, String... properties) {
        return PageRequest.of(req.getPageNum() - 1, req.getPageSize(), dir, properties);
    }
}
