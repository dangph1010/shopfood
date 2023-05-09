package com.example.duantotnghiep.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * @author: Phạm Quang Bình
 * @Date: 31/10/2022
 */
public class User {

    @SerializedName("USERNAME")
    public String uSERNAME;
    @SerializedName("FULLNAME")
    public String fULLNAME;
    @SerializedName("PASSWORD")
    public String pASSWORD;
    @SerializedName("EMAIL")
    public String eMAIL;
    @SerializedName("ROLE")
    public int rOLE;
    @SerializedName("IMAGE")
    public String iMAGE;
    @SerializedName("ADDRESS")
    public String aDDRESS;
    @SerializedName("PHONENUMBER")
    public String pHONENUMBER;
    @SerializedName("DATEOFBIRTH")
    public String dATEOFBIRTH;
    @SerializedName("STATUS")
    public String sTATUS;

    public String getuSERNAME() {
        return uSERNAME;
    }

    public void setuSERNAME(String uSERNAME) {
        this.uSERNAME = uSERNAME;
    }

    public String getfULLNAME() {
        return fULLNAME;
    }

    public void setfULLNAME(String fULLNAME) {
        this.fULLNAME = fULLNAME;
    }

    public String getpASSWORD() {
        return pASSWORD;
    }

    public void setpASSWORD(String pASSWORD) {
        this.pASSWORD = pASSWORD;
    }

    public String geteMAIL() {
        return eMAIL;
    }

    public void seteMAIL(String eMAIL) {
        this.eMAIL = eMAIL;
    }

    public int getrOLE() {
        return rOLE;
    }

    public void setrOLE(int rOLE) {
        this.rOLE = rOLE;
    }

    public String getiMAGE() {return iMAGE;}

    public void setiMAGE(String iMAGE) {this.iMAGE = iMAGE;}

    public String getaDDRESS() {
        return aDDRESS;
    }

    public void setaDDRESS(String aDDRESS) {
        this.aDDRESS = aDDRESS;
    }

    public String getpHONENUMBER() {
        return pHONENUMBER;
    }

    public void setpHONENUMBER(String pHONENUMBER) {
        this.pHONENUMBER = pHONENUMBER;
    }

    public String getdATEOFBIRTH() {
        return dATEOFBIRTH;
    }

    public void setdATEOFBIRTH(String dATEOFBIRTH) {
        this.dATEOFBIRTH = dATEOFBIRTH;
    }

    public String getsTATUS() {
        return sTATUS;
    }

    public void setsTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }
}
