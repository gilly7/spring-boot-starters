package org.shield.mybatis.form;

import com.github.pagehelper.IPage;

/**
 * 分页查询模型
 *
 * @author zacksleo@gmail.com
 */
public class PageableQuery implements IPage {

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 每页数目
     */
    private Integer pageSize = 20;

    /**
     * 排序
     */
    private String orderBy = "id desc";

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
