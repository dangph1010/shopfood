package com.example.duantotnghiep.model;
import com.google.gson.annotations.SerializedName;

public class VoucherCode {
    @SerializedName("VOUCHERCODE")
    public String vOUCHERCODE;
    @SerializedName("USERNAME")
    public String uSERNAME;

    public String getvOUCHERCODE() {
        return vOUCHERCODE;
    }

    public void setvOUCHERCODE(String vOUCHERCODE) {
        this.vOUCHERCODE = vOUCHERCODE;
    }

    public String getuSERNAME() {
        return uSERNAME;
    }

    public void setuSERNAME(String uSERNAME) {
        this.uSERNAME = uSERNAME;
    }
}
