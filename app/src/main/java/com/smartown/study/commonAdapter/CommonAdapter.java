package com.smartown.study.commonAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.smartown.study.R;

import java.util.List;

/**
 * Created by Tiger on 2016-07-07.
 */
public class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {

    private Context context;
    private List<T> dataTList;
    private ValueGetter<T> valueGetter;

    public CommonAdapter(Context context, List<T> dataTList) {
        this.context = context;
        this.dataTList = dataTList;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommonViewHolder(LayoutInflater.from(context).inflate(R.layout.common_list_item, null));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        if (valueGetter != null) {
            //通过内容获取工具取值
            holder.getTextView().setText(valueGetter.getValue(dataTList.get(position)));
        }
    }

    @Override
    public int getItemCount() {
        return dataTList.size();
    }

    /**
     * 设置内容获取工具
     *
     * @param valueGetter
     */
    public void setValueGetter(ValueGetter<T> valueGetter) {
        this.valueGetter = valueGetter;
    }

}
