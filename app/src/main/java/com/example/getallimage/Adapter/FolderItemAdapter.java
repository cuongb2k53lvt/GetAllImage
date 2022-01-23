package com.example.getallimage.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.getallimage.Class.AlbumItem;
import com.example.getallimage.MainActivity2;
import com.example.getallimage.R;

import java.util.List;

public class FolderItemAdapter extends RecyclerView.Adapter<FolderItemAdapter.ViewHolder> {
    List<AlbumItem> arrAlbumItem;
    String uri = "";
    public FolderItemAdapter(List<AlbumItem> arrAlbumItem){
        this.arrAlbumItem = arrAlbumItem;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.image_item,null);
        view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        uri = arrAlbumItem.get(position).getPath();
        //Load ảnh bằng Glide
        Glide.with(holder.view).load(uri).placeholder(R.drawable.ic_gallery).into(holder.imgItem);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.view.getContext(), MainActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ImgPosition", position);
                bundle.putString("Folder",arrAlbumItem.get(position).getFolderName());
                intent.putExtras(bundle);
                holder.view.getContext().startActivity(intent);
            }
        });
    }
    
    @Override
    public int getItemCount() {
        return arrAlbumItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgItem;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            imgItem = itemView.findViewById(R.id.img);
        }
    }
}
