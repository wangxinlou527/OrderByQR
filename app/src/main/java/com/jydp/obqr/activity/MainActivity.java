package com.jydp.obqr.activity;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTabHost;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jydp.obqr.App;
import com.jydp.obqr.R;
import com.jydp.obqr.dailog.CustomDialog;
import com.jydp.obqr.dailog.ShowDayinDialog;
import com.jydp.obqr.dailog.ShowLanguageDialog;
import com.jydp.obqr.dailog.ShowLogOutDialog;
import com.jydp.obqr.dailog.ShowRijiDialog;
import com.jydp.obqr.dailog.ShowzhunbeiDialog;
import com.jydp.obqr.databinding.ActivityMainBinding;
import com.jydp.obqr.entity.PrintDetailEntity;
import com.jydp.obqr.entity.PutMoneyEntity;
import com.jydp.obqr.entity.TakeMoney;
import com.jydp.obqr.eventbus.EventCode;
import com.jydp.obqr.eventbus.EventMessage;
import com.jydp.obqr.fragment.Tab1Left;
import com.jydp.obqr.fragment.Tab1Right;
import com.jydp.obqr.fragment.Tab2Left;
import com.jydp.obqr.fragment.Tab2Right;
import com.jydp.obqr.fragment.Tab3Left;
import com.jydp.obqr.fragment.Tab3Right;
import com.jydp.obqr.fragment.Tab4Left;
import com.jydp.obqr.fragment.Tab4Right;
import com.jydp.obqr.fragment.Tab5Left;
import com.jydp.obqr.fragment.Tab5Right;
import com.jydp.obqr.printer.DeviceConnFactoryManager;
import com.jydp.obqr.printer.ThreadPool;
import com.jydp.obqr.util.ISharedPreference;
import com.jydp.obqr.util.ToastUtil;
import com.jydp.obqr.viewmodel.BillViewModel;
import com.jydp.obqr.viewmodel.Callback;
import com.jydp.obqr.viewmodel.ffCallback;
import com.jydp.obqr.websocket.BackService;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, View.OnTouchListener {
    @BindView(R.id.ll_main)
    LinearLayout ll_main;
    @BindView(R.id.main_rg)
    RadioGroup rg;
    @BindView(R.id.tv1)
    RadioButton tv1;
    @BindView(R.id.tv2)
    RadioButton tv2;
    @BindView(R.id.tv3)
    RadioButton tv3;
    @BindView(R.id.tv4)
    RadioButton tv4;
    @BindView(R.id.tv5)
    RadioButton tv5;
    @BindView(R.id.tv6)
    RadioButton tv6;

    private MediaPlayer player;
    private ThreadPool threadPool;
    private int id = 0;
    private FragmentTabHost lftTab;
    private FragmentTabHost rtTab;
    private LinearLayout setLl;
    private LinearLayout zhunbeiBtn, rijiBtn, yuyanBtn, dayinBtn, tuichuBtn;
    private BillViewModel model;
    private Context context;
    private Locale languageStr;
    private Intent startServiceIntent;
    private int fragmentId;
    public static List<String> prints = new ArrayList<>();
    public static String mainPrint;
    private CustomDialog customDialog;


    @Override
    protected Activity getChildActivity() {
        return this;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
        checkPermission();
        requestPermission();
        //开启长连接
        startService();
        initView();
//        initLoading();
        initData();
        initPrinter();


    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        initRadioGroup();

        switch (checkedId) {
            case R.id.tv1:
                tv1.setBackgroundColor(getResources().getColor(R.color.tab1_jia_bg));
                tv1.setTextColor(Color.parseColor("#FFFFFF"));
                tv1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.tab_icon_sy_hig), null, null, null);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_center, new Tab1Left())
                        .replace(R.id.frag_right, new Tab1Right())
                        .commit();
                setLl.setVisibility(View.GONE);
                lftTab.setVisibility(View.VISIBLE);
                rtTab.setVisibility(View.VISIBLE);
                fragmentId = 1;
                break;
            case R.id.tv2:
                tv2.setBackgroundColor(getResources().getColor(R.color.tab1_jia_bg));
                tv2.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.tab_icon_yd_hig), null, null, null);
                tv2.setTextColor(Color.parseColor("#FFFFFF"));
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_center, new Tab2Left())
                        .replace(R.id.frag_right, new Tab2Right())
                        .commit();
                setLl.setVisibility(View.GONE);
                lftTab.setVisibility(View.VISIBLE);
                rtTab.setVisibility(View.VISIBLE);
                fragmentId = 2;
                break;
            case R.id.tv3:
                tv3.setBackgroundColor(getResources().getColor(R.color.tab1_jia_bg));
                tv3.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.tab_icon_xxzx_hig), null, null, null);
                tv3.setTextColor(Color.parseColor("#FFFFFF"));
                setLl.setVisibility(View.GONE);
                lftTab.setVisibility(View.VISIBLE);
                rtTab.setVisibility(View.VISIBLE);
                dataBinding.messageMk.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_center, new Tab3Left())
                        .replace(R.id.frag_right, new Tab3Right())
                        .commit();
                fragmentId = 3;

                break;
            case R.id.tv4:
                tv4.setBackgroundColor(getResources().getColor(R.color.tab1_jia_bg));
                tv4.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.tab_icon_gq_hig), null, null, null);
                tv4.setTextColor(Color.parseColor("#FFFFFF"));
                setLl.setVisibility(View.GONE);
                lftTab.setVisibility(View.VISIBLE);
                rtTab.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_center, new Tab4Left())
                        .replace(R.id.frag_right, new Tab4Right())
                        .commit();
                fragmentId = 4;
                break;
            case R.id.tv5:
                tv5.setBackgroundColor(getResources().getColor(R.color.tab1_jia_bg));
                tv5.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.tab_icon_cxdy_hig), null, null, null);
                tv5.setTextColor(Color.parseColor("#FFFFFF"));
                setLl.setVisibility(View.GONE);
                lftTab.setVisibility(View.VISIBLE);
                rtTab.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_center, new Tab5Left())
                        .replace(R.id.frag_right, new Tab5Right())
                        .commit();
                fragmentId = 5;
                break;
            case R.id.tv6:
                fragmentId = 6;
                onClickSetting();
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
        }

        return false;
    }


    public void onClickSetting() {
        tv6.setBackgroundColor(getResources().getColor(R.color.tab1_jia_bg));
        tv6.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.tab_icon_sz_hig), null, null, null);
        tv6.setTextColor(Color.parseColor("#FFFFFF"));
        lftTab.setVisibility(View.GONE);
        rtTab.setVisibility(View.GONE);
        setLl.setVisibility(View.VISIBLE);
    }


    public void initRadioGroup() {
        tv1.setBackgroundColor(getResources().getColor(R.color.tab1_bg_));
        tv2.setBackgroundColor(getResources().getColor(R.color.tab1_bg_));
        tv3.setBackgroundColor(getResources().getColor(R.color.tab1_bg_));
        tv4.setBackgroundColor(getResources().getColor(R.color.tab1_bg_));
        tv5.setBackgroundColor(getResources().getColor(R.color.tab1_bg_));
        tv6.setBackgroundColor(getResources().getColor(R.color.tab1_bg_));
        tv1.setTextColor(Color.parseColor("#725133"));
        tv2.setTextColor(Color.parseColor("#725133"));
        tv3.setTextColor(Color.parseColor("#725133"));
        tv4.setTextColor(Color.parseColor("#725133"));
        tv5.setTextColor(Color.parseColor("#725133"));
        tv6.setTextColor(Color.parseColor("#725133"));
        tv1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.tab_icon_sy_nor), null, null, null);
        tv1.setCompoundDrawablePadding(17);
        tv2.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.tab_icon_yd_nor), null, null, null);
        tv2.setCompoundDrawablePadding(17);
        tv3.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.tab_icon_xxzx_nor), null, null, null);
        tv3.setCompoundDrawablePadding(17);
        tv4.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.tab_icon_gq_nor), null, null, null);
        tv4.setCompoundDrawablePadding(17);
        tv5.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.tab_icon_cxdy_nor), null, null, null);
        tv5.setCompoundDrawablePadding(17);
        tv6.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.tab_icon_sz_nor), null, null, null);
        tv6.setCompoundDrawablePadding(17);
    }

    public void initView() {

        model = ViewModelProviders.of(this).get(BillViewModel.class);
        dataBinding.setViewModel(model);

        lftTab = findViewById(R.id.frag_center);
        rtTab = findViewById(R.id.frag_right);
        setLl = findViewById(R.id.set_ll);
        zhunbeiBtn = findViewById(R.id.zhunbei_btn);
        rijiBtn = findViewById(R.id.riji_btn);
        yuyanBtn = findViewById(R.id.yuyan_btn);
        dayinBtn = findViewById(R.id.dayin_btn);
        tuichuBtn = findViewById(R.id.tuichu_btn);

        zhunbeiBtn.setOnClickListener(this);
        rijiBtn.setOnClickListener(this);
        yuyanBtn.setOnClickListener(this);
        dayinBtn.setOnClickListener(this);
        tuichuBtn.setOnClickListener(this);
        rg.setOnCheckedChangeListener(this);

        zhunbeiBtn.setOnTouchListener(this);
        rijiBtn.setOnTouchListener(this);
        yuyanBtn.setOnTouchListener(this);
        dayinBtn.setOnTouchListener(this);
        tuichuBtn.setOnTouchListener(this);


        if (getIntent() != null) {
            if (getIntent().getStringExtra("changeLanguage") != null) {
                rg.check(R.id.tv6);
            } else {
                rg.check(R.id.tv1);
            }
        } else {
            rg.check(R.id.tv1);
        }

    }

    public void initData() {
        //getStaff();
        //  getRIji();
    }

    public void initLoading() {
        customDialog = new CustomDialog(this, 1);
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

    private void checkPermission() {
        for (String permission : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, permission)) {
                per.add(permission);
            }
        }
    }

    ArrayList<String> per = new ArrayList<>();
    private String[] permissions = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.BLUETOOTH
    };
    private static final int REQUEST_CODE = 0x004;

    private void requestPermission() {
        if (per.size() > 0) {
            String[] p = new String[per.size()];
            ActivityCompat.requestPermissions(this, per.toArray(p), REQUEST_CODE);
        }
    }

    private void startService() {

        if (isServiceRunning("com.jydp.obqr.websocket.BackService")) {

            Log.i("------", "服务正在运行 : return");
            return;
        }


        /*启动服务*/
        startServiceIntent = new Intent(this, BackService.class);
//        stopService(startServiceIntent);
        startService(startServiceIntent);

    }


