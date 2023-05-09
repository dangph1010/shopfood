package com.example.duantotnghiep.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: Phạm Quang Bình
 * @Date: 30/10/2022
 */
public class ApiService {

    // Thêm ip của mình vào đây để khỏi mắc công xóa sửa

    public static String BINH_IP = "192.168.1.3";
    public static String HANH_IP = "192.168.1.146";
    public static String DAT_IP = "192.168.1.169";
    public static String TAI_IP = "192.168.1.40";
    public static String DANG_IP = "192.168.1.10";
    public static String WOOOO = "192.168.43.26";

    private static Retrofit retrofit;



    private static String Base_Url = "http://" + WOOOO + ":5000";





    public static String getBase_Url(){

        return Base_Url;
    }

    public static void setBase_Url(String base_Url) {
        Base_Url = base_Url;

    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
