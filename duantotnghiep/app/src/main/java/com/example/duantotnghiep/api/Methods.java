package com.example.duantotnghiep.api;



import com.example.duantotnghiep.MainActivity.TokenModel;
import com.example.duantotnghiep.UI.Activity.FoodDetailActivity;
import com.example.duantotnghiep.model.Cart;
import com.example.duantotnghiep.model.Comment;
import com.example.duantotnghiep.model.Favourite;
import com.example.duantotnghiep.model.Food;
import com.example.duantotnghiep.model.Order;
import com.example.duantotnghiep.model.OrderDetail;
import com.example.duantotnghiep.model.RpPostItemCard;
import com.example.duantotnghiep.model.RqPostItemCard;
import com.example.duantotnghiep.model.TypeFood;
import com.example.duantotnghiep.model.User;
import com.example.duantotnghiep.UI.Activity.ChangePasswordProfileActivity.PasswordChanger;
import com.example.duantotnghiep.model.VoucherCode;
import com.example.duantotnghiep.model.VoucherDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * @author: Phạm Quang Bình
 * @Date: 30/10/2022
 */
public interface Methods {
    //food
    @GET("api/getfood")
    Call<List<Food>> getdata();

    //foodhome
    @GET("api/foodByStatus")
    Call<List<Food>> getFoodByStatus(@Query("status") String status);

    //typefoodmenu
    @GET("api/gettypefood")
    Call<List<TypeFood>> getttypefood(@Query("type") int type);
//    @GET("api/gettypefood2")
//    Call<List<TypeFood>> getttypefood2();
//    @GET("api/gettypefood3")
//    Call<List<TypeFood>> getttypefood3();
//    @GET("api/gettypefood4")
//    Call<List<TypeFood>> getttypefood4();

    //favourite
    @GET("api/getfavourite")
    Call<List<TypeFood>> getfavourite(@Query("USERNAME") String username);
    @POST("api/postfavourite")
    Call<Favourite> postfavourite(@Body Favourite favourite);
    @GET("api/checkfavourite")
    Call<FoodDetailActivity.MessageModel> checkfavourite(@Query("USERNAME") String username, @Query("FOODID") int foodid);
    @POST("api/deletefavourite")
    Call<Object> deletefavourite(@Body Favourite favourite);

    //comment
//    @GET("api/getcomment")
//    Call<List<Comment>> getComment();

    @GET("api/getcomment")
    Call<List<Comment>> getComment(@Query("FOODID") int foodid);

    @POST("api/postcomment")
    Call<Comment> postComment(@Body Comment comment);

    //password
    @POST("api/reset-password-request")
    Call<Object> passwordRequest(@Body User user);
    //user
    @POST("api/postuser")
    Call<User> postUser(@Body User user);
    @POST("api/login")
    Call<User> postLogin(@Body User user);
    @GET ("api/getuser-infor")
    Call<User> getUserInfor(@Query("username") String username);
    @POST("api/send-token")
    Call<String> sendToken(@Body TokenModel tokenModel);


    @POST("api/update-infor")
    Call<User> updateinfor(@Body User user);
    @POST("api/update-password")
    Call<PasswordChanger> updatepassword(@Body PasswordChanger changer);

    //detail
    @POST("api/postcart")
    Call<List<Cart>> getdatacart(@Body User user);
//    Call<CartFragment2.CartVoucherModel> getdatacart(@Body User user);

    @POST("api/postitemcard")
    Call<RpPostItemCard> postitemcard(@Body RqPostItemCard data);

    @PUT("api/updatecart")
    Call<RpPostItemCard> updatecart(@Body Cart update);

    @POST("api/deleteitemcart")
    Call<RpPostItemCard> deleteitemcard(@Body Favourite delItem);

//    @GET("otp")
//    Call<OtpModel> getOTP();

    //Voucher
    @POST("api/voucher")
    Call<List<VoucherDetail>> getVoucher(@Body VoucherCode voucher);

    @PUT("api/updatevoucher")
    Call<RpPostItemCard> updatevoucher(@Body VoucherDetail upvoucher);

    //order
    @GET("api/getorder")
    Call<List<OrderDetail>> getOrder(@Query("USERNAME") String username);
//    @POST("api/updateorder")
//    Call<User> postOrderStatus(@Body User user);
    @POST("api/postorder")
    Call<List<Order>> postOrder(@Body Order order);
    //lich su order
    @GET("api/orderList")
    Call<List<Order>> getOrderList(@Query("username") String username);
    //chi tiet lich su don hang
    @GET("api/orderList/detail")
    Call<List<OrderDetail>> getOrderDetail(@Query("username") String username, @Query("orderid") int orderid);
}
