package example.lxl.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import example.lxl.myapplication.R;
import example.lxl.myapplication.adapter.StudyAdapter;
import example.lxl.myapplication.base.BaseActivity;
import example.lxl.myapplication.bean.StudyBean;

/**
 * Created by lxl on 2017/3/29.
 */

public class MineStudyActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private List<StudyBean>  beanList=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_study);
        recyclerView= (RecyclerView) findViewById(R.id.recycle);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        initData();
        final StudyAdapter studyAdapter=new StudyAdapter(beanList);
        studyAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        studyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position==0) startActivityByIntent(MineStudyActivity.this,CameraActivity.class,false);
            }
        });
        studyAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Toast.makeText(MineStudyActivity.this, "准备加载更多", Toast.LENGTH_SHORT).show();
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        studyAdapter.addData(beanList);
                        studyAdapter.loadMoreComplete();
                    }
                },2000);

            }
        },recyclerView);
        recyclerView.setAdapter(studyAdapter);
    }

    @Override
    protected Activity initActivity() {
        return this;
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 10; i++) {
            StudyBean studyBean=new StudyBean();
            studyBean.setTitleName("目录"+i);
            studyBean.setIntroduce("介绍"+i);
            beanList.add(studyBean);
        }
    }
}
