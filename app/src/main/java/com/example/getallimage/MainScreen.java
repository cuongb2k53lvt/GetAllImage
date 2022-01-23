package com.example.getallimage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.getallimage.Adapter.ViewPagerAdapter;
import com.example.getallimage.Adapter.ViewPagerMainAdapter;
import com.example.getallimage.Fragment.FragmentAllImage;
import com.example.getallimage.Fragment.FragmentFolder;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainScreen extends AppCompatActivity {
    BottomNavigationView bottomNav;
    ViewPager2 vp2;
    FragmentAllImage fragmentAllImage;
    FragmentFolder fragmentFolder;
    int checkFragmentCreated = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        bottomNav = findViewById(R.id.bottom_nav);
        vp2 = findViewById(R.id.vpAllImg);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0F6A93"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //Ẩn navigation
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        //Ẩn status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //tạo viewpager bottom nav
        ViewPagerMainAdapter viewPagerAdapter = new ViewPagerMainAdapter(this);
        fragmentAllImage = viewPagerAdapter.getAllImageFragment();
        fragmentFolder = viewPagerAdapter.getFolderFragment();
        vp2.setAdapter(viewPagerAdapter);
        vp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Menu menu = bottomNav.getMenu();
                switch (position){
                    case 0:
                        menu.getItem(0).setChecked(true);
                        break;
                    case 1:
                        menu.getItem(1).setChecked(true);
                        checkFragmentCreated++;
                        break;
                }
            }
        });
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.allimg_nav:
                        item.setChecked(true);
                        vp2.setCurrentItem(0);
                        break;
                    case R.id.folder_nav:
                        item.setChecked(true);
                        vp2.setCurrentItem(1);
                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.column_a:
                fragmentAllImage.changeColumnNumber(3);
                if(checkFragmentCreated>0){
                    fragmentFolder.changeColumnNumber(3);
                }
                break;
            case R.id.column_b:
                fragmentAllImage.changeColumnNumber(4);
                if(checkFragmentCreated>0){
                    fragmentFolder.changeColumnNumber(4);
                }
                break;
            case R.id.sort_by_name:
                fragmentAllImage.sortByName();
                break;
            case R.id.sort_by_date:
                fragmentAllImage.sortByDateTaken();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}