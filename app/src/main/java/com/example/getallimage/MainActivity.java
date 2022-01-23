package com.example.getallimage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.getallimage.Adapter.ImageAdapter;
import com.example.getallimage.Adapter.ViewPagerAdapter;
import com.example.getallimage.Class.AlbumItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    Button btnColNumb;
    ImageAdapter imageAdapter;
    ViewPager2 vpImage2;
    ViewPagerAdapter viewPagerAdapter;
    static final String permission = "PERMISSION";
    static final int readRequestCode = 1;
    int numbColumn = 1;
    int checkShowUp = 0;
    PopupWindow popupWindow;
    LayoutAnimationController layoutAnimationController;
    ArrayList<CheckBox> arrCheckBox = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        btnColNumb = findViewById(R.id.btnColNumb);
        vpImage2 = findViewById(R.id.vpImage2);
        //Ẩn action bar
        getSupportActionBar().hide();
        //Ẩn navigation
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        //Ẩn status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //request permissions
        requestPermission();
        //tạo recycler view và adapter
        imageAdapter = new ImageAdapter(MainActivity.this);
        rv.setNestedScrollingEnabled(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,numbColumn);
        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(imageAdapter);
        //set animation và ảnh cho recycler view
        layoutAnimationController = AnimationUtils.loadLayoutAnimation(this,R.anim.layout_animation);
        try {
            imageAdapter.setData(new GetAllImage(MainActivity.this).getAllImg());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Tạo popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        View view = LayoutInflater.from(this).inflate(R.layout.checkbox_column,null);
        popupWindow = new PopupWindow(view,width,height);
        CheckBox cb1 = view.findViewById(R.id.cb1);
        CheckBox cb2 = view.findViewById(R.id.cb2);
        CheckBox cb3 = view.findViewById(R.id.cb3);
        CheckBox cb4 = view.findViewById(R.id.cb4);
        CheckBox cb5 = view.findViewById(R.id.cb5);
        CheckBox cb6 = view.findViewById(R.id.cb6);
        arrCheckBox.add(cb1);
        arrCheckBox.add(cb2);
        arrCheckBox.add(cb3);
        arrCheckBox.add(cb4);
        arrCheckBox.add(cb5);
        arrCheckBox.add(cb6);
        //Tạo viewpager adapter
        viewPagerAdapter = new ViewPagerAdapter(this);
        vpImage2.setAdapter(viewPagerAdapter);
        vpImage2.setPageTransformer(new MyPageTransformer());
        try {
            viewPagerAdapter.setImage(new GetAllImage(this).getAllImg());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        imageAdapter.setOnImageClick(new OnItemClick() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ImgPosition", position);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void requestPermission() {
        permissionName(Manifest.permission.READ_EXTERNAL_STORAGE,readRequestCode);
    }

    private void permissionName(String name, int requestCode){
        //Kiểm tra permissions, nếu chưa đồng ý thì request
        if(ContextCompat.checkSelfPermission(MainActivity.this, name) == PackageManager.PERMISSION_GRANTED){
            Log.e(permission,name+" permission granted");
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{name},requestCode);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Xử lý khi nhận được result từ người dùng
        switch (requestCode){
            case readRequestCode:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.e(permission,"Read external storage permission granted");
                    try {
                        imageAdapter.setData(new GetAllImage(MainActivity.this).getAllImg());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }else {
                    Log.e(permission,"Read external storage permission denied");
                }
                break;
        }
    }

    public void saveToSdCardButton(View view) throws ParseException {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),"imgphoto2.txt");
        saveFile(file);
    }

    public void saveFile(File file) throws ParseException {
        //Lấy uri ảnh
        List<AlbumItem> arrImg = new GetAllImage(MainActivity.this).getAllImg();
        try {
            //Ghi tên ảnh
            Writer writer  =new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
            for (int i = 0; i<arrImg.size(); i++){
                writer.write(arrImg.get(i).getTitle()+" ");
            }
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFileButton(View view) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),"imgphoto2.txt");
        readFile(file);
    }

    public void readFile(File file){
        FileReader fileReader = null;
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

    public void selectColumnNumber(View view) {
        popupWindow.setAnimationStyle(R.style.SelectColumnAnimation);
        popupWindow.showAtLocation(btnColNumb, Gravity.TOP,0,0);
        if(checkShowUp == 1){
            popupWindow.dismiss();
            checkShowUp = 0;
        }else {
            checkShowUp++;
        }
    }

    public void changeColumnNumber(View view) {
        // check tất cả check box đến vị trí click và bỏ check tất cả check box phía sau
        for (int i = 0; i < arrCheckBox.size(); i++){
            arrCheckBox.get(i).setChecked(true);
            if(view == arrCheckBox.get(i)){
                //set số cột hiển thị
                numbColumn = i+1;
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rv_anim);
                rv.setAnimation(animation);
                imageAdapter.lastPosition = -1;// reset last position
                rv.setLayoutAnimation(layoutAnimationController);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,numbColumn);
                rv.setLayoutManager(gridLayoutManager);
                for (int j = i+1; j < arrCheckBox.size(); j++ ){
                    arrCheckBox.get(j).setChecked(false);
                }
                break;
            }
        }
    }
}