package com.example.getallimage.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.getallimage.Class.AlbumItem;
import com.example.getallimage.OnItemClick;
import com.example.getallimage.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {
    Context context;
    List<AlbumItem> arrPhoto;
    public ViewPagerAdapter(Context context){
        this.context = context;
    }

    public void setImage(List<AlbumItem> arrPhoto){
        this.arrPhoto = arrPhoto;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.viewpager_img,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String uri = arrPhoto.get(position).getPath();
        if(uri == null){
            return;
        }
        //Load ảnh bằng Glide
        Glide.with(context).load(uri).placeholder(R.drawable.ic_gallery).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(arrPhoto!=null){
            return arrPhoto.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            imageView = itemView.findViewById(R.id.imgViewPager);
        }
    }
}
