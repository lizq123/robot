package com.lizq.robot;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.RequiresApi;

public class MyService extends AccessibilityService {
    public static final String TAG = "--MyService--";

    private GestureDescription gestureDescription;

    public MyService() {
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        String packageName = event.getPackageName().toString();
        String className = event.getClassName().toString();
        Log.d(TAG, "----1-----" + packageName);
        Log.d(TAG, "----2-----" + className);
        if (AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED == eventType) {
            //被监听的界面
            Log.d(TAG, "----2-----1111111111111111111");
            if ("com.ss.android.ugc.aweme.main.MainActivity".equals(className)) {
                Log.d(TAG, "----2-----222222222222222222");
                for (; ; ) {
                    try {
                        Log.d(TAG, "----2-----44444444444444444");
                        AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
                        MyGesture();
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Log.d(TAG, "----2-----3333333333");
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    class MyCallBack extends GestureResultCallback {

        public MyCallBack() {
            super();
        }

        @Override
        public void onCompleted(GestureDescription gestureDescription) {
            super.onCompleted(gestureDescription);
            Log.d(TAG, "----2-----56565656");
        }

        @Override
        public void onCancelled(GestureDescription gestureDescription) {
            super.onCancelled(gestureDescription);
            Log.d(TAG, "----2-----777888");
        }
    }

    private void MyGesture() {//仿滑动
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Path path = new Path();
            path.moveTo(500, 1287);//设置Path的起点
            path.quadTo(450, 1036, 90, 864);
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "----2-----555555555555555");
            GestureDescription.Builder builder = new GestureDescription.Builder();
            GestureDescription description = builder.addStroke(new GestureDescription.StrokeDescription(path, 500L, 37L)).build();
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //100L 第一个是开始的时间，第二个是持续时间
            dispatchGesture(description, new MyCallBack(), null);
        }
    }

    @Override
    public void onInterrupt() {
        Log.d(TAG, "----onInterrupt-----");
        Log.d(TAG, "----2-----333333333111113");
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.d(TAG, "----onServiceConnected-----");

    }
}