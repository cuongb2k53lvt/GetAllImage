package com.example.getallimage.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.getallimage.R;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {
    Context context;
    List<String> arrPhoto;
    public ViewPagerAdapter(Context context){
        this.context = context;
    }

    public void setImage(List<String> arrPhoto){
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String uri = arrPhoto.get(position);
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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgViewPager);
        }
    }
}
