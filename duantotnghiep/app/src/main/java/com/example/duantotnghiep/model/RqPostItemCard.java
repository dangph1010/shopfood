package com.example.duantotnghiep.model;
import com.google.gson.annotations.SerializedName;

public class RqPostItemCard {
    @SerializedName("USERNAME")
    public String uSERNAME;
    @SerializedName("FOODID")
    public int fOODID;
    @SerializedName("QUANTITY")
    public int qUANTITY;

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
}
