package com.example.getallimage.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getallimage.Class.AlbumItem;
import com.example.getallimage.GetAllImage;
import com.example.getallimage.OnItemClick;
import com.example.getallimage.R;

import java.util.ArrayList;
import java.util.List;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ViewHolder> {
    ArrayList<String> arrFolder;
    GridLayoutManager gridLayoutManager;
    int columnNumber;
    public FolderAdapter(ArrayList<String> arrFolder, int columnNumber){
        this.arrFolder = arrFolder;
        this.columnNumber = columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.folder_image_layout,null);
        view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvFolderName.setText(arrFolder.get(position));
        List<AlbumItem> arrAlbumItem = new GetAllImage(holder.view.getContext()).getAllImgByFolder(arrFolder.get(position));
        FolderItemAdapter folderItemAdapter = new FolderItemAdapter(arrAlbumItem);
        gridLayoutManager = new GridLayoutManager(holder.view.getContext(),columnNumber);
        holder.rvFolderItem.setLayoutManager(gridLayoutManager);
        holder.rvFolderItem.setAdapter(folderItemAdapter);
        holder.rvFolderItem.setVisibility(View.VISIBLE);
//        holder.view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(holder.rvFolderItem.getVisibility()==View.GONE){
//                    holder.rvFolderItem.setVisibility(View.VISIBLE);
//                }else {
//                    holder.rvFolderItem.setVisibility(View.GONE);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return arrFolder.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvFolderName;
        RecyclerView rvFolderItem;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tvFolderName = itemView.findViewById(R.id.tvFolderName);
            rvFolderItem = itemView.findViewById(R.id.rvFolderItem);
        }
    }
}
