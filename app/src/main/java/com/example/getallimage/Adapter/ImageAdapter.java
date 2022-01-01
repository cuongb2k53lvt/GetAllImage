package com.example.getallimage.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.getallimage.OnItemClick;
import com.example.getallimage.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private Context context;
    private List<String> arrPhoto;
    public int lastPosition = -1;
    private OnItemClick onItemClick;
    public ImageAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<String> arrPhoto){
        this.arrPhoto = arrPhoto;
        notifyDataSetChanged();
    }

    public void setOnImageClick(OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String uri = arrPhoto.get(position);
        if(uri == null){
            return;
        }
        //Load ảnh bằng Glide
        Glide.with(context).load(uri).placeholder(R.drawable.ic_gallery).into(holder.img);
        //Set Animation cho ảnh
        setAnimation(holder.img,position);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onClick(position);
            }
        });
    }

    public void setAnimation (View view, int position){
        if(position>lastPosition){
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.rv_anim);
            view.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public long getItemId(int position) {
        return arrPhoto.get(position).hashCode();
    }

    @Override
    public int getItemCount() {
        if(arrPhoto!=null){
            return arrPhoto.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            view = itemView;
        }
    }
}
