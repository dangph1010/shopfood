package com.example.duantotnghiep.model;

import com.google.gson.annotations.SerializedName;

public class OrderDetail {
    @SerializedName("ORDERID")
    public int oDERID;
    @SerializedName("FOODID")
    public int fOODID;
    @SerializedName("PRICE")
    public int pRICE;
    @SerializedName("QUANTITY")
    public int qUANTITY;
    @SerializedName("TOTAL")
    public float tOTAL;
    @SerializedName("IMAGE")
    public String iMAGE;
    @SerializedName("FOODNAME")
    public String fOODNAME;

    public int getpRICE() {
        return pRICE;
    }

    public void setpRICE(int pRICE) {
        this.pRICE = pRICE;
    }

    public String getiMAGE() {
        return iMAGE;
    }

    public void setiMAGE(String iMAGE) {
        this.iMAGE = iMAGE;
    }

    public String getfOODNAME() {
        return fOODNAME;
    }

    public void setfOODNAME(String fOODNAME) {
        this.fOODNAME = fOODNAME;
    }

    public int getoDERID() {
        return oDERID;
    }

    public void setoDERID(int oDERID) {
        this.oDERID = oDERID;
    }

    public int getfOODID() {
        return fOODID;
    }

    public void setfOODID(int fOODID) {
        this.fOODID = fOODID;
    }

    public int getqUANTITY() {
        return qUANTITY;
    }

    public void setqUANTITY(int qUANTITY) {
        this.qUANTITY = qUANTITY;
    }

    public float gettOTAL() {
        return tOTAL;
    }

    public void settOTAL(float tOTAL) {
        this.tOTAL = tOTAL;
    }
}
