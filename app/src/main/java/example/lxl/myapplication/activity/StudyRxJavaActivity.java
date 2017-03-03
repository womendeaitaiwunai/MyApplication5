package example.lxl.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import example.lxl.myapplication.R;
import example.lxl.myapplication.base.BaseActivity;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

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
            public void onCompleted() {
                Toast.makeText(StudyRxJavaActivity.this, "错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };

        Observable<String> observable=Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("呵呵呵");
                subscriber.onCompleted();
            }
        });

        observable.subscribe(observer);
    }

    @Override
    protected Activity initActivity() {
        return this;
    }

}
