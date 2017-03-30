package example.lxl.myapplication.service;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by lxl on 2017/3/8.
 */

public class ClickDingClockAccessibility extends BaseAccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        super.onAccessibilityEvent(event);
        if (event.getEventType()==AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED){

        }

    }
}
