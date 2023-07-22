package com.example.myapplication;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

public class ChatActivity extends AppCompatActivity {

    TabLayout tabLayout;
    FrameLayout frameLayout;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getSupportActionBar().hide();
        tabLayout = findViewById(R.id.chat_tab);
        frameLayout = findViewById(R.id.frameLayout);

//        tabLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                tabLayout.setupWithViewPager(viewPager);
//            }
//        });
//
//        FragmentManager manager = getSupportFragmentManager();
//        manager.beginTransaction().replace(R.id.chat_pager,new ChatFragment()).commit();
//
//        viewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager());
//
//    }
//
//    private class TabPagerAdapter extends FragmentPagerAdapter {
//        public TabPagerAdapter(FragmentManager supportFragmentManager) {
//            super(supportFragmentManager);
//        }
//
//        @Nullable
//        @Override
//        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "Chat";
//                case 1:
//                    return "Status";
//                case 2:
//                    return "Call";
//                default:
//                    return "Other";
//           }
////            return super.getPageTitle(position);
//        }
//
//        @NonNull
//        @Override
//        public Fragment getItem(int position) {
//            switch (position) {
//                case 0:
//                    return new ChatFragment();
//                case 1:
//                    return new StatusFragment();
//                case 2:
//                    return new CallFragment();
//                default:
//                    return new ChatFragment();
//            }
//            //return null;
//        }
//
//        @Override
//        public int getCount() {
//            return 4;
//        }
//    }
//}
















//        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
//        frameLayout=(FrameLayout)findViewById(R.id.frameLayout);

        fragment = new ChatFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new ChatFragment();
                        break;
                    case 1:
                        fragment = new StatusFragment();
                        break;
                    case 2:
                        fragment = new CallFragment();
                        break;
                    default:
                        fragment = new ChatFragment();
                        break;
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameLayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}