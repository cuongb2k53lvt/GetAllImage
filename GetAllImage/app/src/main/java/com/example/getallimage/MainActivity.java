package com.example.getallimage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;
import gun0912.tedbottompicker.TedBottomSheetDialogFragment;

public class MainActivity extends AppCompatActivity {
    Button btnSelect, btnWrite, btnRead;
    RecyclerView rv;
    ImageAdapter imageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSelect = findViewById(R.id.btn_select);
        btnWrite = findViewById(R.id.btn_write);
        btnRead = findViewById(R.id.btn_read);
        rv = findViewById(R.id.rv);
        imageAdapter = new ImageAdapter(MainActivity.this);
        imageAdapter.setHasStableIds(true);
        rv.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,2);
        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(imageAdapter);
        rv.setNestedScrollingEnabled(false);
        RequestPermission();
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                GetImageFromGallery();
            imageAdapter.setData(new GetAllImage(MainActivity.this).getAllImg());
            }
        });
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> arrImg = new GetAllImage(MainActivity.this).getAllImg();
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),"imgphoto");
//                File file = new File(getExternalFilesDir())
                try {
                    Writer writer  =new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
                    for (int i = 0; i<arrImg.size(); i++){
                        writer.write(arrImg.get(i)+" ");
                    }
                    writer.close();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e("sd",file.getAbsolutePath());
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileReader fileReader = null;
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),"imgphoto");
                StringBuilder stringBuilder = new StringBuilder();
                try {
                    fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line = bufferedReader.readLine();
                    while (line!= null){
                        stringBuilder.append(line);
                        line = bufferedReader.readLine();
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    Log.e("imgtext",stringBuilder.toString());
                }
            }
        });
    }

    private void RequestPermission() {
        PermissionName(Manifest.permission.CAMERA,1);
        PermissionName(Manifest.permission.READ_EXTERNAL_STORAGE,2);
        PermissionName(Manifest.permission.WRITE_EXTERNAL_STORAGE,3);

    }

    private void PermissionName(String name, int requestCode){
        if(ContextCompat.checkSelfPermission(MainActivity.this, name) == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(MainActivity.this, "Succeed "+name, Toast.LENGTH_SHORT).show();
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{name},requestCode);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this, "Succeed Camera", Toast.LENGTH_SHORT).show();
                    PermissionName(Manifest.permission.READ_EXTERNAL_STORAGE,2);
                }else {
                    Toast.makeText(MainActivity.this, "Denied", Toast.LENGTH_SHORT).show();
                    PermissionName(Manifest.permission.READ_EXTERNAL_STORAGE,2);
                }
                break;
            case 2:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this, "Succeed Read", Toast.LENGTH_SHORT).show();
                    PermissionName(Manifest.permission.WRITE_EXTERNAL_STORAGE,3);
                }else {
                    Toast.makeText(MainActivity.this, "Denied", Toast.LENGTH_SHORT).show();
                    PermissionName(Manifest.permission.WRITE_EXTERNAL_STORAGE,3);
                }
                break;
            case 3:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this, "Succeed Write", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}