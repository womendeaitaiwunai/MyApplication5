package example.lxl.myapplication.activity;

import android.animation.TimeAnimator;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.flurgle.camerakit.CameraListener;
import com.flurgle.camerakit.CameraView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import example.lxl.myapplication.R;
import example.lxl.myapplication.base.BaseActivity;

/**
 * Created by lxl on 2017/4/5.
 * 拍照页面
 */

public class CameraActivity extends BaseActivity  implements View.OnClickListener{
    private CameraView cameraView;

    @Override
    protected Activity initActivity() {
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        cameraView= (CameraView) findViewById(R.id.camera);
        final ImageView photoView= (ImageView) findViewById(R.id.photo);
        findViewById(R.id.paizhao).setOnClickListener(this);
        findViewById(R.id.shexiangtou).setOnClickListener(this);

        cameraView.setCameraListener(new CameraListener() {
            @Override
            public void onCameraOpened() {
                super.onCameraOpened();
            }

            @Override
            public void onCameraClosed() {
                super.onCameraClosed();
            }

            @Override
            public void onPictureTaken(byte[] jpeg) {
                super.onPictureTaken(jpeg);
                photoView.setImageBitmap(BitmapFactory.decodeByteArray(jpeg,0,jpeg.length));
                String path=getSDCardPath();
                saveBitmap(path,jpeg);
            }

        });
//        cameraView.captureImage();
    }

    public static String getSDCardPath() {
        return new StringBuilder(String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath()))
                .append(File.separator).toString();
    }

    private void saveBitmap(String path,byte[] byteArray){
        File dir = new File(path);
        if (!dir.exists()){
            dir.mkdirs();
        }
        File photoFile = new File(path , "mine22.jpg");
        FileOutputStream fileOutputStream ;
        BufferedOutputStream bStream = null;
        try {
            fileOutputStream = new FileOutputStream(photoFile);
            bStream = new BufferedOutputStream(fileOutputStream);
            bStream.write(byteArray);
        } catch (Exception e) {
            photoFile.delete();
            e.printStackTrace();
        } finally{
            try {
                bStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    protected void onPause() {
        cameraView.stop();
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.paizhao:
                cameraView.setJpegQuality(100);
                cameraView.captureImage();
                break;
            case R.id.shexiangtou:
                break;

        }
    }
}
