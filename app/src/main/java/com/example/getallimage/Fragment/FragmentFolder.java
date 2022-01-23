package com.example.getallimage.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.example.getallimage.Adapter.FolderAdapter;
import com.example.getallimage.Adapter.ImageAdapter;
import com.example.getallimage.Class.AlbumItem;
import com.example.getallimage.Class.ItemInfo;
import com.example.getallimage.GetAllImage;
import com.example.getallimage.OnItemClick;
import com.example.getallimage.R;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class FragmentFolder extends Fragment {
    RecyclerView rvFolder;
    FolderAdapter folderAdapter;
    public FragmentFolder() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Tạo recycler view
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_folder, container, false);
        rvFolder = view.findViewById(R.id.rvFolder);
        ArrayList<String> arrFolderName = new ArrayList<>();
        String folderName = "";
        //Lấy danh sách album item
        try {
            List<AlbumItem> arrAlbumItem = new GetAllImage(getContext()).getAllImg();
            for (int i = 0; i < arrAlbumItem.size(); i++){
                if(!folderName.equals(arrAlbumItem.get(i).getFolderName())){
                    folderName = arrAlbumItem.get(i).getFolderName();
                    if(!arrFolderName.contains(folderName)){
                        arrFolderName.add(folderName);
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        folderAdapter = new FolderAdapter(arrFolderName,3);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvFolder.setLayoutManager(linearLayoutManager);
        rvFolder.setAdapter(folderAdapter);
        return view;
    }
    public void changeColumnNumber(int numberCol){
        folderAdapter.setColumnNumber(numberCol);
    }
}