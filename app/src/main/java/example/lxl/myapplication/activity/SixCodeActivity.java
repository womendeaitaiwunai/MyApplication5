package example.lxl.myapplication.activity;

import android.animation.TimeAnimator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import example.lxl.myapplication.base.BaseActivity;

/**
 * Created by lxl on 2017/2/21.
 */

public class SixCodeActivity extends BaseActivity {
    @Override
    protected Activity initActivity() {
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TimeAnimator timeAnimator=new TimeAnimator();
        timeAnimator.setTimeListener(new TimeAnimator.TimeListener() {
            @Override
            public void onTimeUpdate(TimeAnimator animation, long totalTime, long deltaTime) {
                Log.i("得到的两个",totalTime+"--->"+deltaTime);
                if (totalTime>=500){
                    animation.cancel();
                }
            }
        });
        timeAnimator.setCurrentPlayTime(1);
        timeAnimator.start();
    }
}
