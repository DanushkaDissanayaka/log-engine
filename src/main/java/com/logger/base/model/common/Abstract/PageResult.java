package com.logger.base.model.common.Abstract;

import java.util.List;

public interface PageResult <T>{
    List<T> getData();

    void setData(List<T> data);

    int getPages();

    void setPages(int pages);
}
