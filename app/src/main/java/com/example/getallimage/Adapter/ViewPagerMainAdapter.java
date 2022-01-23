package com.example.getallimage.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.getallimage.Fragment.FragmentAllImage;
import com.example.getallimage.Fragment.FragmentFolder;

import java.util.ArrayList;

public class ViewPagerMainAdapter extends FragmentStateAdapter {
    ArrayList<Fragment> arrFragment = new ArrayList<>();
    FragmentAllImage fragmentAllImage = new FragmentAllImage();
    FragmentFolder fragmentFolder = new FragmentFolder();
    public ViewPagerMainAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        arrFragment.add(fragmentAllImage);
        arrFragment.add(fragmentFolder);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return arrFragment.get(position);
    }

    @Override
    public int getItemCount() {
        return arrFragment.size();
    }

    public FragmentAllImage getAllImageFragment() {
        return fragmentAllImage;
    }
    public FragmentFolder getFolderFragment(){
        return fragmentFolder;
    }
}
