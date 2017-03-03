package example.lxl.myapplication.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by lxl on 2016/10/28.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public String TAG;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        Log.i("创建：","BaseActivity--》onCreate");
        TAG=initActivity().getComponentName().getShortClassName();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected abstract Activity initActivity();
}
