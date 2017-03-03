package example.lxl.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import example.lxl.myapplication.base.BaseActivity;
import example.lxl.myapplication.util.anima.CookieThumperSample;
import example.lxl.myapplication.util.AgainTimerTask;
import su.levenetc.android.textsurface.Debug;
import su.levenetc.android.textsurface.TextSurface;

/**
 * Created by lxl on 2016/10/27.
 */

public class WelcomeActivity extends BaseActivity {
    private TextSurface textSurface;
    private TextView go;
    int time =0;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_surf);
        Log.i("创建","WelcomeActivity-->onCreate");
        textSurface = (TextSurface) findViewById(R.id.text_surface);
        go= (TextView) findViewById(R.id.go);

        textSurface.postDelayed(new Runnable() {
            @Override public void run() {
                show();
            }
        }, 1000);

        findViewById(R.id.btn_refresh).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                show();
            }
        });

        CheckBox checkDebug = (CheckBox) findViewById(R.id.check_debug);
        checkDebug.setChecked(Debug.ENABLED);
        checkDebug.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Debug.ENABLED = isChecked;
                textSurface.invalidate();
            }
        });

        final AgainTimerTask timerTask=new AgainTimerTask(1000).setTimerTask(new AgainTimerTask.TimeTaskResult() {
            @Override
            public void run() {
                time=time+1;
                if (time>10){
                    WelcomeActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            go.setVisibility(View.VISIBLE);
                        }
                    });
                }
            }
        });
        timerTask.startTimer();

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerTask.stopTimer();
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                finish();
            }
        });

    }

    @Override
    protected Activity initActivity() {
        return this;
    }

    private void show() {
        textSurface.reset();
        CookieThumperSample.play(textSurface,getAssets());
    }
}
