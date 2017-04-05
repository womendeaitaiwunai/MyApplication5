package example.lxl.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import example.lxl.myapplication.R;
import example.lxl.myapplication.base.BaseActivity;

/**
 * Created by lxl on 2017/2/21.
 * 自己学习的页面
 */

public class StudyActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_rx);
    }

    @Override
    protected Activity initActivity() {
        return this;
    }

}
