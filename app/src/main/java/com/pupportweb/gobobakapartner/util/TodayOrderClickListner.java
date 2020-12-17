package com.pupportweb.gobobakapartner.util;

public interface TodayOrderClickListner {
    void ClickGetBoys(int position,String viewType);

    void assignClickBoys(int position,String viewType);

    void forOrderTodayClick(int position);
    void forOrderNextClick(int position);
}
