package com.wu.viewpagerdemo;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout weixinLayout;
    private LinearLayout addressLayout;
    private LinearLayout findLayout;
    private LinearLayout settingLayout;
    private ImageView weixinImg;
    private ImageView addressImg;
    private ImageView findImg;
    private ImageView settingImg;
    private List<View> viewList= new ArrayList<>();
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        event();
    }
    private void init(){
        weixinLayout = (LinearLayout) findViewById(R.id.weixin_tab_layout);
        addressLayout = (LinearLayout) findViewById(R.id.address_tab_layout);
        findLayout = (LinearLayout) findViewById(R.id.find_frd_tab_layout);
        settingLayout = (LinearLayout) findViewById(R.id.settings_tab_layout);
        weixinImg = (ImageView) findViewById(R.id.weixin_tab_img);
        addressImg = (ImageView) findViewById(R.id.address_tab_img);
        findImg = (ImageView) findViewById(R.id.find_frd_tab_img);
        settingImg = (ImageView) findViewById(R.id.settings_tab_img);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        LayoutInflater inflater = LayoutInflater.from(this);
        View tab1 = inflater.inflate(R.layout.tab1, null);
        View tab2 = inflater.inflate(R.layout.tab2, null);
        View tab3 = inflater.inflate(R.layout.tab3, null);
        View tab4 = inflater.inflate(R.layout.tab4, null);
        viewList.add(tab1);
        viewList.add(tab2);
        viewList.add(tab3);
        viewList.add(tab4);
        pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = viewList.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }
        };
        viewPager.setAdapter(pagerAdapter);
    }
    private void event(){
        weixinLayout.setOnClickListener(this);
        addressLayout.setOnClickListener(this);
        findLayout.setOnClickListener(this);
        settingLayout.setOnClickListener(this);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentItem = viewPager.getCurrentItem();
                resetImg();
                switch(currentItem){
                    case 0:
                        weixinImg.setImageResource(R.drawable.tab_weixin_pressed);
                        break;
                    case 1:
                        addressImg.setImageResource(R.drawable.tab_address_pressed);
                        break;
                    case 2:
                        findImg.setImageResource(R.drawable.tab_find_frd_pressed);
                        break;
                    case 3:
                        settingImg.setImageResource(R.drawable.tab_settings_pressed);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void resetImg(){
        weixinImg.setImageResource(R.drawable.tab_weixin_normal);
        addressImg.setImageResource(R.drawable.tab_address_normal);
        findImg.setImageResource(R.drawable.tab_find_frd_normal);
        settingImg.setImageResource(R.drawable.tab_settings_normal);
    }

    @Override
    public void onClick(View v) {
        resetImg();
        switch(v.getId()){
            case R.id.weixin_tab_layout:
                viewPager.setCurrentItem(0);
                weixinImg.setImageResource(R.drawable.tab_weixin_pressed);
                break;
            case R.id.address_tab_layout:
                viewPager.setCurrentItem(1);
                addressImg.setImageResource(R.drawable.tab_address_pressed);
                break;
            case R.id.find_frd_tab_layout:
                viewPager.setCurrentItem(2);
                findImg.setImageResource(R.drawable.tab_find_frd_pressed);
                break;
            case R.id.settings_tab_layout:
                viewPager.setCurrentItem(3);
                settingImg.setImageResource(R.drawable.tab_settings_pressed);
                break;
            default:
                break;
        }
    }
}
