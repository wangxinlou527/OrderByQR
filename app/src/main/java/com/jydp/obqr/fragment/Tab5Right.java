package com.jydp.obqr.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jydp.obqr.App;
import com.jydp.obqr.R;
import com.jydp.obqr.adapter.CheckOutAdapter;
import com.jydp.obqr.adapter.MessageAdapter;
import com.jydp.obqr.dailog.CustomDialog;
import com.jydp.obqr.databinding.FramTab5RightBinding;
import com.jydp.obqr.entity.MessageEntuty;
import com.jydp.obqr.eventbus.EventBusHelper;
import com.jydp.obqr.eventbus.EventCode;
import com.jydp.obqr.eventbus.EventMessage;
import com.jydp.obqr.util.ISharedPreference;
import com.jydp.obqr.viewmodel.BillViewModel;
import com.jydp.obqr.viewmodel.Callback;
import com.jydp.obqr.viewmodel.ffCallback;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/*
2020/10/24 16:39 星期六
作用 ：
*/
public class Tab5Right extends BaseFragment<FramTab5RightBinding> implements View.OnClickListener {

    private BillViewModel model;
    private int page = 1;
    private int limit = 14;
    private CheckOutAdapter checkOutAdapter;
    private CustomDialog customDialog;
    @Override
    public int getContentLayoutId() {
        return R.layout.fram_tab5_right;
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
        binding.page1Btn.setText("<");
        binding.page2Btn.setText(">");
        binding.page1Btn.setOnClickListener(this);
        binding.page2Btn.setOnClickListener(this);
        binding.ivHard.setCompoundDrawablePadding(17);
        binding.ivHard.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.tab_icon_cxdy_hig), null, null, null);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),limit);
        gridLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        checkOutAdapter = new CheckOutAdapter(R.layout.itme_check_out,model.checkOutList);
        checkOutAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                checkOutAdapter.setmPosition(position);
                checkOutAdapter.notifyDataSetChanged();
                Log.d("-------","orderid:" + model.checkOutList.get(position).getId());
                EventBusHelper.post(new EventMessage(EventCode.EVENT_CHECK_OUT_DETAIL,model.checkOutList.get(position)));
            }
        });
        binding.rt5Rv.setLayoutManager(gridLayoutManager);
        binding.rt5Rv.setAdapter(checkOutAdapter);
        page = 1;
        checkOutAdapter.setmPosition(-1);
        checkOutAdapter.setPage(page);
        checkOutAdapter.setLimit(limit);

    }
    public void initData() {

        if (ISharedPreference.getInstance(App.app).getTime() < (System.currentTimeMillis() - model.timeS)) {
            model.upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getOrdersList();
                }
                @Override
                public void file(String s) {

                }
            });
        }else{
            getOrdersList();
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
                customDialog.show();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(EventMessage eventMessage) {
        switch (eventMessage.getCode()) {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.page_1_btn:
                if(page > 1) {
                    page --;
                    checkOutAdapter.setPage(page);
                    checkOutAdapter.setLimit(limit);
                    checkOutAdapter.setmPosition(-1);
                    getOrdersList();
                    EventBusHelper.post(new EventMessage(EventCode.EVENT_LFT5_CLEAR));
                }
                break;
            case R.id.page_2_btn:
                if(model.notbillrightMata.get().getPagination().getTotal_pages() > page) {
                    page ++;
                    checkOutAdapter.setPage(page);
                    checkOutAdapter.setLimit(limit);
                    checkOutAdapter.setmPosition(-1);
                    getOrdersList();
                    EventBusHelper.post(new EventMessage(EventCode.EVENT_LFT5_CLEAR));
                }
                break;

        }
    }

    public void getOrdersList(){
        model.requestOrdersList(page, limit, new Callback() {
            @Override
            public void success() {
                checkOutAdapter.notifyDataSetChanged();
                if(model.checkOutList.size() > 0) {
                    binding.rt5Ll.setVisibility(View.VISIBLE);
                    binding.rt5Txt.setVisibility(View.GONE);
                }else{
                    binding.rt5Ll.setVisibility(View.GONE);
                    binding.rt5Txt.setVisibility(View.VISIBLE);
                }
                binding.rt5PageTv.setText(model.notbillrightMata.get().getPagination().getCurrent_page() + "/" + model.notbillrightMata.get().getPagination().getTotal_pages());
            }
        });
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
