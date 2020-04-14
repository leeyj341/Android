package multi.android.support_lib.viewpager.exam;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import multi.android.support_lib.R;

public class ExamFragmentActivity extends AppCompatActivity {
    ViewFragment1 viewFragment1 = new ViewFragment1();
    ListFragmentTest viewFragment2 = new ListFragmentTest();
    ViewFragment3 viewFragment3 = new ViewFragment3();
    ViewFragment4 viewFragment4 = new ViewFragment4();

    ArrayList<Fragment> fragmentlist = new ArrayList<Fragment>();
    ViewPager mainPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_exam);
        mainPager = findViewById(R.id.examMainPager);

        fragmentlist.add(viewFragment1);
        fragmentlist.add(viewFragment2);
        fragmentlist.add(viewFragment3);
        fragmentlist.add(viewFragment4);

        MainFragmentPagerAdapter adapter = new MainFragmentPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mainPager.setAdapter(adapter);

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
            return fragmentlist.get(position);
        }

        @Override
        public int getCount() {
            return fragmentlist.size();
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
