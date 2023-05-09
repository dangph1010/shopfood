package com.example.duantotnghiep.UI.Activity;

import static com.android.volley.VolleyLog.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep.R;
import com.example.duantotnghiep.SplashScreen;
import com.example.duantotnghiep.UI.loginscreen.SigninActivity;
import com.example.duantotnghiep.adapter.CommentAdapter;
import com.example.duantotnghiep.api.ApiService;
import com.example.duantotnghiep.api.Methods;
import com.example.duantotnghiep.model.Comment;
import com.example.duantotnghiep.model.RpPostItemCard;
import com.example.duantotnghiep.model.RqPostItemCard;
import com.example.duantotnghiep.model.Favourite;
import com.google.gson.annotations.SerializedName;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodDetailActivity extends AppCompatActivity {

    TextView tvNameDetailFood, tvPriceDetailFood, tvQuantity, tvAddFoodToCart;
    ImageView ivDetailFood, imgAdd, imgMinus , savefavourite , btnBack;
    EditText edtComment;
    CardView btnAddComment;
    CommentAdapter commentAdapter;
    RecyclerView rcvComment;
    List<Comment> lstComment;
    int TongTien;
    String idUser = SplashScreen.getUser().getuSERNAME();
    static boolean isSaveFavourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_food_detail_item);
        tvNameDetailFood = findViewById(R.id.tvNameDetailFood);
        tvPriceDetailFood = findViewById(R.id.tvPriceDetailFood);
        ivDetailFood = findViewById(R.id.ivDetailFood);
        tvQuantity = findViewById(R.id.tvQuantity);
        tvAddFoodToCart = findViewById(R.id.tvAddFoodToCart);
        edtComment = findViewById(R.id.edtComment);
        imgAdd = findViewById(R.id.imgAdd);
        imgMinus = findViewById(R.id.imgMinus);
        savefavourite = findViewById(R.id.savefavourite);
        btnBack = findViewById(R.id.btnBack);
        btnAddComment = findViewById(R.id.btnAddComment);


        Intent intent = getIntent();
        int idfood = intent.getIntExtra("id", -1);
        String namefood = intent.getStringExtra("name");
        String imagefood = intent.getStringExtra("image");
        String username = SplashScreen.getUser().getuSERNAME();
        int pricefood = intent.getIntExtra("price",-1);
        int quantityfood = intent.getIntExtra("quantity",-1);

        //comment
        rcvComment = findViewById(R.id.rcvComment);
        lstComment = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,false);
        rcvComment.setLayoutManager(linearLayoutManager);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postComment(idfood);
            }
        });

        //set text
        tvNameDetailFood.setText(namefood);
