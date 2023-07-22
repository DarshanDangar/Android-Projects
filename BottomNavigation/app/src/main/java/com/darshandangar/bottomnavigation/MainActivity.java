package com.darshandangar.bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bnview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnview =findViewById(R.id.bnview);

        bnview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if (id == R.id.navHome) {
                    loadFrag(new HomeFragment(),false);

                } else if (id == R.id.navSearch) {
                    loadFrag(new SearchFragment(),false);

                } else if (id == R.id.navUtilities) {
                    loadFrag(new HomeFragment(),false);

                } else if (id == R.id.navContact) {
                    loadFrag(new contactFragment(),false);

                } else {
                    loadFrag(new MyProfileFragment(),true);
                }

                return true;
            }
        });

        bnview.setSelectedItemId(R.id.navMyProfile);

    }
    public void loadFrag(Fragment fragment,boolean flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (flag)
            ft.add(R.id.container1,fragment);
        else
            ft.replace(R.id.container1,fragment);
        ft.commit();
    }
}