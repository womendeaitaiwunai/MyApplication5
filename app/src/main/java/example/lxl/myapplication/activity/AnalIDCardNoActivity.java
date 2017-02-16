package example.lxl.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import example.lxl.myapplication.R;
import example.lxl.myapplication.base.BaseActivity;
import example.lxl.myapplication.util.idcard.CameraActivity;

/**
 * Created by lxl on 2017/2/16.
 * 身份证识别算法
 */

public class AnalIDCardNoActivity extends BaseActivity implements View.OnClickListener{
    private TextView result;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_card);
        Log.i("标志：","AnalIDCardNoActivity--》onCreate");
        initView();
    }

    private void initView() {
        result= (TextView) findViewById(R.id.id_card_result);
        findViewById(R.id.start_id_card).setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(this);
    }

    @Override
    protected Activity initActivity(Activity activity) {
        Log.i("标志：","AnalIDCardNoActivity--》initActivity");
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_id_card:
                startActivityForResult(new Intent(AnalIDCardNoActivity.this, CameraActivity.class),0x22);
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x22 && resultCode == RESULT_OK && data != null) {
            String id = data.getStringExtra("id");
            Toast.makeText(this, id, Toast.LENGTH_LONG).show();
            if (id != null && id.length() == 18) {
                result.setText(getString(R.string.id_card_result,id));
            }
        }
    }
}
