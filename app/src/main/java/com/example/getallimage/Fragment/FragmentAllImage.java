package com.example.getallimage.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.example.getallimage.Adapter.ImageAdapter;
import com.example.getallimage.Class.AlbumItem;
import com.example.getallimage.GetAllImage;
import com.example.getallimage.MainActivity;
import com.example.getallimage.MainActivity2;
import com.example.getallimage.OnItemClick;
import com.example.getallimage.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class FragmentAllImage extends Fragment {
    RecyclerView rvMain;
    LayoutAnimationController layoutAnimationController;
    GridLayoutManager gridLayoutManager;
    List<AlbumItem> arrAlbumItem = new ArrayList<>();
    ImageAdapter imageAdapter;
    public FragmentAllImage() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_image, container, false);
        rvMain = view.findViewById(R.id.rvMain);
        imageAdapter = new ImageAdapter(getContext());
        rvMain.setNestedScrollingEnabled(false);
        gridLayoutManager = new GridLayoutManager(getContext(),3);
        rvMain.setLayoutManager(gridLayoutManager);
        rvMain.setAdapter(imageAdapter);
        //set animation và ảnh
        layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_animation);
        try {
            arrAlbumItem = new GetAllImage(getContext()).getAllImg();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        imageAdapter.setData(arrAlbumItem);
        imageAdapter.setOnImageClick(new OnItemClick() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getContext(), MainActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ImgPosition", position);
                bundle.putString("Folder","all");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        return view;
    }

    public void changeColumnNumber(int colNumber){
        gridLayoutManager.setSpanCount(colNumber);
        rvMain.setLayoutManager(gridLayoutManager);
    }

    public void sortByName(){
        Collections.sort(arrAlbumItem, new Comparator<AlbumItem>() {
            @Override
            public int compare(AlbumItem o1, AlbumItem o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        imageAdapter.notifyDataSetChanged();
    }

    public void sortByDateTaken(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Collections.sort(arrAlbumItem, new Comparator<AlbumItem>() {
            @Override
            public int compare(AlbumItem o1, AlbumItem o2) {
                return o1.getDateTaken().compareTo(o2.getDateTaken());
            }
        });
        for (int i = 0; i < arrAlbumItem.size(); i++){
            Date date = new Date(Long.parseLong(arrAlbumItem.get(i).getDateTaken()));
            Log.e("DateTaken",simpleDateFormat.format(date));
        }
        imageAdapter.notifyDataSetChanged();
    }
}