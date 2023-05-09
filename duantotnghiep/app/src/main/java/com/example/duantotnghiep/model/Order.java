
package com.example.duantotnghiep.model;

import com.google.gson.annotations.SerializedName;

public class Order {
    private Boolean expanded = false;

    @SerializedName("ORDERID")
    public int orderid;
    @SerializedName("USERNAME")
    public String username;
    @SerializedName("VOUCHERDETAIL")
    public String voucherdetail;
    @SerializedName("ADDRESS")
    public String address;
    @SerializedName("PHONENUMBER")
    public String phonenumber;
    @SerializedName("TIME")
    public String tIME;
    @SerializedName("ORDSTATUS")
    public int oRDSTATUS;
    @SerializedName("TOTAL")
    public String tOTAL;
    @SerializedName("VOUCHERCODE")
    public String vOUCHERCODE;

    public Order(int orderid, String username, String address, String phonenumber, String tIME, int oRDSTATUS, String tOTAL) {
        this.orderid = orderid;
        this.username = username;
        this.address = address;
        this.phonenumber = phonenumber;
        this.tIME = tIME;
        this.oRDSTATUS = oRDSTATUS;
        this.tOTAL = tOTAL;
        this.expanded = false;
    }

    public Order() {
    }

    public String getVoucherdetail() {
        return voucherdetail;
    }

    public void setVoucherdetail(String voucherdetail) {
        this.voucherdetail = voucherdetail;
    }

    public Boolean getExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    public String gettOTAL() {
        return tOTAL;
    }

    public void settOTAL(String tOTAL) {
        this.tOTAL = tOTAL;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String gettIME() {
        return tIME;
    }

    public void settIME(String tIME) {
        this.tIME = tIME;
    }

    public int getoRDSTATUS() {
        return oRDSTATUS;
    }

    public void setoRDSTATUS(int oRDSTATUS) {
        this.oRDSTATUS = oRDSTATUS;
    }

    public String getvOUCHERCODE() {
        return vOUCHERCODE;
    }

    public void setvOUCHERCODE(String vOUCHERCODE) {
        this.vOUCHERCODE = vOUCHERCODE;
    }
}
