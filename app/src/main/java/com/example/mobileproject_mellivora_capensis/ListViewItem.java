package com.example.mobileproject_mellivora_capensis;

import android.graphics.drawable.Drawable;

public class ListViewItem {
    private int iconDrawable ;
    private String titleStr ;
    private String descStr ;
    private String majorStr ;
    private int count;

    public void setIcon(int icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }
    public void setMajor(String major) {
        majorStr = major ;
    }
    public void setCount(int n) {
        count = n ;
    }


    public int getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
    public String getMajor() {
        return this.majorStr ;
    }
    public int getCount() {
        return this.count ;
    }

}
