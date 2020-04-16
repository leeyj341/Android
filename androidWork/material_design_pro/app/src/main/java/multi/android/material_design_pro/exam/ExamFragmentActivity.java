package multi.android.material_design_pro.exam;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import multi.android.material_design_pro.R;

public class ExamFragmentActivity extends AppCompatActivity {
    Fragment[] arrFragment = {new ViewFragment1(), new ViewFragment2(), new ViewFragment3()};
    String[] arrTitle = {"첫번째 뷰", "두번째 뷰", "세번째 뷰"};
    ViewPager mainPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_exam);
        mainPager = findViewById(R.id.examMainPager);
        tabLayout = findViewById(R.id.examTabs);
        tabLayout.setTabTextColors(Color.LTGRAY, Color.DKGRAY);

        MainFragmentPagerAdapter adapter = new MainFragmentPagerAdapter(getSupportFragmentManager(),
                arrFragment.length);
        mainPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(mainPager);

        PageListener pageListener = new PageListener();
        mainPager.addOnPageChangeListener(pageListener);
    }

    public void btn_click(View v) {
        mainPager.setCurrentItem(Integer.parseInt(v.getTag().toString()));
    }

    class MainFragmentPagerAdapter extends FragmentPagerAdapter {
        public MainFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return arrFragment[position];
        }

        @Override
        public int getCount() {
            return arrFragment.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return arrTitle[position];
        }
    }

    class PageListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        //페이지가 변경되었을 때
        @Override
        public void onPageSelected(int position) {
            Toast.makeText(ExamFragmentActivity.this,
                    "페이지가 전환되었습니다", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
