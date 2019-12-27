package com.example.threadpoolexectordemo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.threadpoolexectordemo.downloaded.view.DownloadedFragment;
import com.example.threadpoolexectordemo.filedownloading.view.FileDownloadingFragment;
import com.example.threadpoolexectordemo.model.FileDetails;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FileDownloadingFragment.SendCompletedFileDetails {

    private TabLayout tabLayout;

    private ViewPager viewPager;
    private DownloadedFragment downloadedFragment = new DownloadedFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        viewPagerAdapter viewPagerAdapter = new viewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new FileDownloadingFragment(), "Downloading");
        viewPagerAdapter.addFragment(downloadedFragment, "Downloaded");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    private void initView() {
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
    }

    @Override
    public void sendList(List<FileDetails> fileDetails) {
        downloadedFragment.receivedData(fileDetails);
    }

    private class viewPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> fragmentList = new ArrayList<>();
        List<String> title = new ArrayList<>();

        public viewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            this.title.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }
    }

}
