package example.lxl.myapplication.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by lxl on 2017/3/8.
 */

public class ClockClickAccessibilityService extends BaseAccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        super.onAccessibilityEvent(event);
//        if (event.getEventType()==AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED&&
//                event.getText().get(0).equals("闹钟")){}
        if (event.getEventType()==AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
                &&event.getClassName().equals("android.widget.FrameLayout")
                &&event.getText().get(0).equals("12:01")){
//            Intent intent=new Intent();
//            intent.setComponent(new ComponentName("com.alibaba.android.rimet", "com.alibaba.android.rimet.biz.im.activities.AlbumActivity"));
//            startActivity(new Intent("com.alibaba.android.rimet.biz.im.activities.AlbumActivity"));

            PackageManager packageManager =this.getPackageManager();
            Intent it= packageManager.getLaunchIntentForPackage("com.alibaba.android.rimet");
            startActivity(it);
        }
    }
}
