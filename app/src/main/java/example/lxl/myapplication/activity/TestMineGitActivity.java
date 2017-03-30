package example.lxl.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import example.lxl.myapplication.base.BaseActivity;

/**
 * Created by lxl on 2017/3/13.
 */

public class TestMineGitActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Activity initActivity() {
        return this;
    }
}
