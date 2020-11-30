package com.jydp.obqr.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jydp.obqr.R;
import com.jydp.obqr.adapter.GuqingLftAdapter;
import com.jydp.obqr.dailog.ShowGuqingDialog;
import com.jydp.obqr.dailog.ShowIOSDialog;
import com.jydp.obqr.databinding.FramTab4LeftBinding;
import com.jydp.obqr.eventbus.EventBusHelper;
import com.jydp.obqr.eventbus.EventCode;
import com.jydp.obqr.eventbus.EventMessage;
import com.jydp.obqr.viewmodel.BillViewModel;
import com.jydp.obqr.viewmodel.Callback;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/*
2020/10/24 17:04 星期六
作用 ：
*/
public class Tab4Left extends BaseFragment<FramTab4LeftBinding> implements View.OnClickListener , View.OnTouchListener {

    private BillViewModel model;
    private GuqingLftAdapter guqingLftAdapter;

    @Override
    public int getContentLayoutId() {
        return R.layout.fram_tab4_left;
    }

    @Override
    protected void viewCreate(Bundle savedInstanceState) {
        model = ViewModelProviders.of(this).get(BillViewModel.class);
        binding.setViewModel(model);
        initView();
        initData();
    }

    public void initView() {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(RecyclerView.VERTICAL);
        guqingLftAdapter = new GuqingLftAdapter(R.layout.item_guqing,model.guqingList,getContext());
        guqingLftAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShowGuqingDialog guqingDialog = new ShowGuqingDialog();
                guqingDialog.show(getContext(),3,model.guqingList.get(position).getOrder_menu_translate().getName(),
                        new ShowGuqingDialog.OnBottomClickListener(){

                            @Override
                            public void positive(int i ,String s) {
                                if(i == 0) {
                                    addGuqing(model.guqingList.get(position).getOrder_menu_translate().getMenu_id(),"0");
                                }else{
                                    addGuqing(model.guqingList.get(position).getOrder_menu_translate().getMenu_id(),s);
                                }
                            }

                            @Override
                            public void negative() {

                            }

                            @Override
                            public void del() {
                                Log.d("------","删除4");
                                model.delGuqList(model.guqingList.get(position).getOrder_menu_translate().getMenu_id(), new Callback() {
                                    @Override
                                    public void success() {
                                        initData();
                                        EventBusHelper.post(new EventMessage(EventCode.EVENT_GUQING_RT_UPDATE));
                                    }
                                });
                            }
                        });


            }
        });
        binding.lft4List.setLayoutManager(llm);
        binding.lft4List.setAdapter(guqingLftAdapter);

        binding.clearBtn.setOnClickListener(this);
        binding.updateBtn.setOnClickListener(this);
        binding.clearBtn.setOnTouchListener(this);
        binding.updateBtn.setOnTouchListener(this);

    }
    public void initData() {

//        getGuqList();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(EventMessage eventMessage) {
        switch (eventMessage.getCode()) {
            case EventCode.EVENT_GUQING_LFT_UPDATE:
                getGuqList();
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.clear_btn:
                ShowIOSDialog showIOSDialog = new ShowIOSDialog();
                showIOSDialog.show(getContext(),1,getResources().getString(R.string.tab4_hint),new ShowIOSDialog.OnBottomClickListener(){
                    @Override
                    public void positive() {
                        model.clearGuqList(new Callback() {
                            @Override
                            public void success() {
                                EventBusHelper.post(new EventMessage(EventCode.EVENT_GUQING_RT_UPDATE));
                                initData();
                            }
                        });
                    }

                    @Override
                    public void negative() {

                    }
                });

                break;
            case R.id.update_btn:
                initData();
                EventBusHelper.post(new EventMessage(EventCode.EVENT_GUQING_RT_UPDATE));
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                v.setAlpha(0.4f);
                break;

            case MotionEvent.ACTION_UP:
                v.setAlpha(1f);
                break;

            case MotionEvent.ACTION_CANCEL:
                v.setAlpha(1f);
                break;
        }

        return false;
    }


    public void addGuqing(int menu_id,String count){
        model.addGuqList(menu_id, count, new Callback() {
            @Override
            public void success() {
                initData();
                EventBusHelper.post(new EventMessage(EventCode.EVENT_GUQING_RT_UPDATE));
            }
        });
    }

    public void getGuqList(){
        model.getGuqList(new Callback() {
            @Override
            public void success() {
                guqingLftAdapter.notifyDataSetChanged();
                Log.d("----------","----------");
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("---------","zhouiqi:Left onStart"  );
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("---------","zhouiqi:Left onResume"  );
    }
}
