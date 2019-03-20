package com.example.phoebe.youtiao.api.vo;

import lombok.Data;
import lombok.ToString;

/**
 * Created By hugh, 2019/03/04
 **/
@Data
@ToString(callSuper = true)
public class BasePageVo {
    // 当前页

    private Integer pageNum;
    // 分页大小
    private Integer pageSize;

    public int getOffset() {
        return (pageNum - 1) * pageSize;
    }

    public int getLimit() {
        return pageSize;
    }
}