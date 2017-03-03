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
    private HashMap<String,SoftReference<Activity>> myActivityList=new HashMap<>();
    public String TAG;

    protected abstract Activity initActivity();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        Log.i("创建：","BaseActivity--》onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        TAG=initActivity().getComponentName().getShortClassName();
        Activity activity=initActivity();
        SoftReference<Activity> activitySoftReference=new SoftReference<>(activity);
        myActivityList.put(activity.getLocalClassName(),activitySoftReference);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

//    protected List<Activity> getMineActivityList(){
//        List<Activity> activities=new ArrayList<>();
//        for (int i = 0; i < myActivityList.size(); i++) {
//
//        }
//    }

    protected Activity getActivity(String activityName){
        if (myActivityList.get(activityName)!=null)
        return myActivityList.get(activityName).get();
        else return null;
    }

}
