package example.lxl.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import example.lxl.myapplication.R;
import example.lxl.myapplication.bean.StudyBean;

/**
 * Created by lxl on 2017/3/30.
 */

public class TestRecycleView extends RecyclerView.Adapter<TestRecycleView.MineViewHolder> {
    private List<StudyBean> studyBeenList=new ArrayList<>();
    public TestRecycleView(List<StudyBean> studyBeenList){
        this.studyBeenList=studyBeenList;
    }

    @Override
    public TestRecycleView.MineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_study,parent,false);
        return new MineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TestRecycleView.MineViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return studyBeenList.size();
    }

    public class MineViewHolder extends RecyclerView.ViewHolder{
        public MineViewHolder(View itemView) {
            super(itemView);
        }
    }
}
