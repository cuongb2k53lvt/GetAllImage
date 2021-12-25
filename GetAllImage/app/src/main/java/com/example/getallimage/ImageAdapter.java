package com.example.getallimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private Context context;
    private List<String> arrPhoto;

    public ImageAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<String> arrPhoto){
        this.arrPhoto = arrPhoto;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String uri = arrPhoto.get(position);
        if(uri == null){
            return;
        }
        Glide.with(context).load(uri).centerCrop().fitCenter().placeholder(R.drawable.ic_gallery).into(holder.img);
//        holder.img.setImageURI(uri);
//        try {
//            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(),uri);
//            if(bitmap!=null){
//                holder.img.setImageBitmap(bitmap);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
        }
    }
}
