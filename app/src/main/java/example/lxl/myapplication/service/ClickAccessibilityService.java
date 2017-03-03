package example.lxl.myapplication.service;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by lxl on 2017/2/7.
 */

public class ClickAccessibilityService extends BaseAccessibilityService {
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        super.onAccessibilityEvent(event);
        Toast.makeText(this, "进入了判断额", Toast.LENGTH_SHORT).show();

        if (!checkIsRun()){
            try{
                Toast.makeText(this, "准确启动", Toast.LENGTH_SHORT).show();
                Intent intent = this.getPackageManager().getLaunchIntentForPackage("example.lxl.myapplication.MainActivity");
                startActivity(intent);
            }catch(Exception e){
                Toast.makeText(this, "没有安装", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "正在运行额", Toast.LENGTH_SHORT).show();
        }
//        if (event.getEventType()==AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED
//                && event.getText().size()!=0&&event.getText().get(0).equals("点击了")){
//            //event.setAction(GLOBAL_ACTION_HOME);
//            //execShellCmd("input keyevent 3");//home
//
////            Intent i= new Intent(Intent.ACTION_MAIN);
////            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////            i.addCategory(Intent.CATEGORY_HOME);
////            startActivity(i);
//        }
    }

    private boolean checkIsRun(){
        ActivityManager mActivityManager = (ActivityManager)
                this
                        .getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo>
                mRunningProcess = mActivityManager
                .getRunningAppProcesses();

        for (ActivityManager.RunningAppProcessInfo amProcess : mRunningProcess) {
            Log.i("ClickAccess",amProcess.processName+"");
            if (amProcess.processName.equals("example.lxl.myapplication"))
            return true;
        }
        return false;
    }
}
