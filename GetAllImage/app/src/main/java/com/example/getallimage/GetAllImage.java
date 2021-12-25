package com.example.getallimage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

public class GetAllImage{
    Context context1;
    public GetAllImage(Context context) {
        this.context1 = context;
    }
    public List<String> getAllImg(){
        List<String> arrImg = new ArrayList<>();
        Cursor cursor = context1.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.MediaColumns.DATA},null,null,null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String absolutePathOfImage = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
            arrImg.add(absolutePathOfImage);
        }
        cursor.close();
        return arrImg;
    }
}
