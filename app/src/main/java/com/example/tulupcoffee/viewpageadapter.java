package com.example.tulupcoffee;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class viewpageadapter extends FragmentPagerAdapter {

    private final ArrayList<Fragment> fragmentlist= new ArrayList<>();
    private final ArrayList<String> fragmenttitle= new ArrayList<>();

    public viewpageadapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        return fragmentlist.get(position);
    }

    @Override
    public int getCount() {
        return fragmentlist.size();
    }

    public void addFragment(Fragment fragment , String title){
        fragmentlist.add(fragment);
        fragmenttitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmenttitle.get(position);
    }
}
