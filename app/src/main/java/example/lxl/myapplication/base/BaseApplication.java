package example.lxl.myapplication.base;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import example.lxl.myapplication.R;
import example.lxl.myapplication.util.Cockroach;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by lxl on 2016/10/28.
 */

public class BaseApplication extends Application implements Application.ActivityLifecycleCallbacks{
    static List<Activity> activityLists=new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath( "fonts/Roboto-Black.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        Cockroach.install(new Cockroach.ExceptionHandler() {
            @Override
            public void handlerException(final Thread thread, final Throwable throwable) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Log.d("Cockroach", thread + "\n" + throwable.toString());
                            throwable.printStackTrace();
                            Toast.makeText(BaseApplication.this, "代码出现异常", Toast.LENGTH_SHORT).show();
                            //Toast.makeText(BaseApplication.this, "Exception Happend\n" + thread + "\n" + throwable.toString(), Toast.LENGTH_SHORT).show();
                        } catch (Throwable e) {

                        }
                    }
                });
            }
        });
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.i("创建","BaseApplication-->onActivityCreated");
        activityLists.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activityLists.remove(activity);
    }
    public static List<Activity> getActivityLists(){
        return activityLists;
    }
}
