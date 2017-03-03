package example.lxl.myapplication.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import example.lxl.myapplication.R;

/**
 * Created by lxl on 2017/3/3.
 */

public class TitleView extends LinearLayout implements View.OnClickListener{
    private TextView titleName;
    public TitleView(Context context) {
        this(context,null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }
    private void initView(Context context,AttributeSet attrs) {
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.TitleView);
        String titleName=typedArray.getString(R.styleable.TitleView_titleName);
        View view=LayoutInflater.from(this.getContext()).inflate(R.layout.title_view,this,true);
        this.titleName= (TextView) view.findViewById(R.id.title_name);
        this.titleName.setText(titleName);
        view.findViewById(R.id.back).setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                ((Activity)this.getContext()).finish();
                break;
        }
    }
}
