package example.lxl.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;


import example.lxl.myapplication.R;
import example.lxl.myapplication.base.BaseActivity;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lxl on 2017/2/21.
 */

public class StudyRxJavaActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_rx);
        Observer<String> observer=new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                if (value.equals("呵呵")){
                    Toast.makeText(StudyRxJavaActivity.this, "是呵呵额", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(StudyRxJavaActivity.this, "是呵呵哒额", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(StudyRxJavaActivity.this, "执行错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                Toast.makeText(StudyRxJavaActivity.this, "都执行完成了", Toast.LENGTH_SHORT).show();
            }
        };


        io.reactivex.Observable<String> observable=new io.reactivex.Observable<String>() {
            @Override
            protected void subscribeActual(Observer<? super String> observer) {
                observer.onNext("呵呵");
                observer.onNext("呵呵哒");
                observer.onComplete();
            }
        };

        observable.safeSubscribe(observer);
    }

    @Override
    protected Activity initActivity() {
        return this;
    }

}
