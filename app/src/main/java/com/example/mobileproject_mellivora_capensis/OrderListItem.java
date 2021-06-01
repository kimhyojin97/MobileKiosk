package com.example.mobileproject_mellivora_capensis;


public class OrderListItem {

    private String menuStr;
    private String quanStr;
    private String totalStr;

    public void setMenu(String menu) {
        menuStr = menu ;
    }
    public void setQuan(String quan) {
        quanStr = quan ;
    }
    public void setTotal(String total) {
        totalStr = total ;
    }

    public String getMenu() {
        return this.menuStr;
    }
    public String getQuan() {
        return this.quanStr;
    }
    public String getTotal() {
        return this.totalStr;
    }
}