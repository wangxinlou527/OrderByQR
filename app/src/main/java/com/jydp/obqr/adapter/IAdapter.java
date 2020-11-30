package com.jydp.obqr.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/24 0024.company keydom
 */

public abstract class IAdapter<T, VB extends ViewDataBinding> extends RecyclerView.Adapter<IHolder<VB>> {
    private static final String TAG = IAdapter.class.getSimpleName();
    private List<T> mData, mOldData;
    private int NO_MAX_SIZE = -1;
    private int headCount = 0;

    private int count = 0;

    public IAdapter() {
    }

    public void setData(final List<T> data) {
        if (data == null) {
            return;
        }
        if (mData == null) {
            mData = new ArrayList<>();
            mData.addAll(data);
            notifyItemRangeInserted(0, data.size());
        } else {
            mOldData = new ArrayList<>(mData);
            mData.clear();
            mData.addAll(data);

            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mOldData.size();
                }

                @Override
                public int getNewListSize() {
                    return mData.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {

                    return IAdapter.this.areItemsTheSame(mOldData, mData, oldItemPosition, newItemPosition);
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    //对象相同判断有问题
                    return false;
                }
            });
            result.dispatchUpdatesTo(this);

        }

    }

    @Override
    public IHolder<VB> onCreateViewHolder(ViewGroup parent, int viewType) {
        VB vb = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getLayoutId(viewType), parent, false);
        convertListener(vb);
        return new IHolder<>(vb);
    }

    /**
     * 设置回调
     *
     * @param binding
     */
    protected void convertListener(@NonNull VB binding) {
    }

    protected abstract int getLayoutId(int viewType);

    @Override
    public void onBindViewHolder(IHolder<VB> holder, int position) {
        T data = null;
        if (position >= getHeadCount()) {
            data = getData().get(position - getHeadCount());
            holder.binding.setVariable(getDataBRId(holder.getItemViewType()), data);
            holder.binding.executePendingBindings();
        }
//      Log.e("TAG", "position=----" + position + "******" + (data == null));//position=0******false
        convert(holder.binding, position, data);
//        Log.e("TAG", "********---------"+holder.binding.toString());//com.shanghankj.pay.databinding.ItemAtuHeaderBinding@7832ea
//        Log.e("TAG", "position---------"+position);//0
//        Log.e("TAG", "data---------"+data);//com.shanghankj.pay.main.bill.model.BillAutBean@c8eda9b
    }

    protected abstract int getDataBRId(int itemViewType);

    protected void convert(@NonNull VB binding, int position, T t) {
    }

    private int getHeadCount() {
        return headCount;
    }

    @Override
    public int getItemCount() {
        return (mData == null ? 0 : (getMaxSize() <= NO_MAX_SIZE) ? mData.size() : Math.min(mData.size(), getMaxSize())) + getHeadCount();
    }

    public List<T> getData() {
        return mData;
    }

    public boolean areItemsTheSame(List<T> oldData, List<T> data, int oldItemPosition, int newItemPosition) {
        //这里打算换成hashcode,我还在研究hashcode和equals的区别

        return getItemViewType(oldItemPosition) == getItemViewType(newItemPosition);
    }

    public boolean areContentsTheSame(List<T> oldData, List<T> data, int oldItemPosition, int newItemPosition) {
        //我这里通过equals来判断的数据,不知道需不需要单独写
        return oldData.get(oldItemPosition).equals(data.get(newItemPosition));
    }

    public void notifyItemChanged(T data) {
        notifyItemChanged(getData().indexOf(data));
    }

    private int getMaxSize() {
        return NO_MAX_SIZE;
    }

    public interface ItemNavigator<T> {

        /**
         * item的点击事件
         *
         * @param item ItemView用到的数据
         */
        void itemClick(T item);
    }
    public interface ItemOcNavigator<T> {

        /**
         * item的点击事件
         *
         * @param item ItemView用到的数据
         */
        void itemClick(T item);
    }
}
