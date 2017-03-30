package example.lxl.myapplication.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import example.lxl.myapplication.bean.StudyBean;

/**
 * Created by lxl on 2017/3/30.
 */

public class StudyAdapter extends BaseQuickAdapter<StudyBean,StudyAdapter.MineViewHolder> {

    public StudyAdapter(int layoutResId, List<StudyBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(MineViewHolder helper, StudyBean item) {

    }


    public class MineViewHolder extends BaseViewHolder{
        public MineViewHolder(View view) {
            super(view);
        }
    }
}
