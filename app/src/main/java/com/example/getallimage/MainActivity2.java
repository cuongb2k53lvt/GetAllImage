package com.example.getallimage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.getallimage.Adapter.ImageAdapter;
import com.example.getallimage.Adapter.ViewPagerAdapter;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    ViewPager2 vpImage;
    ViewPagerAdapter viewPagerAdapter;
    static int imgPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        vpImage = findViewById(R.id.vpImage);
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
        //set adapter vp, hiển thị và lấy index ảnh
        List<String> arrImg = new GetAllImage(this).getAllImg();
        imgPosition = getIntent().getExtras().getInt("ImgPosition");
        viewPagerAdapter = new ViewPagerAdapter(this);
        vpImage.setAdapter(viewPagerAdapter);
        vpImage.setPageTransformer(new MyPageTransformer());
        viewPagerAdapter.setImage(arrImg);
        vpImage.setCurrentItem(imgPosition);
        Log.e("BUG","1");
    }

}