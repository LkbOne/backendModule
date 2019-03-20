package com.example.phoebe.youtiao.controller.arg;

import lombok.Data;

/**
 * @author hugh
 */
@Data
public class BasePageArg {
    private Integer pageNum;
    private Integer pageSize;

    public int getOffset() {
        return (pageNum - 1) * pageSize;
    }

    public int getLimit() {
        return pageSize;
    }
}
