package com.example.duantotnghiep.model;
import com.google.gson.annotations.SerializedName;

public class VoucherDetail {
    @SerializedName("VOUCHER")
    public String vOUCHER;
    @SerializedName("QUANTITY")
    public int qUANTITY;
    @SerializedName("VOUCHERCODE")
    public String vOUCHERCODE;

    public int getqUANTITY() {
        return qUANTITY;
    }

    public void setqUANTITY(int qUANTITY) {
        this.qUANTITY = qUANTITY;
    }

    public String getvOUCHER() {
        return vOUCHER;
    }

    public void setvOUCHER(String vOUCHER) {
        this.vOUCHER = vOUCHER;
    }

    public String getvOUCHERCODE() {
        return vOUCHERCODE;
    }

    public void setvOUCHERCODE(String vOUCHERCODE) {
        this.vOUCHERCODE = vOUCHERCODE;
    }
}
