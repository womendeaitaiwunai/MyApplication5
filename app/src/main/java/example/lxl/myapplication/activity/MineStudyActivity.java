package example.lxl.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import example.lxl.myapplication.R;
import example.lxl.myapplication.adapter.TestRecycleView;
import example.lxl.myapplication.base.BaseActivity;
import example.lxl.myapplication.bean.StudyBean;

/**
 * Created by lxl on 2017/3/29.
 */

public class MineStudyActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private TestRecycleView testRecycleView;
    private List<StudyBean> beanList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_study);
        recyclerView= (RecyclerView) findViewById(R.id.recycle);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        initData();
        testRecycleView=new TestRecycleView(beanList);
        recyclerView.setAdapter(testRecycleView);
    }

    @Override
    protected Activity initActivity() {
        return this;
    }

    @Override
    protected void initData() {
        super.initData();
        beanList=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            StudyBean studyBean=new StudyBean();
            studyBean.setTitleName("目录"+i);
            studyBean.setIntroduce("介绍"+i);
            beanList.add(studyBean);
        }

    }
}
