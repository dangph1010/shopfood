package com.example.duantotnghiep.model;
import com.google.gson.annotations.SerializedName;
public class Cart {
    @SerializedName("USERNAME")
    public String uSERNAME;
    @SerializedName("FOODID")
    public int fOODID;
    @SerializedName("QUANTITY")
    public int qUANTITY;
    @SerializedName("CATID")
    public int cATID;
    @SerializedName("FOODNAME")
    public String fOODNAME;
    @SerializedName("PRICE")
    public int pRICE;
    @SerializedName("IMAGE")
    public String iMAGE;
    @SerializedName("STATUS")
    public String sTATUS;

    public String getuSERNAME() {
        return uSERNAME;
    }

    public void setuSERNAME(String uSERNAME) {
        this.uSERNAME = uSERNAME;
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

    public int getcATID() {
        return cATID;
    }

    public void setcATID(int cATID) {
        this.cATID = cATID;
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

    public String getsTATUS() {
        return sTATUS;
    }

    public void setsTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }
}
