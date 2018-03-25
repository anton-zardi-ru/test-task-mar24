package ru.zardi.tests.web;

import com.google.code.stackexchange.schema.User;

/**
 * Created by Anton Petrov
 * Time: 13:01
 * Date: 2018-03-25.
 */
@SuppressWarnings("unused")
public class QueryModel {
    private final static int DEFAULT_PAGE_NUMBER = 0;
    private final static int DEFAULT_PAGE_SIZE = 30;
    private final static String DEFAULT_SEARCH = "";
    private final static User.QuestionSortOrder DEFAULT_ORDER = User.QuestionSortOrder.MOST_RELEVANT;

    public final static QueryModel DEFAULT_MODEL = new QueryModel();

    private String search = DEFAULT_SEARCH;

    private int pageNumber = DEFAULT_PAGE_NUMBER;

    private int pageSize = DEFAULT_PAGE_SIZE;

    private User.QuestionSortOrder sortOrder = DEFAULT_ORDER;

    public void setSearch(String search) {
        this.search = search;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setSortOrder(User.QuestionSortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSearch() {
        return search;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public User.QuestionSortOrder getSortOrder() {
        return sortOrder;
    }
}
