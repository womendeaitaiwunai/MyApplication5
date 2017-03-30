package example.lxl.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import example.lxl.myapplication.R;
import example.lxl.myapplication.base.BaseActivity;
import example.lxl.myapplication.service.BaseAccessibilityService;

/**
 * Created by lxl on 2017/3/8.
 * 自动打卡
 */

public class AutoClickDingDingActivity extends BaseActivity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_ding);

        BaseAccessibilityService.getInstance().init(AutoClickDingDingActivity.this);
        findViewById(R.id.setting).setOnClickListener(this);
    }

    @Override
    protected Activity initActivity() {
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting:
                BaseAccessibilityService.getInstance().goAccess();
                break;
        }
    }
}
