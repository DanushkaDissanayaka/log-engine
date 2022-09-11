package com.logger.base.model.common.Concrete;
import com.logger.base.model.common.Abstract.PageResult;

import java.util.List;

public class PageResultImpl<T> implements PageResult<T> {

    private List<T> data;
    private int pages;

    public PageResultImpl(List<T> data, int pages) {
        this.data = data;
        this.pages = pages;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