//    private void reStartService() {
//
//        if (isServiceRunning("com.jydp.obqr.websocket.BackService")) {
//            stopService(startServiceIntent);
//        }
//        startService(startServiceIntent);
//    }

    /**
     * 判断服务是否运行
     */
    private boolean isServiceRunning(final String className) {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> info = activityManager.getRunningServices(Integer.MAX_VALUE);
        if (info == null || info.size() == 0) return false;
        for (ActivityManager.RunningServiceInfo aInfo : info) {
            if (className.equals(aInfo.service.getClassName())) return true;
        }
        return false;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(EventMessage eventMessage) {
        switch (eventMessage.getCode()) {
            case EventCode.EVENT_MESSAGE_VOICE:
                Log.e("-------", "web voice");
                playAudio();
                if (fragmentId != 3) {
                    dataBinding.messageMk.setVisibility(View.VISIBLE);
                }
                break;

            case EventCode.EVENT_NO_TOKEN:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;

            case EventCode.EVENT_UPDATE_TOKEN:
                upDateToken();
                break;

//            case EventCode.EVENT_MAIN_RIJI:
//                getRIji(8);
//
//            case EventCode.EVENT_MAIN_RIJI_ALL:
//                getRIji(9, true);
//                break;
        }
    }

    //播放音频--
    private void playAudio() {

        try {
            if (player == null) {
                player = MediaPlayer.create(this, R.raw.voice);
            }
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //停止播放声音
    public void stopVoice(MediaPlayer player) {
        if (null != player) {
            player.stop();
        }
    }

    public void initPrinter() {
        Log.d("---------", "Printer init");
        prints.clear();
        //初始化端口信息
        threadPool = ThreadPool.getInstantiation();
        model.getDetailsPrint(new Callback() {
            @Override
            public void success() {
                mainPrint = model.staffPrint.get().getIp();
//                model.getPrintLock(mainPrint, Integer.valueOf(ISharedPreference.getInstance(getApplication()).getMerchant_id()), new Callback() {
//                    @Override
//                    public void success() {
//                        Log.d("-----------","1");
//                    }
//                });
                new DeviceConnFactoryManager.Build()
                        //设置端口连接方式
                        .setConnMethod(DeviceConnFactoryManager.CONN_METHOD.WIFI)
                        //设置端口IP地址
                        .setIp(model.staffPrint.get().getIp())//"192.168.199.213"
                        //设置端口ID（主要用于连接多设备）
                        .setId(0)
                        //设置连接的热点端口号
                        .setPort(9100)
                        .build();

                threadPool.addTask(new Runnable() {
                    @Override
                    public void run() {
                        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0].openPort();
                        if(!DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0].getConnState()) {
                            Log.e("--------", "打印机初始化IP设置异常:" + 0);
  //                          ToastUtil.showToastOne(getBaseContext(),getString(R.string.setting_dayinji_1) + model.staffPrint.get().getIp() + getString(R.string.setting_dayinji_2) );
                        }
                    }
                });

            }
        });


        model.getPrintList(new Callback() {
            @Override
            public void success() {
                try {
                    if (model.printList != null) {
                        for (int i = 0; i < model.printList.size(); i++) {
                            Log.d("---------", "Print init ip:" + model.printList.get(i).getIp() + "," + (i + 1));
                            //初始化端口信息
                            new DeviceConnFactoryManager.Build()
                                    //设置端口连接方式
                                    .setConnMethod(DeviceConnFactoryManager.CONN_METHOD.WIFI)
                                    //设置端口IP地址
                                    .setIp(model.printList.get(i).getIp())//"192.168.199.213"
                                    //设置端口ID（主要用于连接多设备）
                                    .setId(i + 1)
                                    //设置连接的热点端口号
                                    .setPort(9100)
                                    .build();
                            prints.add(model.printList.get(i).getIp());

                            int finalI = i;
                            threadPool.addTask(new Runnable() {
                                @Override
                                public void run() {
                                    DeviceConnFactoryManager.getDeviceConnFactoryManagers()[finalI + 1].openPort();
                                    if(!DeviceConnFactoryManager.getDeviceConnFactoryManagers()[finalI + 1].getConnState()) {
                                        Log.e("--------", "打印机初始化IP设置异常:" + model.printList.get(finalI).getIp() + "," + (finalI + 1));
//                                        ToastUtil.showToastOne(App.app,getString(R.string.setting_dayinji_1) + model.printList.get(finalI).getIp() + getString(R.string.setting_dayinji_2) );
                                    }
                                }
                            });


                        }
                    }

                } catch (Exception e) {
                    Log.e("--------", "打印机初始化IP设置异常:" + e.toString());
                }
            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhunbei_btn:
                zhunbeiDailog();
                break;
            case R.id.riji_btn:
                rijiDailog();
                break;
            case R.id.yuyan_btn:
                languageDailog();
                break;
            case R.id.dayin_btn:
                dayinDailog();
                break;
            case R.id.tuichu_btn:
                logOutDailog();
                break;

        }
    }

    public void getStaff() {
        model.getStaffList(new Callback() {
            @Override
            public void success() {

            }
        });
    }

//    public void getRIji(int i) {
//        model.getCashPrint(new Callback() {
//            @Override
//            public void success() {
//                btnReceiptPrint(i);
//            }
//        });
//    }
//
//    public void getRIji(int i, boolean n) {
//        model.getCashPrint(new Callback() {
//            @Override
//            public void success() {
//                btnReceiptPrint(i, n);
//            }
//        });
//    }


    public void zhunbeiDailog() {
        ShowzhunbeiDialog showzhunbeiDialog = new ShowzhunbeiDialog();
        showzhunbeiDialog.show(this, 1, getResources().getString(R.string.setting_zhunbei), model, new ShowzhunbeiDialog.OnBottomClickListener() {
            @Override
            public void positive() {

            }

            @Override
            public void negative() {

            }
        });
    }

    public void logOutDailog() {
        ShowLogOutDialog showLogOutDialog = new ShowLogOutDialog();
        showLogOutDialog.show(context, 1, getResources().getString(R.string.setting_logout), new ShowLogOutDialog.OnBottomClickListener() {
            @Override
            public void positive() {
//                ISharedPreference.getInstance(getApplication()).loginOut();
                ISharedPreference.getInstance(getApplication()).saveToken("");
                Intent intent1 = new Intent(MainActivity.this, BackService.class);
                startActivity(new Intent(context, LoginActivity.class));
                finish();
                stopService(intent1);
            }

            @Override
            public void negative() {
            }
        });
    }

    public void dayinDailog() {

        model.getDetailsPrint(new Callback() {
            @Override
            public void success() {
                ShowDayinDialog showDayinDialog = new ShowDayinDialog();
                showDayinDialog.show(context, 1, getResources().getString(R.string.setting_printer), model, new ShowDayinDialog.OnBottomClickListener() {
                    @Override
                    public void positive() {
                    }

                    @Override
                    public void negative() {
                    }
                });
            }
        });
    }


    public void rijiDailog() {

        ShowRijiDialog showRijiDialog = new ShowRijiDialog();
//                showRijiDialog.show(context, 1, getResources().getString(R.string.setting_riji), model.staffList, model.PrintCashDetail.get(), new ShowRijiDialog.OnBottomClickListener() {
        showRijiDialog.show(this, 1, getResources().getString(R.string.setting_riji), model, new ShowRijiDialog.OnBottomClickListener() {
            @Override
            public void positive() {
            }

            @Override
            public void negative() {

            }
        });
    }

    public void languageDailog() {
        ShowLanguageDialog showLanguageDialog = new ShowLanguageDialog();
        showLanguageDialog.show(context, 1, getResources().getString(R.string.setting_yuyan), new ShowLanguageDialog.OnBottomClickListener() {
            @Override
            public void positive(String str) {
                Log.d("--------", "languageDailog :" + str);
                switch (str) {
                    case "中文简体":
                        model.changeLanguage("zh-CN", new Callback() {
                            @Override
                            public void success() {
                                ISharedPreference.getInstance(getApplication()).saveLocale("zh-CN");
                                changeView();
                            }
                        });
                        break;
                    case "日本語":
                        model.changeLanguage("ja-JP", new Callback() {
                            @Override
                            public void success() {
                                ISharedPreference.getInstance(getApplication()).saveLocale("ja-JP");
                                changeView();
                            }
                        });

                        break;
                }
            }

            @Override
            public void negative() {

            }
        });
    }

    public void changeView() {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("changeLanguage", "1"); //该参数会在MainActivity的onNewIntent中获取
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void changeLanguage(Locale languageStr) {
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Log.d("--------", "切换语言");
            config.setLocale(languageStr);//切换成中文繁体，其它语言以此类推，对于一些sdk中没有事先定义的语言常量，可以使用Locale的构造方法传入需要的语言字符
        }
        resources.updateConfiguration(config, dm);

    }

    // MainActivity.class
    @Override
    public void setContentView(int layoutResID) {
        switch (ISharedPreference.getInstance(getApplication()).getLocale()) {
            case "zh-CN":
                changeLanguage(Locale.CHINESE);//在加载布局之前设置语言
                break;

            case "en":
                changeLanguage(Locale.ENGLISH);//在加载布局之前设置语言
                break;
            case "ja-JP":
                changeLanguage(Locale.JAPAN);
                break;
        }

        super.setContentView(layoutResID);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        boolean changeLanguage = intent.getBooleanExtra("changeLanguage", false);
        if (changeLanguage) {
            Log.d("----------", " 如果有一些文字是在加载完布局后设置的，那么需要重新设置一次");
            setContentView(R.layout.activity_main);//重新加载布局，改变布局中的语言

            // TODO 如果有一些文字是在加载完布局后设置的，那么需要重新设置一次
        }
    }

    public void upDateToken() {
        model.upDateToken(new ffCallback() {
            @Override
            public void success() {

            }

            @Override
            public void file(String s) {

            }
        });
    }


    public static List<String> getPrints() {
        return prints;
    }


    @Override
    protected void onStart() {
        super.onStart();
//        startService();
        Log.d("---------", "zhouiqi:ACtivty onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("---------", "zhouiqi:ACtivty onResume");

    }

    @Override
    protected void onDestroy() {
        if (customDialog != null) {
            customDialog.dismiss();
        }
        stopVoice(player);
        super.onDestroy();
    }
}