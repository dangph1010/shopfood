package com.example.duantotnghiep.adapter;

import static com.example.duantotnghiep.UI.Fragment.CartFragment2.setRcvCart;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep.MainActivity;
import com.example.duantotnghiep.R;
import com.example.duantotnghiep.SplashScreen;
import com.example.duantotnghiep.UI.Fragment.CartFragment2;
import com.example.duantotnghiep.UI.loginscreen.SigninActivity;
import com.example.duantotnghiep.api.ApiService;
import com.example.duantotnghiep.api.Methods;
import com.example.duantotnghiep.model.Cart;
import com.example.duantotnghiep.model.Favourite;
import com.example.duantotnghiep.model.Food;
import com.example.duantotnghiep.model.RpPostItemCard;
import com.example.duantotnghiep.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    Context context;
    List<Cart> lst;
    int TongTien, sl1;

    public CartAdapter(Context context, List<Cart> lst) {
        this.context = context;
        this.lst = lst;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_items_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

            Glide.with(context).load(lst.get(position).getiMAGE()).into(holder.ivItemCart);
            holder.tvNameItemCart.setText(lst.get(position).getfOODNAME());
            int price = lst.get(position).getpRICE() * lst.get(position).getqUANTITY();
            holder.tvPriceItemCart.setText(price+" VNĐ");
            holder.tvQuantityItemCart.setText(String.valueOf(lst.get(position).getqUANTITY()));

            holder.imgAddItemCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int sl = Integer.valueOf(holder.tvQuantityItemCart.getText().toString()) + 1;
                    holder.tvQuantityItemCart.setText(String.valueOf(sl));
                    TongTien = lst.get(position).getpRICE()
                            * Integer.valueOf(holder.tvQuantityItemCart.getText().toString());
                    holder.tvPriceItemCart.setText(TongTien + " VNĐ");

                    Methods methods = ApiService.getRetrofit().create(Methods.class);
                    Cart cart = new Cart();
                    cart.setfOODID(lst.get(position).fOODID);
                    cart.setuSERNAME(SplashScreen.getUser().getuSERNAME());
                    Log.d( "UPDATE CART",SplashScreen.getUser().getuSERNAME().toString());
                    Log.d("IDDDDDDDDDDD:...", String.valueOf(lst.get(position).fOODID).toString());
                    cart.setqUANTITY(sl);
                    Call<RpPostItemCard> call = methods.updatecart(cart);
                    call.enqueue(new Callback<RpPostItemCard>() {
                        @Override
                        public void onResponse(Call<RpPostItemCard> call, Response<RpPostItemCard> response) {
                            Toast.makeText(context, "Update Success", Toast.LENGTH_LONG).show();
                            CartFragment2.setRcvCart();
                        }

                        @Override
                        public void onFailure(Call<RpPostItemCard> call, Throwable t) {

                        }
                    });
                }
            });

            holder.imgMinusItemCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int sl = Integer.valueOf(holder.tvQuantityItemCart.getText().toString());
                    if( sl < 2){
                        return;
                    }else {
                        sl1 = sl - 1;
                        holder.tvQuantityItemCart.setText(String.valueOf(sl1));
                        TongTien = TongTien - lst.get(position).getpRICE();
                        holder.tvPriceItemCart.setText(TongTien + " VNĐ");
                    }

                    Methods methods = ApiService.getRetrofit().create(Methods.class);
                    Cart cart = new Cart();
                    cart.setfOODID(lst.get(position).fOODID);
                    cart.setuSERNAME(SplashScreen.getUser().getuSERNAME());
                    Log.d( "UPDATE CART",SplashScreen.getUser().getuSERNAME().toString());
                    Log.d("IDDDDDDDDDDD:...", String.valueOf(lst.get(position).fOODID).toString());
                    cart.setqUANTITY(sl1);
                    Call<RpPostItemCard> call = methods.updatecart(cart);
                    call.enqueue(new Callback<RpPostItemCard>() {
                        @Override
                        public void onResponse(Call<RpPostItemCard> call, Response<RpPostItemCard> response) {
                            Toast.makeText(context, "Update Success", Toast.LENGTH_LONG).show();
                            CartFragment2.setRcvCart();
                        }

                        @Override
                        public void onFailure(Call<RpPostItemCard> call, Throwable t) {

                        }
                    });
                }
            });

            holder.ivDeleteCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(v.getContext());
                    builder.setTitle("Are you sure");
                    builder.setMessage("Please confirm");
                    builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Methods methods = ApiService.getRetrofit().create(Methods.class);
                            Favourite item = new Favourite();
                            item.setfOODID(lst.get(position).fOODID);
                            item.setuSERNAME(SplashScreen.getUser().getuSERNAME());
                            Call<RpPostItemCard> call = methods.deleteitemcard(item);
                            call.enqueue(new Callback<RpPostItemCard>() {
                                @Override
                                public void onResponse(Call<RpPostItemCard> call, Response<RpPostItemCard> response) {
                                    Toast.makeText(context, "Delete Success", Toast.LENGTH_LONG).show();
                                    CartFragment2.setRcvCart();
                                }

                                @Override
                                public void onFailure(Call<RpPostItemCard> call, Throwable t) {

                                }
                            });
                        }
                    });
                    builder.show();
                }
            });
    }

    @Override
    public int getItemCount() {

        return lst.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivItemCart, ivDeleteCart, imgMinusItemCart, imgAddItemCart;
        TextView tvNameItemCart, tvPriceItemCart, tvQuantityItemCart;

        public ViewHolder(View itemView) {
            super(itemView);

            ivItemCart = itemView.findViewById(R.id.ivItemCart);
            ivDeleteCart = itemView.findViewById(R.id.ivDeleteCart);
            imgMinusItemCart = itemView.findViewById(R.id.imgMinusItemCart);
            imgAddItemCart = itemView.findViewById(R.id.imgAddItemCart);
            tvNameItemCart = itemView.findViewById(R.id.tvNameItemCart);
            tvPriceItemCart = itemView.findViewById(R.id.tvPriceItemCart);
            tvQuantityItemCart = itemView.findViewById(R.id.tvQuantityItemCart);
        }
    }
}
