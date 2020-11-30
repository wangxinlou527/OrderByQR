package com.jydp.obqr.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jydp.obqr.App;
import com.jydp.obqr.R;
import com.jydp.obqr.adapter.MessageAdapter;
import com.jydp.obqr.dailog.CustomDialog;
import com.jydp.obqr.databinding.FramTab3RightBinding;
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
public class Tab3Right extends BaseFragment<FramTab3RightBinding> {
    private BillViewModel model;
    private MessageAdapter messageAdapter;
    private int type;
    private CustomDialog customDialog;

    @Override
    public int getContentLayoutId() {
        return R.layout.fram_tab3_right;
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
        binding.ivHard.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.tab_icon_xxzx_hig), null, null, null);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        binding.rt3MainRv.setLayoutManager(llm);
        messageAdapter = new MessageAdapter(R.layout.item_message,model.messageList,getContext());
        messageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                messageAdapter.setmPosition(position);
                messageAdapter.notifyDataSetChanged();
                model.messageList.get(position).setStatus(type);
                EventBusHelper.post(new EventMessage(EventCode.EVENT_MESSAGE_DATA,model.messageList.get(position)));
            }
        });
        binding.rt3MainRv.setAdapter(messageAdapter);
        binding.messageRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btn_1:
                        type = 1;
                        messageAdapter.setmPosition(-1);
                        getMessage();
                        EventBusHelper.post(new EventMessage(EventCode.EVENT_LFT3_CLEAR));
                        break;

                    case R.id.btn_2:
                        type = 2;
                        messageAdapter.setmPosition(-1);
                        getMessage();
                        EventBusHelper.post(new EventMessage(EventCode.EVENT_LFT3_CLEAR));
                        break;
                }
            }
        });
        type = 1;
        messageAdapter.setmPosition(-1);

    }
    public void initData() {
        if (ISharedPreference.getInstance(App.app).getTime() < (System.currentTimeMillis() - model.timeS)) {
            model.upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getMessage();
                }

                @Override
                public void file(String s) {

                }
            });
        }else{
            getMessage();
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
                if(model.messageList.size() == 0) {
                    customDialog.show();
                }

            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(EventMessage eventMessage) {
        switch (eventMessage.getCode()) {
            case EventCode.EVENT_MESSAGE_RT_UPDATE:
                initData();
                break;
        }
    }

    public void  getMessage(){
        model.getMessageList(type, new Callback() {
            @Override
            public void success() {
                Log.d("---------","model.messageList.sizeL:" + model.messageList.size());
                if(model.messageList.size() > 0) {
                    binding.rt3MainRv.setVisibility(View.VISIBLE);
                    binding.rt3Img.setVisibility(View.GONE);
                }else{
                    binding.rt3MainRv.setVisibility(View.GONE);
                    binding.rt3Img.setVisibility(View.VISIBLE);
                }
                messageAdapter.setmPosition(-1);
                messageAdapter.notifyDataSetChanged();
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
