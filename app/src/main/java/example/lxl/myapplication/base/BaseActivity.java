package example.lxl.myapplication.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.WeakHashMap;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by lxl on 2016/10/28.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    public String TAG;

    protected abstract Activity initActivity();
    //初始化视图
    protected void initView() {}
    //初始化数据
    protected void initData(){}
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
        initData();
        TAG=initActivity().getComponentName().getShortClassName();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    private void showProDialog(String message){
        if (progressDialog==null){
            progressDialog=new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage(message);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }else if (!progressDialog.isShowing()){
            progressDialog.setMessage(message);
            progressDialog.show();
        }else {
            progressDialog.setMessage(message);
        }
    }


    /**
     *  吐司的方法
     * @param toastMsg StringId
     */
    public void showToast(@StringRes int toastMsg){
        showToast(getString(toastMsg));
    }

    /**
     *  吐司的方法
     * @param toastMsg ""
     */
    public void showToast(String toastMsg){
        Toast.makeText(this, toastMsg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 根据取控件
     * @param viewId 控件ID
     * @param <view> 获取的View
     * @return
     */
    public <view extends View> view getViewById(@IdRes int  viewId){
        return (view)findViewById(viewId);
    }

    /**
     *  跳转页面
     * @param context 要跳转的页面的上下文
     * @param cls 跳转的结果页面
     * @param isFinish 是否结束跳转页面
     */
    public void startActivityByIntent(Context context,Class<?> cls,boolean isFinish){
        startActivityByIntent(context,cls,isFinish,android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    /**
     *  跳转页面
     * @param context 要跳转的页面的上下文
     * @param cls 跳转的结果页面
     * @param isFinish 是否结束跳转页面
     * @param startAnim 开始动画
     * @param stopAnim 结束动画
     */
    public void startActivityByIntent(Context context, Class<?> cls, boolean isFinish, @AnimRes int startAnim,@AnimRes int stopAnim){
        startActivity(new Intent(context, cls));
        if (isFinish) {
            finish();
        }
        overridePendingTransition(startAnim, stopAnim);
    }

}
