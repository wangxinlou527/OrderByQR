package com.jydp.obqr.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.donkingliang.groupedadapter.layoutmanger.GroupedGridLayoutManager;
import com.jydp.obqr.App;
import com.jydp.obqr.R;
import com.jydp.obqr.adapter.CommodityAdapter;
import com.jydp.obqr.adapter.CommodityTabAdapter;
import com.jydp.obqr.adapter.GuqingAdapter;
import com.jydp.obqr.adapter.MessageAdapter;
import com.jydp.obqr.dailog.CustomDialog;
import com.jydp.obqr.dailog.GuqingDialog;
import com.jydp.obqr.dailog.ShowGuqingDialog;
import com.jydp.obqr.databinding.FramTab4RightBinding;
import com.jydp.obqr.eventbus.EventBusHelper;
import com.jydp.obqr.eventbus.EventCode;
import com.jydp.obqr.eventbus.EventMessage;
import com.jydp.obqr.util.ISharedPreference;
import com.jydp.obqr.view.WrapContentLinearLayoutManager;
import com.jydp.obqr.viewmodel.BillViewModel;
import com.jydp.obqr.viewmodel.Callback;
import com.jydp.obqr.viewmodel.ffCallback;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static com.jydp.obqr.dailog.ShowGuqingDialog.*;

/*
2020/10/24 16:39 星期六
作用 ：
*/
public class Tab4Right extends BaseFragment<FramTab4RightBinding> {
    private BillViewModel model;
    private CommodityTabAdapter commodityTabAdapter;
    private GuqingAdapter guqingAdapter;
    private GroupedGridLayoutManager gridLayoutManager;
    private int moveScor;
    private CustomDialog customDialog;

    @Override
    public int getContentLayoutId() {
        return R.layout.fram_tab4_right;
    }

    @Override
    protected void viewCreate(Bundle savedInstanceState) {
        model = ViewModelProviders.of(this).get(BillViewModel.class);
        binding.setViewModel(model);
        initView();
        initLoading();
//        initData();
    }
    public void initView() {
        binding.ivHard.setCompoundDrawablePadding(17);
        binding.ivHard.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.tab_icon_gq_hig), null, null, null);

        //沽清标签
        WrapContentLinearLayoutManager llm = new WrapContentLinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.rt4GuqingTab.setLayoutManager(llm);
        commodityTabAdapter = new CommodityTabAdapter(R.layout.item_commodity_tab,model.differenGuDian);
        binding.rt4GuqingTab.setAdapter(commodityTabAdapter);
        commodityTabAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                commodityTabAdapter.setmPosition(position);
                commodityTabAdapter.notifyDataSetChanged();

                int position1 = 0;
                for (int i = 0;i < model.differenGuDian.size();i++){
                    position1 += model.differenGuDian.get(i).getMenus().size()  + 2;
                    if(i >= (position -1)) {
                        break;
                    }
                    position1 += 1;
                    Log.d("---------position1",position1 + "");
                }

                Log.d("---------position1",position1 + "");
                if(moveScor > position1) {
                    position1 -= 1;
                }
                if(position == 0) {
                    position1 = 0;
                }
                LinearSmoothScroller s1 = new LinearSmoothScroller(getActivity());
                s1.setTargetPosition(position1);
                gridLayoutManager.startSmoothScroll(s1);
                moveScor = position1;
            }
        });

        //沽清
        guqingAdapter = new GuqingAdapter(getContext(),model.differenGuDian);
        gridLayoutManager = new GroupedGridLayoutManager(getContext(), 5, guqingAdapter);
        binding.rt4GuqingMian.setLayoutManager(gridLayoutManager);
        guqingAdapter.setOnChildClickListener(new GroupedRecyclerViewAdapter.OnChildClickListener() {
            @Override
            public void onChildClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder, int groupPosition, int childPosition) {
                ShowGuqingDialog guqingDialog = new ShowGuqingDialog();
                guqingDialog.show(getContext(),1,model.differenGuDian.get(groupPosition).getMenus().get(childPosition).getOrder_menu_translate().getName(),
                        new ShowGuqingDialog.OnBottomClickListener(){

                            @Override
                            public void positive(int i ,String s) {
                                if(i == 0) {
                                    addGuqing(model.differenGuDian.get(groupPosition).getMenus().get(childPosition).getOrder_menu_translate().getMenu_id(),"0");
                                }else{
                                    addGuqing(model.differenGuDian.get(groupPosition).getMenus().get(childPosition).getOrder_menu_translate().getMenu_id(),s);
                                }
                            }

                            @Override
                            public void negative() {

                            }

                            @Override
                            public void del() {

                            }
                        });

            }
        });
        binding.rt4GuqingMian.setAdapter(guqingAdapter);


    }
    public void initData() {

        if (ISharedPreference.getInstance(App.app).getTime() < (System.currentTimeMillis() - model.timeS)) {
            model.upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getDifferentList();
                }

                @Override
                public void file(String s) {

                }
            });
        }else{
            getDifferentList();
        }
    }

    public void initLoading(){
        customDialog = new CustomDialog(getContext(), 1);
        model.dialogText.observe(this, s -> {
            if (s == null) {
                if (customDialog != null) {
                    customDialog.dismiss();
                }
            } else {
                //我的dialog需要一个标题,所以我需要一个接口方法来获取标题
                // dialog = ProgressDialog.show(LoginActivity.this, s, "请稍等……", false, false);
                if(model.differenGuDian.size() == 0) {
                    customDialog.show();
                }

            }
        });
    }

    public void getDifferentList(){
        model.getDifferentList(new Callback() {
            @Override
            public void success() {
                commodityTabAdapter.notifyDataSetChanged();
                guqingAdapter.notifyDataChanged();
                EventBusHelper.post(new EventMessage(EventCode.EVENT_GUQING_LFT_UPDATE));
            }
        });
    }

    public void addGuqing(int menu_id,String count){
        model.addGuqList(menu_id, count, new Callback() {
            @Override
            public void success() {
                initData();
                EventBusHelper.post(new EventMessage(EventCode.EVENT_GUQING_LFT_UPDATE));
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(EventMessage eventMessage) {
        switch (eventMessage.getCode()) {
            case EventCode.EVENT_GUQING_RT_UPDATE:
                initData();
                break;
        }
    }



    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onDestroy() {
        if(customDialog != null) {
            customDialog.dismiss();
        }
        super.onDestroy();
    }
}
