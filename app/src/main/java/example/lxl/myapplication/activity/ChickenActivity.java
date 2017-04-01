package example.lxl.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.yanzhenjie.album.Album;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import example.lxl.myapplication.R;
import example.lxl.myapplication.base.BaseActivity;
import example.lxl.myapplication.fragment.CommonFragment;
import example.lxl.myapplication.widget.CustPagerTransformerViewPage;


/**
 * Created by xmuSistone on 2016/9/18.
 */
public class ChickenActivity extends BaseActivity implements View.OnClickListener{

    private TextView indicatorTv;
    private View positionView;
    private ViewPager viewPager;
    private List<CommonFragment> fragments = new ArrayList<>(); // 供ViewPager使用
    private LinearLayout selectView;
    ArrayList<String> imageArray;
    //private final String[] imageArray = {"assets://image1.jpg", "assets://image2.jpg", "assets://image3.jpg", "assets://image4.jpg", "assets://image5.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicken);

        // 1. 沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(Color.TRANSPARENT);
                getWindow()
                        .getDecorView()
                        .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            } else {
                getWindow()
                        .setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
        positionView = findViewById(R.id.position_view);
        findViewById(R.id.chicken_back).setOnClickListener(this);
        dealStatusBar(); // 调整状态栏高度


        selectView= (LinearLayout) findViewById(R.id.select_view);

        findViewById(R.id.xuantu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Album.album(ChickenActivity.this)
                        .requestCode(999) // 请求码，返回时onActivityResult()的第一个参数。
//                        .toolBarColor(R.color.colorPrimary) // Toolbar 颜色，默认蓝色。
//                        .statusBarColor(R.color.colorAccent) // StatusBar 颜色，默认蓝色。
//                        .navigationBarColor(R.color.colorPrimary) // NavigationBar 颜色，默认黑色，建议使用默认。
                        .title("图库") // 配置title。

                        .selectCount(9) // 最多选择几张图片。
                        .columnCount(2) // 相册展示列数，默认是2列。
                        .camera(true) // 是否有拍照功能。
                        .checkedList(imageArray) // 已经选择过得图片，相册会自动选中选过的图片，并计数。
                        .start();
            }
        });

        findViewById(R.id.hualang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Album.gallery(ChickenActivity.this)
                        .requestCode(666) // 请求码，返回时onActivityResult()的第一个参数。
//                        .toolBarColor(android.R.color.holo_red_dark) // Toolbar 颜色，默认蓝色。
//                        .statusBarColor(android.R.color.holo_red_dark) // StatusBar 颜色，默认蓝色。
//                        .navigationBarColor(android.R.color.holo_red_dark) // NavigationBar 颜色，默认黑色，建议使用默认。

                        .checkedList(imageArray) // 要预览的图片list。
                        .currentPosition(1) // 预览的时候要显示list中的图片的index。
                        .checkFunction(true) // 预览时是否有反选功能。
                        .start();
            }
        });


        findViewById(R.id.rouji).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectView.setVisibility(View.GONE);
                // 2. 初始化ImageLoader
                initImageLoader();
                // 3. 填充ViewPager
                fillViewPager();
            }
        });


    }

    @Override
    protected Activity initActivity() {
        return this;
    }

    /**
     * 填充ViewPager
     */
    private void fillViewPager() {
        indicatorTv = (TextView) findViewById(R.id.indicator_tv);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        // 1. viewPager添加parallax效果，使用PageTransformer就足够了
        viewPager.setPageTransformer(false, new CustPagerTransformerViewPage(this));

        for (String imgUrl:imageArray){
            Log.i("选择图片的地址",imgUrl);
        }
        // 2. viewPager添加adapter
        for (int i = 0; i < 10; i++) {
            // 预先准备10个fragment
            fragments.add(new CommonFragment());
        }

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                CommonFragment fragment = fragments.get(position % 10);
                fragment.bindData(imageArray.get(position % imageArray.size()));
                return fragment;
            }

            @Override
            public int getCount() {
                return 666;
            }
        });


        // 3. viewPager滑动时，调整指示器
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                updateIndicatorTv();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        updateIndicatorTv();
    }

    /**
     * 更新指示器
     */
    private void updateIndicatorTv() {
        int totalNum = viewPager.getAdapter().getCount();
        int currentItem = viewPager.getCurrentItem() + 1;
        indicatorTv.setText(Html.fromHtml("<font color='#12edf0'>" + currentItem + "</font>  /  " + totalNum));
    }

    /**
     * 调整沉浸式菜单的title
     */
    private void dealStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int statusBarHeight = getStatusBarHeight();
            ViewGroup.LayoutParams lp = positionView.getLayoutParams();
            lp.height = statusBarHeight;
            positionView.setLayoutParams(lp);
        }
    }

    private int getStatusBarHeight() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    @SuppressWarnings("deprecation")
    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this)
                .memoryCacheExtraOptions(480, 800)
                // default = device screen dimensions
                .threadPoolSize(3)
                // default
                .threadPriority(Thread.NORM_PRIORITY - 1)
                // default
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024).memoryCacheSizePercentage(13) // default
                .discCacheSize(50 * 1024 * 1024) // 缓冲大小
                .discCacheFileCount(100) // 缓冲文件数目
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(this)) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs().build();

        // 2.单例ImageLoader类的初始化
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 999||requestCode==666) {
            if (resultCode == RESULT_OK) { // Successfully.
                // 不要质疑你的眼睛，就是这么简单。
                imageArray = Album.parseResult(data);
            } else if (resultCode == RESULT_CANCELED) { // User canceled.
                // 用户取消了操作。
                Toast.makeText(this, "点击了取消额", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chicken_back:
                finish();
                break;
        }
    }
}
