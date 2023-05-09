package com.example.duantotnghiep.model;

import com.google.gson.annotations.SerializedName;

public class Favourite {
    @SerializedName("FOODID")
    public int fOODID;
    @SerializedName("USERNAME")
    public String uSERNAME;

    public int getfOODID() {
        return fOODID;
    }

    public void setfOODID(int fOODID) {
        this.fOODID = fOODID;
    }

    public String getuSERNAME() {
        return uSERNAME;
    }

    public void setuSERNAME(String uSERNAME) {
        this.uSERNAME = uSERNAME;
    }
}
