package com.example.duantotnghiep.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author: Phạm Quang Bình
 * @Date: 30/10/2022
 */
public class TypeFood {
    @SerializedName("FOODID")
    public int fOODID;
    @SerializedName("CATID")
    public int cATID;
    @SerializedName("QUANTITY")
    public int qUANTITY;
    @SerializedName("FOODNAME")
    public String fOODNAME;
    @SerializedName("PRICE")
    public int pRICE;
    @SerializedName("IMAGE")
    public String iMAGE;

    public int getfOODID() {
        return fOODID;
    }

    public void setfOODID(int fOODID) {
        this.fOODID = fOODID;
    }

    public int getcATID() {
        return cATID;
    }

    public void setcATID(int cATID) {
        this.cATID = cATID;
    }

    public int getqUANTITY() {
        return qUANTITY;
    }

    public void setqUANTITY(int qUANTITY) {
        this.qUANTITY = qUANTITY;
    }

    public String getfOODNAME() {
        return fOODNAME;
    }

    public void setfOODNAME(String fOODNAME) {
        this.fOODNAME = fOODNAME;
    }

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
}