//        tvPriceDetailFood.setText(String.valueOf(pricefood) + " " + "VNĐ");
        tvPriceDetailFood.setText(NumberFormat.getNumberInstance(new Locale("us")).format(pricefood) +"đ");
        tvAddFoodToCart.setText("Add to cart :" + " " + pricefood + " " + "VNĐ");
        Glide.with(this).load(imagefood).into(ivDetailFood);

        getComment(idfood);
        checkFavourite(idfood);

        //up quantity
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl = Integer.valueOf(tvQuantity.getText().toString());
                if ( sl < quantityfood == false){
                    return;
                }else {
                    int sl1 = sl + 1;
                    tvQuantity.setText(String.valueOf(sl1));
                    TongTien = pricefood * Integer.valueOf(tvQuantity.getText().toString());
                    tvAddFoodToCart.setText("Add to cart :" + " " + String.valueOf(TongTien) + " " + "VNĐ");
                }
            }
        });

        imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl = Integer.valueOf(tvQuantity.getText().toString());
                if( sl < 2){
                    return;
                }else {
                    int sl1 = sl - 1;
                    tvQuantity.setText(String.valueOf(sl1));
                    TongTien = TongTien - pricefood;
                    Log.d("SSFSFSFSFSF", String.valueOf(TongTien));
                    tvAddFoodToCart.setText("Add to cart :" + " " + String.valueOf(TongTien) + " " + "VNĐ");
                }
            }
        });

        //add to cart
        tvAddFoodToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Methods methods = ApiService.getRetrofit().create(Methods.class);
                RqPostItemCard temp = new RqPostItemCard();
                temp.setuSERNAME(username);
                temp.setfOODID(idfood);
                temp.setqUANTITY(Integer.valueOf(tvQuantity.getText().toString()));
                Call<RpPostItemCard> call = methods.postitemcard(temp);
                call.enqueue(new Callback<RpPostItemCard>() {
                    @Override
                    public void onResponse(Call<RpPostItemCard> call, Response<RpPostItemCard> response) {
                        Toast.makeText(FoodDetailActivity.this, "Success", Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<RpPostItemCard> call, Throwable t) {
                        Toast.makeText(FoodDetailActivity.this, "Error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


        savefavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSaveFavourite){
                    deleteFavourite(idfood);
                }else{
                    postFavourite(idfood);
                }
            }
        });
    }

    private void getComment(int idfood) {
        Methods methods = ApiService.getRetrofit().create(Methods.class);
        Call<List<Comment>> call = methods.getComment(idfood);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                List<Comment> data = response.body();
                if(data != null){
                    for (Comment dt:data
                    ) {
                        Log.d("Comment......", String.valueOf(dt.fOODID));
                    }
                    commentAdapter = new CommentAdapter(data,FoodDetailActivity.this);
                    rcvComment.setAdapter(commentAdapter);
                    commentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
    }

    public void postComment(int idfood) {
        Methods methods = ApiService.getRetrofit().create(Methods.class);
        Comment comment = new Comment();
        comment.setfOODID(idfood);
        comment.setuSERNAME(idUser);
        comment.setcOMMENT(edtComment.getText().toString());
        Call<Comment> call = methods.postComment(comment);
        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                Toast.makeText(FoodDetailActivity.this, "Thanks for comment!", Toast.LENGTH_SHORT).show();
                getComment(idfood);
                edtComment.setText("");
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {

            }
        });
    }

    public void postFavourite(int idfood) {
        Methods methods = ApiService.getRetrofit().create(Methods.class);
        Favourite favourite = new Favourite();
        favourite.setfOODID(idfood);
        favourite.setuSERNAME(idUser);
        Call<Favourite> call = methods.postfavourite(favourite);
        call.enqueue(new Callback<Favourite>() {
            @Override
            public void onResponse(Call<Favourite> call, Response<Favourite> response) {
                checkFavourite(idfood);
            }

            @Override
            public void onFailure(Call<Favourite> call, Throwable t) {

            }
        });
    }

    public void deleteFavourite(int idfood) {
        Methods methods = ApiService.getRetrofit().create(Methods.class);
        Favourite favourite = new Favourite();
        favourite.setfOODID(idfood);
        favourite.setuSERNAME(idUser);
        Call<Object> call = methods.deletefavourite(favourite);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                checkFavourite(idfood);
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }

    public void checkFavourite(int idfood) {
        Methods methods = ApiService.getRetrofit().create(Methods.class);
        Call<MessageModel> call = methods.checkfavourite(idUser, idfood);
        call.enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                MessageModel message = response.body();
                if(message.getMessage().trim().equals("Liked")){
                    isSaveFavourite = true;
                    savefavourite.setImageResource(R.drawable.ic_heart_red);
                }else if (message.getMessage().trim().equals("Not liked")){
                    isSaveFavourite = false;
                    savefavourite.setImageResource(R.drawable.ic_heart);
                }
                if(message.getMessage2().trim().equals("Bought")){
//                    isSaveFavourite = true;
//                    savefavourite.setImageResource(R.drawable.ic_heart_red);
                }else if (message.getMessage2().trim().equals("Havent bought")){
//                    isSaveFavourite = false;
//                    savefavourite.setImageResource(R.drawable.ic_heart);
                    disableComment(edtComment, btnAddComment);
                }
                if(message.getMessage3().trim().equals("In cart")){
                    tvAddFoodToCart.setText("Already in your cart! please check");
                    tvAddFoodToCart.setEnabled(false);
                }else if (message.getMessage3().trim().equals("Not in cart")){

                }
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {

            }
        });

    }

    public class MessageModel {
        @SerializedName("MESSAGE")
        String message;
        @SerializedName("MESSAGE2")
        String message2;
        @SerializedName("MESSAGE3")
        String message3;

        public String getMessage3() {
            return message3;
        }

        public void setMessage3(String message3) {
            this.message3 = message3;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMessage2() {
            return message2;
        }

        public void setMessage2(String message2) {
            this.message2 = message2;
        }
    }

    private void disableComment(EditText editText, CardView btnAddComment) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        editText.setHint("Oops! make sure you've bought it");
        editText.setBackgroundColor(Color.TRANSPARENT);
        btnAddComment.setEnabled(false);
    }
}