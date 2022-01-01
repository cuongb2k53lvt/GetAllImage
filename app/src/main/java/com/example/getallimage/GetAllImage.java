package com.example.getallimage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetAllImage{
    Context context1;
    public static final String checkExistFile = "Existed";
    public GetAllImage(Context context) {
        this.context1 = context;
    }
    public List<String> getAllImg(){
        List<String> arrImg = new ArrayList<>();
        //Lấy ảnh trong External storage
        Cursor cursor = context1.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.MediaColumns.DATA},null,null,null);
        while (cursor.moveToNext()) {
            int column_index = cursor.getColumnIndex(MediaStore.MediaColumns.DATA);
            String absolutePathOfImage = cursor.getString(column_index);
            Uri imgUri = Uri.parse(absolutePathOfImage);
            File imageFile = new File(imgUri.getPath());
            if(imageFile.exists()){
                arrImg.add(absolutePathOfImage);
                Log.e(checkExistFile,"True");
            }else {
                Log.e(checkExistFile,"False");
            }
        }
        cursor.close();
        //Lấy ảnh trong Internal storage
        Cursor cursor2 = context1.getContentResolver().query(MediaStore.Images.Media.INTERNAL_CONTENT_URI, new String[]{MediaStore.MediaColumns.DATA},null,null,null);
        while (cursor2.moveToNext()) {
            int column_index = cursor2.getColumnIndex(MediaStore.MediaColumns.DATA);
            String absolutePathOfImage = cursor2.getString(column_index);
            Uri imgUri = Uri.parse(absolutePathOfImage);
            File imageFile = new File(imgUri.getPath());
            if(imageFile.exists()){
                arrImg.add(absolutePathOfImage);
                Log.e(checkExistFile,"True");
            }else {
                Log.e(checkExistFile,"False");
            }
        }
        cursor2.close();
        return arrImg;
    }
}
