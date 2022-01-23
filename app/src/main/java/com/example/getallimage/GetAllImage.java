package com.example.getallimage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.getallimage.Class.AlbumItem;
import com.example.getallimage.Class.ItemInfo;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetAllImage{
    Context context1;
    public static final String checkExistFile = "Existed";
    public GetAllImage(Context context) {
        this.context1 = context;
    }
    public List<AlbumItem> getAllImgByFolder(String foldername){
        List<AlbumItem> arrImg = new ArrayList<>();
        //Lấy ảnh trong External storage
        Cursor cursor = context1.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.MediaColumns.DATA,
                        MediaStore.Images.Media.DISPLAY_NAME,
                        MediaStore.Images.Media.DATE_TAKEN,
                        MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                        MediaStore.Images.Media.ORIENTATION,
                        MediaStore.Images.Media.WIDTH,
                        MediaStore.Images.Media.HEIGHT,
                        MediaStore.Images.Media.SIZE},null,null,null);
        while (cursor.moveToNext()) {
            int column_path = cursor.getColumnIndex(MediaStore.MediaColumns.DATA);
            int column_name = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);
            int column_date_taken = cursor.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN);
            int column_folder_name = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
            int column_orientarion = cursor.getColumnIndex(MediaStore.Images.Media.ORIENTATION);
            int column_width = cursor.getColumnIndex(MediaStore.Images.Media.WIDTH);
            int comlumn_height = cursor.getColumnIndex(MediaStore.Images.Media.HEIGHT);
            int column_size = cursor.getColumnIndex(MediaStore.Images.Media.SIZE);
            String absolutePathOfImage = cursor.getString(column_path);
            String nameOfImage = cursor.getString(column_name);
            String dateTaken = cursor.getString(column_date_taken);
            String folderName = cursor.getString(column_folder_name);
            String orientation = cursor.getString(column_orientarion);
            String width = cursor.getString(column_width);
            String height = cursor.getString(comlumn_height);
            String size = cursor.getString(column_size);
            Uri imgUri = Uri.parse(absolutePathOfImage);
            File imageFile = new File(imgUri.getPath());
            if(imageFile.exists()&&foldername.equals(folderName)){
                ItemInfo itemInfo = new ItemInfo(orientation, width, height, size);
                AlbumItem item = new AlbumItem(nameOfImage,absolutePathOfImage,dateTaken,folderName,itemInfo);
                arrImg.add(item);
                Log.e(checkExistFile,"True");
            }else {
                Log.e(checkExistFile,"False");
            }
        }
        cursor.close();
        //Lấy ảnh trong Internal storage
        Cursor cursor2 = context1.getContentResolver().query(MediaStore.Images.Media.INTERNAL_CONTENT_URI,
                new String[]{MediaStore.MediaColumns.DATA,
                        MediaStore.Images.Media.DISPLAY_NAME,
                        MediaStore.Images.Media.DATE_TAKEN,
                        MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                        MediaStore.Images.Media.ORIENTATION,
                        MediaStore.Images.Media.WIDTH,
                        MediaStore.Images.Media.HEIGHT,
                        MediaStore.Images.Media.SIZE},null,null,null);
        while (cursor2.moveToNext()) {
            int column_path = cursor2.getColumnIndex(MediaStore.MediaColumns.DATA);
            int column_name = cursor2.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);
            int column_date_taken = cursor2.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN);
            int column_folder_name = cursor2.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
            int column_orientarion = cursor2.getColumnIndex(MediaStore.Images.Media.ORIENTATION);
            int column_width = cursor2.getColumnIndex(MediaStore.Images.Media.WIDTH);
            int comlumn_height = cursor2.getColumnIndex(MediaStore.Images.Media.HEIGHT);
            int column_size = cursor2.getColumnIndex(MediaStore.Images.Media.SIZE);
            String absolutePathOfImage = cursor2.getString(column_path);
            String nameOfImage = cursor2.getString(column_name);
            String dateTaken = cursor2.getString(column_date_taken);
            String folderName = cursor2.getString(column_folder_name);
            String orientation = cursor2.getString(column_orientarion);
            String width = cursor2.getString(column_width);
            String height = cursor2.getString(comlumn_height);
            String size = cursor2.getString(column_size);
            Uri imgUri = Uri.parse(absolutePathOfImage);
            File imageFile = new File(imgUri.getPath());
            if(imageFile.exists()&&foldername.equals(folderName)){
                ItemInfo itemInfo = new ItemInfo(orientation, width, height, size);
                AlbumItem item = new AlbumItem(nameOfImage,absolutePathOfImage,dateTaken,folderName,itemInfo);
                arrImg.add(item);
                Log.e(checkExistFile,"True");
            }else {
                Log.e(checkExistFile,"False");
            }
        }
        cursor2.close();
        return arrImg;
    }
    public List<AlbumItem> getAllImg() throws ParseException {
        List<AlbumItem> arrImg = new ArrayList<>();
        //Lấy ảnh trong External storage
        Cursor cursor = context1.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_TAKEN,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.ORIENTATION,
                MediaStore.Images.Media.WIDTH,
                MediaStore.Images.Media.HEIGHT,
                MediaStore.Images.Media.SIZE},null,null,null);
        while (cursor.moveToNext()) {
            int column_path = cursor.getColumnIndex(MediaStore.MediaColumns.DATA);
            int column_name = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);
            int column_date_taken = cursor.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN);
            int column_folder_name = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
            int column_orientarion = cursor.getColumnIndex(MediaStore.Images.Media.ORIENTATION);
            int column_width = cursor.getColumnIndex(MediaStore.Images.Media.WIDTH);
            int comlumn_height = cursor.getColumnIndex(MediaStore.Images.Media.HEIGHT);
            int column_size = cursor.getColumnIndex(MediaStore.Images.Media.SIZE);
            String absolutePathOfImage = cursor.getString(column_path);
            String nameOfImage = cursor.getString(column_name);
            String dateTaken = cursor.getString(column_date_taken);
            String folderName = cursor.getString(column_folder_name);
            String orientation = cursor.getString(column_orientarion);
            String width = cursor.getString(column_width);
            String height = cursor.getString(comlumn_height);
            String size = cursor.getString(column_size);
            Uri imgUri = Uri.parse(absolutePathOfImage);
            File imageFile = new File(imgUri.getPath());
            if(imageFile.exists()){
                ItemInfo itemInfo = new ItemInfo(orientation, width, height, size);
                AlbumItem item = new AlbumItem(nameOfImage,absolutePathOfImage,dateTaken,folderName,itemInfo);
                arrImg.add(item);
                Log.e(checkExistFile,"True");
            }else {
                Log.e(checkExistFile,"False");
            }
        }
        cursor.close();
        //Lấy ảnh trong Internal storage
        Cursor cursor2 = context1.getContentResolver().query(MediaStore.Images.Media.INTERNAL_CONTENT_URI,
                new String[]{MediaStore.MediaColumns.DATA,
                        MediaStore.Images.Media.DISPLAY_NAME,
                        MediaStore.Images.Media.DATE_TAKEN,
                        MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                        MediaStore.Images.Media.ORIENTATION,
                        MediaStore.Images.Media.WIDTH,
                        MediaStore.Images.Media.HEIGHT,
                        MediaStore.Images.Media.SIZE},null,null,null);
        while (cursor2.moveToNext()) {
            int column_path = cursor2.getColumnIndex(MediaStore.MediaColumns.DATA);
            int column_name = cursor2.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);
            int column_date_taken = cursor2.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN);
            int column_folder_name = cursor2.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
            int column_orientarion = cursor2.getColumnIndex(MediaStore.Images.Media.ORIENTATION);
            int column_width = cursor2.getColumnIndex(MediaStore.Images.Media.WIDTH);
            int comlumn_height = cursor2.getColumnIndex(MediaStore.Images.Media.HEIGHT);
            int column_size = cursor2.getColumnIndex(MediaStore.Images.Media.SIZE);
            String absolutePathOfImage = cursor2.getString(column_path);
            String nameOfImage = cursor2.getString(column_name);
            String dateTaken = cursor2.getString(column_date_taken);
            String folderName = cursor2.getString(column_folder_name);
            String orientation = cursor2.getString(column_orientarion);
            String width = cursor2.getString(column_width);
            String height = cursor2.getString(comlumn_height);
            String size = cursor2.getString(column_size);
            Uri imgUri = Uri.parse(absolutePathOfImage);
            File imageFile = new File(imgUri.getPath());
            if(imageFile.exists()){
                ItemInfo itemInfo = new ItemInfo(orientation, width, height, size);
                AlbumItem item = new AlbumItem(nameOfImage,absolutePathOfImage,dateTaken,folderName,itemInfo);
                arrImg.add(item);
                Log.e(checkExistFile,"True");
            }else {
                Log.e(checkExistFile,"False");
            }
        }
        cursor2.close();
        return arrImg;
    }

}
