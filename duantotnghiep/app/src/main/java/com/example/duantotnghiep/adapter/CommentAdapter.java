package com.example.duantotnghiep.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep.R;
import com.example.duantotnghiep.model.Cart;
import com.example.duantotnghiep.model.Comment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{
    List<Comment> lst;
    Context context;

    public CommentAdapter(List<Comment> lst, Context context) {
        this.lst = lst;
        this.context = context;
    }

    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_items_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(CommentAdapter.ViewHolder holder, int position) {

        holder.tvUserComment.setText(String.valueOf(lst.get(position).getuSERNAME()));
        holder.tvComment.setText(String.valueOf(lst.get(position).getcOMMENT()));
    }

    @Override
    public int getItemCount() {

        return lst.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserComment, tvComment;

        public ViewHolder(View itemView) {
            super(itemView);

            tvUserComment = itemView.findViewById(R.id.tvUserComment);
            tvComment = itemView.findViewById(R.id.tvComment);
        }
    }
}
