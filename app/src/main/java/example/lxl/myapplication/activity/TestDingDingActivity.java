package example.lxl.myapplication.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

import example.lxl.myapplication.R;
import example.lxl.myapplication.base.BaseActivity;

/**
 * Created by lxl on 2017/3/8.
 */

public class TestDingDingActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected Activity initActivity() {
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        findViewById(R.id.test).setOnClickListener(this);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {

                Looper.prepare();
                Message message=Message.obtain();
                message.getCallback();

                Handler handler=new Handler(){

                    @Override
                    public void dispatchMessage(Message msg) {
                        super.dispatchMessage(msg);
                    }

                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                    }
                };
                handler.sendMessage(message);
                Looper.myLooper();
            }
        }).start();

        Handler handler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                return false;
            }
        });
        handler.post(new Runnable() {
            @Override
            public void run() {

            }
        });

        new View(this).post(new Runnable() {
            @Override
            public void run() {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.test:
                PackageManager packageManager =this.getPackageManager();
                Intent it= packageManager.getLaunchIntentForPackage("com.alibaba.android.rimet");
                startActivity(it);
                break;
        }
    }
}
