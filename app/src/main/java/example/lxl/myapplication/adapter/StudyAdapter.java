package example.lxl.myapplication.adapter;

import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import javax.sql.DataSource;

import example.lxl.myapplication.R;
import example.lxl.myapplication.bean.StudyBean;

/**
 * Created by lxl on 2017/3/30.
 */

public class StudyAdapter extends BaseQuickAdapter<StudyBean,BaseViewHolder> {

    public StudyAdapter(List<StudyBean> data) {
        super(R.layout.adapter_study,data);
    }


    @Override
    protected void convert(BaseViewHolder helper, StudyBean item) {
        helper.setText(R.id.title,item.getTitleName())
                .setText(R.id.introduce,item.getIntroduce());
    }
}
