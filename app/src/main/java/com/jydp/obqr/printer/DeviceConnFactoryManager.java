package com.jydp.obqr.printer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.usb.UsbDevice;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.jydp.obqr.App;
import com.jydp.obqr.R;
import com.jydp.obqr.activity.MainActivity;
import com.jydp.obqr.entity.AAPrintEntity;
import com.jydp.obqr.entity.HouChuPrintEntity;
import com.jydp.obqr.entity.HouchuMenus;
import com.jydp.obqr.entity.MoneyPrintEntity;
import com.jydp.obqr.entity.OrderItemEntity;
import com.jydp.obqr.entity.PrintMaidanlEntity;
import com.jydp.obqr.entity.QrcodeEntity;
import com.jydp.obqr.entity.RejectedEntity;
import com.jydp.obqr.util.ISharedPreference;
import com.jydp.obqr.util.StrUtil;
import com.jydp.obqr.util.ToastUtil;
import com.jydp.obqr.viewmodel.BillViewModel;
import com.jydp.obqr.viewmodel.Callback;
import com.tools.command.EscCommand;
import com.tools.command.LabelCommand;
import com.tools.io.BluetoothPort;
import com.tools.io.EthernetPort;
import com.tools.io.PortManager;
import com.tools.io.SerialPort;
import com.tools.io.UsbPort;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import butterknife.internal.Utils;

import static android.hardware.usb.UsbManager.ACTION_USB_DEVICE_DETACHED;
import static com.tools.command.EscCommand.CHARACTER_SET.CHINA;

/**
 * Created by Administrator
 *
 * @author 猿史森林
 * Time 2017/8/2
 */
public class DeviceConnFactoryManager {

    public PortManager mPort;

    private static final String TAG = "------------";

    /**
     * 使用打印机指令错误
     */
    private static final int PRINTER_COMMAND_ERROR = 0x008;

    public CONN_METHOD connMethod;

    private String ip;

    private int port;

    private String macAddress;

    private UsbDevice mUsbDevice;

    private Context mContext;

    private String serialPortPath;

    private int baudrate;

    private int id;

    private static DeviceConnFactoryManager[] deviceConnFactoryManagers = new DeviceConnFactoryManager[10];

    private boolean isOpenPort;
    /**
     * ESC查询打印机实时状态指令
     */
    private byte[] esc = {0x10, 0x04, 0x02};


    /**
     * ESC查询打印机实时状态 缺纸状态
     */
    private static final int ESC_STATE_PAPER_ERR = 0x20;

    /**
     * ESC指令查询打印机实时状态 打印机开盖状态
     */
    private static final int ESC_STATE_COVER_OPEN = 0x04;

    /**
     * ESC指令查询打印机实时状态 打印机报错状态
     */
    private static final int ESC_STATE_ERR_OCCURS = 0x40;

    /**
     * TSC查询打印机状态指令
     */
    private byte[] tsc = {0x1b, '!', '?'};

    /**
     * TSC指令查询打印机实时状态 打印机缺纸状态
     */
    private static final int TSC_STATE_PAPER_ERR = 0x04;

    /**
     * TSC指令查询打印机实时状态 打印机开盖状态
     */
    private static final int TSC_STATE_COVER_OPEN = 0x01;

    /**
     * TSC指令查询打印机实时状态 打印机出错状态
     */
    private static final int TSC_STATE_ERR_OCCURS = 0x80;

    private byte[] cpcl = {0x1b, 0x68};

    /**
     * CPCL指令查询打印机实时状态 打印机缺纸状态
     */
    private static final int CPCL_STATE_PAPER_ERR = 0x01;
    /**
     * CPCL指令查询打印机实时状态 打印机开盖状态
     */
    private static final int CPCL_STATE_COVER_OPEN = 0x02;

    private byte[] sendCommand;
    /**
     * 判断打印机所使用指令是否是ESC指令
     */
    private PrinterCommand currentPrinterCommand;
    public static final byte FLAG = 0x10;
    private static final int READ_DATA = 10000;
    private static final String READ_DATA_CNT = "read_data_cnt";
    private static final String READ_BUFFER_ARRAY = "read_buffer_array";
    public static final String ACTION_CONN_STATE = "action_connect_state";
    public static final String ACTION_QUERY_PRINTER_STATE = "action_query_printer_state";
    public static final String STATE = "state";
    public static final String DEVICE_ID = "id";
    public static final int CONN_STATE_DISCONNECT = 0x90;
    public static final int CONN_STATE_CONNECTING = CONN_STATE_DISCONNECT << 1;
    public static final int CONN_STATE_FAILED = CONN_STATE_DISCONNECT << 2;
    public static final int CONN_STATE_CONNECTED = CONN_STATE_DISCONNECT << 3;
    public PrinterReader reader;
    public boolean device0;
    public static List<String> idList = new ArrayList<>();

    public enum CONN_METHOD {
        //蓝牙连接
        BLUETOOTH("BLUETOOTH"),
        //USB连接
        USB("USB"),
        //wifi连接
        WIFI("WIFI"),
        //串口连接
        SERIAL_PORT("SERIAL_PORT");

        private String name;

        private CONN_METHOD(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    public static DeviceConnFactoryManager[] getDeviceConnFactoryManagers() {
        return deviceConnFactoryManagers;
    }


    public static void InitQianting(BillViewModel model,ThreadPool threadPool,int i){
        Log.e("--------","打印机初始化 Qianting:");
        model.getDetailsPrint(new Callback() {
            @Override
            public void success() {
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
                DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0].onConnect(threadPool, model, false, i);
            }
        });
    }

    public static void InitHouchu(BillViewModel model,ThreadPool threadPool,String ip,int n,int m){
        Log.e("--------","打印机初始化 Houchu:");
        model.getPrintList(new Callback() {
            @Override
            public void success() {
                MainActivity.prints.clear();
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
                            MainActivity.prints.add(model.printList.get(i).getIp());

                        }
                        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[n + 1].onConnect(threadPool, n + 1, model, false, ip, m);
                    }

                } catch (Exception e) {
                    Log.e("--------","打印机初始化IP设置异常:" + e.toString());
                }
            }
        });
    }
    /**
     * 打开端口
     *
     * @return
     */
    public void openPort() {
        deviceConnFactoryManagers[id].isOpenPort = false;
        sendStateBroadcast(CONN_STATE_CONNECTING);
        switch (deviceConnFactoryManagers[id].connMethod) {
            case BLUETOOTH:
                System.out.println("id -> " + id);
                mPort = new BluetoothPort(macAddress);
                isOpenPort = deviceConnFactoryManagers[id].mPort.openPort();
                break;
            case USB:
                mPort = new UsbPort(mContext, mUsbDevice);
                isOpenPort = mPort.openPort();
                if (isOpenPort) {
                    IntentFilter filter = new IntentFilter(ACTION_USB_DEVICE_DETACHED);
                    mContext.registerReceiver(usbStateReceiver, filter);
                }
                break;
            case WIFI:
                mPort = new EthernetPort(ip, port);
                isOpenPort = mPort.openPort();
                break;
            case SERIAL_PORT:
                mPort = new SerialPort(serialPortPath, baudrate, 0);
                isOpenPort = mPort.openPort();
                break;
            default:
                break;
        }

        //端口打开成功后，检查连接打印机所使用的打印机指令ESC、TSC
        if (isOpenPort) {
            queryCommand();
        } else {
            if (this.mPort != null) {
                this.mPort = null;
            }
            sendStateBroadcast(CONN_STATE_FAILED);
        }
    }

    /**
     * 查询当前连接打印机所使用打印机指令（ESC（EscCommand.java）、TSC（LabelCommand.java））
     */
    private void queryCommand() {
        //开启读取打印机返回数据线程

        reader = new PrinterReader();
        reader.start(); //读取数据线程
        //查询打印机所使用指令
        queryPrinterCommand(); //
    }

    /**
     * 获取端口连接方式
     *
     * @return
     */
    public CONN_METHOD getConnMethod() {
        return connMethod;
    }

    /**
     * 获取端口打开状态（true 打开，false 未打开）
     *
     * @return
     */
    public boolean getConnState() {
        return isOpenPort;
    }

    /**
     * 获取连接蓝牙的物理地址
     *
     * @return
     */
    public String getMacAddress() {
        return macAddress;
    }

    /**
     * 获取连接网口端口号
     *
     * @return
     */
    public int getPort() {
        return port;
    }

    /**
     * 获取连接网口的IP
     *
     * @return
     */
    public String getIp() {
        return ip;
    }

    /**
     * 获取连接的USB设备信息
     *
     * @return
     */
    public UsbDevice usbDevice() {
        return mUsbDevice;
    }

    /**
     * 关闭端口
     */
    public void closePort(int id) {
        Log.d("---------", "Print8-----------closePort" + id);
        if (this.mPort != null) {
            System.out.println("id -> " + id);
            reader.cancel();
            boolean b = this.mPort.closePort();
            if (b) {
                this.mPort = null;
                isOpenPort = false;
                currentPrinterCommand = null;
            }
        }
        sendStateBroadcast(CONN_STATE_DISCONNECT);
    }

    /**
     * 获取串口号
     *
     * @return
     */
    public String getSerialPortPath() {
        return serialPortPath;
    }

    /**
     * 获取波特率
     *
     * @return
     */
    public int getBaudrate() {
        return baudrate;
    }

    public static void closeAllPort() {
        for (DeviceConnFactoryManager deviceConnFactoryManager : deviceConnFactoryManagers) {
            if (deviceConnFactoryManager != null) {
                Log.e(TAG, "cloaseAllPort() id -> " + deviceConnFactoryManager.id);
                deviceConnFactoryManager.closePort(deviceConnFactoryManager.id);
                deviceConnFactoryManagers[deviceConnFactoryManager.id] = null;
            }
        }
    }

    private DeviceConnFactoryManager(Build build) {
        this.connMethod = build.connMethod;
        this.macAddress = build.macAddress;
        this.port = build.port;
        this.ip = build.ip;
        this.mUsbDevice = build.usbDevice;
        this.mContext = build.context;
        this.serialPortPath = build.serialPortPath;
        this.baudrate = build.baudrate;
        this.id = build.id;
        deviceConnFactoryManagers[id] = this;
    }

    /**
     * 获取当前打印机指令
     *
     * @return PrinterCommand
     */
    public PrinterCommand getCurrentPrinterCommand() {
        return deviceConnFactoryManagers[id].currentPrinterCommand;
    }

    public static final class Build {
        private String ip;
        private String macAddress;
        private UsbDevice usbDevice;
        private int port;
        private CONN_METHOD connMethod;
        private Context context;
        private String serialPortPath;
        private int baudrate;
        private int id;

        public DeviceConnFactoryManager.Build setIp(String ip) {
            this.ip = ip;
            return this;
        }

        public DeviceConnFactoryManager.Build setMacAddress(String macAddress) {
            this.macAddress = macAddress;
            return this;
        }

        public DeviceConnFactoryManager.Build setUsbDevice(UsbDevice usbDevice) {
            this.usbDevice = usbDevice;
            return this;
        }

        public DeviceConnFactoryManager.Build setPort(int port) {
            this.port = port;
            return this;
        }

        public DeviceConnFactoryManager.Build setConnMethod(CONN_METHOD connMethod) {
            this.connMethod = connMethod;
            return this;
        }

        public DeviceConnFactoryManager.Build setContext(Context context) {
            this.context = context;
            return this;
        }

        public DeviceConnFactoryManager.Build setId(int id) {
            this.id = id;
            return this;
        }

        public DeviceConnFactoryManager.Build setSerialPort(String serialPortPath) {
            this.serialPortPath = serialPortPath;
            return this;
        }

        public DeviceConnFactoryManager.Build setBaudrate(int baudrate) {
            this.baudrate = baudrate;
            return this;
        }

        public DeviceConnFactoryManager build() {
            return new DeviceConnFactoryManager(this);
        }
    }

    public void sendDataImmediately(final Vector<Byte> data) {
        if (this.mPort == null) {
            return;
        }
        try {
            //  Log.e(TAG, "data -> " + new String(com.gprinter.command.GpUtils.convertVectorByteTobytes(data), "gb2312"));
            this.mPort.writeDataImmediately(data, 0, data.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int readDataImmediately(byte[] buffer) throws IOException {
        return this.mPort.readData(buffer);
    }


    /**
     * 查询打印机当前使用的指令（TSC、ESC）
     */
    private void queryPrinterCommand() {
        //线程池添加任务
        ThreadPool.getInstantiation().addTask(new Runnable() {
            @Override
            public void run() {
                //发送ESC查询打印机状态指令
                sendCommand = esc;
                Vector<Byte> data = new Vector<>(esc.length);
                for (int i = 0; i < esc.length; i++) {
                    data.add(esc[i]);
                }
                sendDataImmediately(data); //发送esc数据
                //开启计时器，隔2000毫秒没有没返回值时发送TSC查询打印机状态指令
                final ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder("Timer");
                final ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1, threadFactoryBuilder);
                scheduledExecutorService.schedule(threadFactoryBuilder.newThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("--------","Command1-----------");
                        if (currentPrinterCommand == null || currentPrinterCommand != PrinterCommand.ESC) {
                            Log.e(TAG, Thread.currentThread().getName());
                            //发送TSC查询打印机状态指令
                            sendCommand = tsc;
                            Vector<Byte> data = new Vector<>(tsc.length);
                            for (int i = 0; i < tsc.length; i++) {
                                data.add(tsc[i]);
                            }
                            sendDataImmediately(data);
                            //开启计时器，隔2000毫秒没有没返回值时发送CPCL查询打印机状态指令
                            scheduledExecutorService.schedule(threadFactoryBuilder.newThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("--------","Command2-----------");
                                    if (currentPrinterCommand == null || (currentPrinterCommand != PrinterCommand.ESC && currentPrinterCommand != PrinterCommand.TSC)) {
                                        Log.e(TAG, Thread.currentThread().getName());
                                        //发送CPCL查询打印机状态指令
                                        sendCommand = cpcl;
                                        Vector<Byte> data = new Vector<Byte>(cpcl.length);
                                        for (int i = 0; i < cpcl.length; i++) {
                                            data.add(cpcl[i]);
                                        }
                                        sendDataImmediately(data);
                                        //开启计时器，隔2000毫秒打印机没有响应者停止读取打印机数据线程并且关闭端口
                                        scheduledExecutorService.schedule(threadFactoryBuilder.newThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Log.d("--------","Command3-----------");
                                                if (currentPrinterCommand == null) {
                                                    Log.d("---------", "--Print--------12");
                                                    if (reader != null) {
                                                        reader.cancel();
                                                        mPort.closePort();
                                                        isOpenPort = false;
                                                        mPort = null;
                                                        sendStateBroadcast(CONN_STATE_FAILED);
                                                    }
                                                }
                                            }
                                        }), 2000, TimeUnit.MILLISECONDS);
                                    }
                                }
                            }), 2000, TimeUnit.MILLISECONDS);
                        }
                    }
                }), 2000, TimeUnit.MILLISECONDS);
            }
        });
    }

    class PrinterReader extends Thread {
        private boolean isRun = false;

        private byte[] buffer = new byte[100];

        public PrinterReader() {
            isRun = true;
        }

        @Override
        public void run() {
            try {
                Log.d("---------", "Print4-----------");
                while (isRun) {
                    //读取打印机返回信息
                    int len = readDataImmediately(buffer);
                    if (len > 0) {
                        Message message = Message.obtain();
                        message.what = READ_DATA;
                        Bundle bundle = new Bundle();
                        bundle.putInt(READ_DATA_CNT, len); //数据长度
                        bundle.putByteArray(READ_BUFFER_ARRAY, buffer); //数据
                        message.setData(bundle);
                        mHandler.sendMessage(message);
                        Log.d("---------", "Print5-----------");

                    }
                }
                Log.d("---------", "Print6-----------");
            } catch (Exception e) {
                Log.d("---------", "Print---------13--");
                if (deviceConnFactoryManagers[id] != null) {
                    try {
                        closePort(id);
                    }catch (Exception e1){

                    }

                }
            }
        }

        public void cancel() {
            isRun = false;
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case READ_DATA:
                    int cnt = msg.getData().getInt(READ_DATA_CNT); //数据长度 >0;
                    byte[] buffer = msg.getData().getByteArray(READ_BUFFER_ARRAY);  //数据
                    //这里只对查询状态返回值做处理，其它返回值可参考编程手册来解析
                    if (buffer == null) {
                        return;
                    }
                    int result = judgeResponseType(buffer[0]); //数据右移
                    String status = App.getContext().getString(R.string.str_printer_conn_normal);
                    if (sendCommand == esc) {
                        //设置当前打印机模式为ESC模式
                        if (currentPrinterCommand == null) {
                            currentPrinterCommand = PrinterCommand.ESC;
                            sendStateBroadcast(CONN_STATE_CONNECTED);
                        } else {//查询打印机状态
                            if (result == 0) {//打印机状态查询
                                Intent intent = new Intent(ACTION_QUERY_PRINTER_STATE);
                                intent.putExtra(DEVICE_ID, id);
                                App.getContext().sendBroadcast(intent);
                            } else if (result == 1) {//查询打印机实时状态
                                if ((buffer[0] & ESC_STATE_PAPER_ERR) > 0) {
                                    status += " " + App.getContext().getString(R.string.str_printer_out_of_paper);
                                }
                                if ((buffer[0] & ESC_STATE_COVER_OPEN) > 0) {
                                    status += " " + App.getContext().getString(R.string.str_printer_open_cover);
                                }
                                if ((buffer[0] & ESC_STATE_ERR_OCCURS) > 0) {
                                    status += " " + App.getContext().getString(R.string.str_printer_error);
                                }
                                System.out.println(App.getContext().getString(R.string.str_state) + status);
                                String mode = App.getContext().getString(R.string.str_printer_printmode_esc);
                                Log.d("----------","Print status:" + status);
//                                ToastUtil.CenterShow(mode + " " + status);
                            }
                        }
                    } else if (sendCommand == tsc) {
                        //设置当前打印机模式为TSC模式
                        if (currentPrinterCommand == null) {
                            currentPrinterCommand = PrinterCommand.TSC;
                            sendStateBroadcast(CONN_STATE_CONNECTED);
                        } else {
                            if (cnt == 1) {//查询打印机实时状态
                                if ((buffer[0] & TSC_STATE_PAPER_ERR) > 0) {//缺纸
                                    status += " " + App.getContext().getString(R.string.str_printer_out_of_paper);
                                }
                                if ((buffer[0] & TSC_STATE_COVER_OPEN) > 0) {//开盖
                                    status += " " + App.getContext().getString(R.string.str_printer_open_cover);
                                }
                                if ((buffer[0] & TSC_STATE_ERR_OCCURS) > 0) {//打印机报错
                                    status += " " + App.getContext().getString(R.string.str_printer_error);
                                }
                                System.out.println(App.getContext().getString(R.string.str_state) + status);
                                String mode = App.getContext().getString(R.string.str_printer_printmode_tsc);
//                                ToastUtil.CenterShow(mode + " " + status);
                                Log.d("----------","Print status2:" + status);
                            } else {//打印机状态查询
                                Intent intent = new Intent(ACTION_QUERY_PRINTER_STATE);
                                intent.putExtra(DEVICE_ID, id);
                                App.getContext().sendBroadcast(intent);
                            }
                        }
                    } else if (sendCommand == cpcl) {
                        if (currentPrinterCommand == null) {
                            currentPrinterCommand = PrinterCommand.CPCL;
                            sendStateBroadcast(CONN_STATE_CONNECTED);
                        } else {
                            if (cnt == 1) {
                                System.out.println(App.getContext().getString(R.string.str_state) + status);
                                if ((buffer[0] == CPCL_STATE_PAPER_ERR)) {//缺纸
                                    status += " " + App.getContext().getString(R.string.str_printer_out_of_paper);
                                }
                                if ((buffer[0] == CPCL_STATE_COVER_OPEN)) {//开盖
                                    status += " " + App.getContext().getString(R.string.str_printer_open_cover);
                                }
                                String mode = App.getContext().getString(R.string.str_printer_printmode_cpcl);
                                Log.d("----------","Print status3:" + status);
//                                ToastUtil.CenterShow(mode + " " + status);
                            } else {//打印机状态查询
                                Intent intent = new Intent(ACTION_QUERY_PRINTER_STATE);
                                intent.putExtra(DEVICE_ID, id);
                                App.getContext().sendBroadcast(intent);
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private void sendStateBroadcast(int state) {
        Intent intent = new Intent(ACTION_CONN_STATE);
        intent.putExtra(STATE, state);
        intent.putExtra(DEVICE_ID, id);
        App.getContext().sendBroadcast(intent);
    }

    /**
     * 判断是实时状态（10 04 02）还是查询状态（1D 72 01）
     */
    private int judgeResponseType(byte r) {
        return (byte) ((r & FLAG) >> 4);
    }

    BroadcastReceiver usbStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case ACTION_USB_DEVICE_DETACHED:
                    sendStateBroadcast(CONN_STATE_DISCONNECT);
                    break;
                default:
                    break;
            }
        }
    };

    boolean printAble = true;
    int activCount;
    boolean printAble2 = true;
    int activCount2;


//    public void onConnect(ThreadPool threadPool, BillViewModel model, boolean openBox, int n) {
//        int id = 0;
//        final boolean[] runing = {false};
//
////        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].closePort(id);
//        Log.d("---------", "Print0-----------");
//        Log.d("---------", "Print1-----1------ConnState:" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState());
//        Log.d("---------", "Print1-----1------OpenPort:" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].isOpenPort);
//        if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].isOpenPort) {
////            Log.d("---------", "Print1-----1------ return:" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].isOpenPort);
//            return;
//        }
////        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].openPort();
//        threadPool.addTask(new Runnable() {
//            @Override
//            public void run() {
//                DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].openPort();
//            }
//        });
//        Log.d("---------", "Print1-----2------ConnState:" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState());
//        Log.d("---------", "Print1-----2------OpenPort:" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].isOpenPort);
//        if (!DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState()) {
//            if (!runing[0]) {
//                runing[0] = true;
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.d("---------", "Print1-----2.1------ConnState:" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState());
//                        Log.d("---------", "Print1-----2.1------OpenPort:" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].isOpenPort);
////                        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].openPort();
//                        while (!DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState()) {
//
//                            Log.d("------", id + "Print1-----2.2-----打印机状态:" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState());
//                            try {
//                                Thread.sleep(1000);
//                                if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState()) {
//                                    Log.d("------", id + "Print1-----2.2-----打印机状态:" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState());
//                                    onPrint(threadPool, model, openBox, n);
//                                    runing[0] = false;
//                                }
//                            } catch (InterruptedException e) {
//
//                            }
////                            ToastUtil.CenterShow("打印繁忙...");
//                        }
//                    }
//                }).start();
//            }
//
//        } else {
//            onPrint(threadPool, model, openBox, n);
//        }
//
////        onPrint(threadPool, model, openBox, n);
//    }


    public void onConnect(ThreadPool threadPool, BillViewModel model, boolean openBox, int n) {
        Log.d("---------", "Print0-----------" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0].getConnState());
        Log.d("---------", "Print0-----------" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0].getPort());
        Log.d("---------", "Print0-----------" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0].getConnMethod());
        Log.d("---------", "Print0-----------" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0].isOpenPort);
        if (!DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState()) {
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
        onPrint(threadPool, model, openBox, n);
    }

    public void onPrint(ThreadPool threadPool, BillViewModel model, boolean openBox, int n) {

        if (!DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState()) {
            return;
        }

        threadPool.addTask(new Runnable() {
            @Override
            public void run() {
//                if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0].getCurrentPrinterCommand() == PrinterCommand.ESC) {
                switch (n) {
                    case 1:
                        Log.d("---------", "Print3-----------");
                        sendReceiptWithResponse(model, openBox);
                        Log.d("---------", "Print7-----------");
                        break;

                    //菜单详情
                    case 2:
                        NowprintOrder(model, openBox);
                        break;

                    //退菜
                    case 3:
                        btnPrintOrder(model, openBox, 0);
                        break;
                    case 7:
                        btnPrintOrder2(model, openBox, 0);
                        break;

//                        //后厨小票
//                        case 4:
//                            btnPrintHouchu(model, openBox);
//                            break;

                    //領収書
                    case 5:
                        sendReceiptWithResponse2(model, openBox);
                        break;

                    case 6:
                        printDingdan(model, openBox);
                        break;

                    //日计表
                    case 8:
                        rijiPrint(model, openBox);
                        break;

                    //日计表
                    case 9:
                        rijiPrint2(model, openBox);
                        break;

                    //日计表
                    case 10:
                        AAprintOrder(model, openBox);
                        break;
                }

            }
//            }
        });

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.d("--------", "----thread----------" + id);
//                while (threadPool.getPoolSize() != 0) {
//                    try {
//                        Thread.sleep(2000);
//                        Log.d("--------", "----thread----PoolSize:" + threadPool.getPoolSize());
//                        if (threadPool.getPoolSize() == 0) {
//                            if (deviceConnFactoryManagers[id] != null) {
//                                closePort(id);
//                                if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState()) {
//                                    ToastUtil.CenterShow("打印断开失败");
//                                }
//
//                            }
//                        }
//                    } catch (InterruptedException e) {
//
//                    }
//                }
//            }
//        }).start();

    }


    //10.10
//    public void onConnect(ThreadPool threadPool, int id, BillViewModel model, boolean openBox, String ip, int n) {
//        final boolean[] runing = {false};
//        Log.d("---------", id + "Print0-----------");
//        Log.d("---------", id + "Print1-----1------ConnState:" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState());
//        Log.d("---------", id + "Print1-----1------OpenPort:" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].isOpenPort);
//        if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].isOpenPort) {
//            Log.d("---------", "Print1-----1------ return:" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].isOpenPort);
//            return;
//        }
////        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].openPort();
//        threadPool.addTask(new Runnable() {
//            @Override
//            public void run() {
//                DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].openPort();
//            }
//        });
//        Log.d("---------", id + "Print1-----2------ConnState:" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState());
//        Log.d("---------", id + "Print1-----2------OpenPort:" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].isOpenPort);
//        if (!DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState()) {
//            if (!runing[0]) {
//                runing[0] = true;
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.d("---------", id + "Print1-----2.1------ConnState:" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState());
//                        Log.d("---------", id + "Print1-----2.1------OpenPort:" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].isOpenPort);
////                        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].openPort();
//                        while (!DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState()) {
//
//                            Log.d("------", id + "Print1-----2.2-----打印机状态:" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState());
//                            try {
//                                Thread.sleep(1000);
//                                if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState()) {
//                                    Log.d("------", id + "Print1-----2.2-----打印机状态:" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState());
//                                    onPrint2(threadPool, id, model, openBox, ip, n);
//                                    runing[0] = false;
//                                }
//                            } catch (InterruptedException e) {
//
//                            }
//                        }
//                    }
//                }).start();
//            }
//
//        } else {
//            onPrint2(threadPool, id, model, openBox, ip, n);
//        }
//
//
//    }

    //11-26
    public void onConnect(ThreadPool threadPool, int id, BillViewModel model, boolean openBox, String ip, int n) {
        onPrint2(threadPool, id, model, openBox, ip, n);
    }

    public void onPrint2(ThreadPool threadPool, int id, BillViewModel model, boolean openBox, String ip, int n) {
        if (!DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState()) {
            return;
        }
        threadPool.addTask(new Runnable() {
            @Override
            public void run() {
                switch (n) {
                    //后厨小票
                    case 1:
                        btnPrintHouchu(model, openBox, id, ip);
                        break;

                    //退菜
                    case 2:
                        btnPrintOrder2(model, openBox, id);
                        break;
                }
            }
        });


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.d("--------", "----thread----------" + id);
//                while (threadPool.getPoolSize() != 0) {
//                    try {
//                        Thread.sleep(2000);
//                        Log.d("--------", "----thread----PoolSize:" + threadPool.getPoolSize());
//                        if (threadPool.getPoolSize() == 0) {
//                            if (deviceConnFactoryManagers[id] != null) {
//                                closePort(id);
//                            }
//                        }
//                    } catch (InterruptedException e) {
//
//                    }
//                }
//            }
//        }).start();
    }


//    public void onConnect2(int id, BillViewModel model, boolean openBox, int n) {
//        activCount = 0;
//        if (printAble) {
//            printAble = false;
//
//            ThreadPool threadPool = ThreadPool.getInstantiation();
//            if (!DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState()) {
//                threadPool.addTask(new Runnable() {
//                    @Override
//                    public void run() {
//                        while (!DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState()) {
//                            // Log.d("------", id + "打印机状态:" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState());
//                            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].openPort();
//                            try {
//                                Thread.sleep(1000);
//                                if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState()) {
//                                    Log.d("------", id + "打印机状态:" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState());
//                                    onPrint2(threadPool, id, model, openBox, n);
//                                }
//                            } catch (InterruptedException e) {
//
//                            }
//                        }
//                    }
//                });
//            } else {
//                onPrint2(threadPool, id, model, openBox, n);
//            }
//        }
//    }



    // 打印下单
    public void btnPrintHouchu(BillViewModel model, boolean openBox, int id, String ip) {

        if (model.houchuDetail.get() != null) {
            HouChuPrintEntity Houbean = model.houchuDetail.get();
            Log.d("------", "Houchu size:" + Houbean.getBackend_info().size());
            Log.d("------", "Houchu id:" + id);

            for (HouChuPrintEntity.BackendInfoBean backendInfoBean:Houbean.getBackend_info()){
                if(backendInfoBean.getIp().equals(ip)) {
                    for (HouChuPrintEntity.BackendInfoBean.MenuBean houchuMenus : backendInfoBean.getMenu()) {
                        EscCommand esc = new EscCommand();
                        esc.addInitializePrinter();//初始化打印机
                        esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
                        esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                                EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON,
                                EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);//00110
                        esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.ON);//放大字体
                        esc.addText(Houbean.getSeat_info().getNumber());
                        esc.addPrintAndLineFeed();//功能：打印并换行
//                esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
                        esc.addPrintAndLineFeed();//功能：打印并换行

                        esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                                EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF,
                                EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);//00110
                        esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.OFF);//放大字体
                        esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
                        esc.addTurnDoubleStrikeOnOrOff(EscCommand.ENABLE.OFF);//是否反白打印on反白----24多少次想要放弃，多少次
                        esc.addText(Houbean.getOrder_info().getOpened_at());
                        esc.addHorTab();
                        esc.addHorTab();
                        esc.addHorTab();
                        esc.addText(Houbean.getSeat_info().getCount() + "人");
                        esc.addPrintAndLineFeed();//功能：打印并换行
                        esc.addText("-----------------------------------------------");
                        esc.addPrintAndLineFeed();//功能：打印并换行
//                esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
                        esc.addPrintAndLineFeed();//功能：打印并换行

                        esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                                EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON,
                                EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);//00110
                        bigFormat(esc, houchuMenus.getName(), String.valueOf(houchuMenus.getCount()));
                        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
                        if (houchuMenus.getAttributes() != null) {
                            if (!houchuMenus.getAttributes().trim().equals("")) {
                                esc.addText("(" + houchuMenus.getAttributes() + ")");
                            }
                        }

                        esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                                EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF,//
                                EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);//00110
                        esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.OFF);//不放大字体---

                        esc.addPrintAndLineFeed();//功能：打印并换行
                        esc.addPrintAndLineFeed();//功能：打印并换行
                        esc.addPrintAndLineFeed();//功能：打印并换行
//                esc.addPrintAndFeedLines((byte) 2);//打印并走纸 n 行

                        esc.addPrintAndFeedLines((byte) 5);//打印并走纸 n 行
                        esc.addCutPaper();//半切纸并走纸，这条命令只在行首有效
//        /* 加入查询打印机状态，用于连续打印 */

                        byte[] bytes = {29, 114, 1};
                        esc.addUserCommand(bytes);
                        Vector<Byte> datas = esc.getCommand();
                        /* 发送数据 */
                        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].sendDataImmediately(datas);

                    }
                }
            }
        }

    }

    // 打印退菜的详情
    public void btnPrintOrder(BillViewModel model, boolean openBox, int id) {

        if (model.RejectedDetail.get() != null) {
            RejectedEntity bean = model.RejectedDetail.get();
            EscCommand esc = new EscCommand();
            esc.addInitializePrinter();//初始化打印机

            esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.ON);//放大字体
            esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
            esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON,
                    EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);//00110
            esc.addText(bean.getSeat_info().getNumber());
            esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);//00110
            esc.addPrintAndLineFeed();//功能：打印并换行
//            esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
            esc.addPrintAndLineFeed();//功能：打印并换行

            esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.OFF);//放大字体
            esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
            esc.addTurnDoubleStrikeOnOrOff(EscCommand.ENABLE.OFF);//是否反白打印on反白----24多少次想要放弃，多少次
            esc.addText(bean.getPrint_time());
            esc.addHorTab();
            esc.addHorTab();
            esc.addHorTab();
            esc.addText(bean.getSeat_info().getCount() + "人");
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addText("-----------------------------------------------");
            esc.addPrintAndLineFeed();//功能：打印并换行
//            esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
            esc.addPrintAndLineFeed();//功能：打印并换行

            bigFormat(esc, bean.getMenu_info().getName(), String.valueOf(bean.getBefore_count()));
            esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
            esc.addText("(" + bean.getAttributes() + ")");


            esc.addPrintAndLineFeed();//功能：打印并换行
//            esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
            esc.addTurnReverseModeOnOrOff(EscCommand.ENABLE.ON);//是否反白打印on反白----24
            esc.addText("订正:");
            esc.addHorTab();
            esc.addHorTab();
            esc.addHorTab();
            esc.addHorTab();
            esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON,
                    EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);//00110


            if (bean.getCount().length() > 3) {
                esc.addText(String.valueOf(bean.getBefore_count() - 1));
            } else if (bean.getCount().length() > 2) {
                esc.addTurnReverseModeOnOrOff(EscCommand.ENABLE.OFF);//是否反白打印on反白----24
                esc.addText(" ");
                esc.addTurnReverseModeOnOrOff(EscCommand.ENABLE.ON);//是否反白打印on反白----24
                esc.addText(String.valueOf(bean.getBefore_count() - 1));
            } else {
                esc.addTurnReverseModeOnOrOff(EscCommand.ENABLE.OFF);//是否反白打印on反白----24
                esc.addText("  ");
                esc.addTurnReverseModeOnOrOff(EscCommand.ENABLE.ON);//是否反白打印on反白----24
                esc.addText(String.valueOf(bean.getBefore_count() - 1));
            }
//            esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF,//
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);//00110
            esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.OFF);//不放大字体---

            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addPrintAndLineFeed();//功能：打印并换行
//            esc.addPrintAndFeedLines((byte) 2);//打印并走纸 n 行

            esc.addPrintAndFeedLines((byte) 5);//打印并走纸 n 行
            esc.addCutPaper();//半切纸并走纸，这条命令只在行首有效
//        /* 加入查询打印机状态，用于连续打印 */

            byte[] bytes = {29, 114, 1};
            esc.addUserCommand(bytes);
            Vector<Byte> datas = esc.getCommand();
            /* 发送数据 */
            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].sendDataImmediately(datas);

        }

    }

    // 打印退菜的详情
    public void btnPrintOrder2(BillViewModel model, boolean openBox, int id) {

        if (model.RejectedDetail.get() != null) {
            RejectedEntity bean = model.RejectedDetail.get();
            EscCommand esc = new EscCommand();
            esc.addInitializePrinter();//初始化打印机

            esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
            esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.ON);//放大字体
            esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON,
                    EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);//00110
            esc.addText(bean.getSeat_info().getNumber());
            esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);//00110
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addPrintAndLineFeed();//功能：打印并换行
//            esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行

            esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.OFF);//放大字体
            esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
            esc.addTurnDoubleStrikeOnOrOff(EscCommand.ENABLE.OFF);//是否反白打印on反白----24多少次想要放弃，多少次
            esc.addText(bean.getPrint_time());
            esc.addHorTab();
            esc.addHorTab();
            esc.addHorTab();
            esc.addText(bean.getSeat_info().getCount() + "人");
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addText("-----------------------------------------------");
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addPrintAndLineFeed();//功能：打印并换行
//            esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
            esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.ON);//放大字体
            esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON,
                    EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);//00110
            bigFormat(esc, bean.getMenu_info().getName(), String.valueOf(bean.getBefore_count()));
            esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);


            esc.addPrintAndLineFeed();//功能：打印并换行
//            esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
            esc.addTurnReverseModeOnOrOff(EscCommand.ENABLE.ON);//是否反白打印on反白----24
            esc.addText("订正:");
            esc.addHorTab();
            esc.addHorTab();
            esc.addHorTab();
            esc.addHorTab();


            if (bean.getCount().length() > 3) {
                esc.addText(String.valueOf(bean.getBefore_count() - 1));
            } else if (bean.getCount().length() > 2) {
                esc.addTurnReverseModeOnOrOff(EscCommand.ENABLE.OFF);//是否反白打印on反白----24
                esc.addText(" ");
                esc.addTurnReverseModeOnOrOff(EscCommand.ENABLE.ON);//是否反白打印on反白----24
                esc.addText(String.valueOf(bean.getBefore_count() - 1));
            } else {
                esc.addTurnReverseModeOnOrOff(EscCommand.ENABLE.OFF);//是否反白打印on反白----24
                esc.addText("  ");
                esc.addTurnReverseModeOnOrOff(EscCommand.ENABLE.ON);//是否反白打印on反白----24
                esc.addText(String.valueOf(bean.getBefore_count() - 1));
            }
//            esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
            esc.addPrintAndLineFeed();//功能：打印并换行

            esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF,//
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);//00110
            esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.OFF);//不放大字体---

            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addPrintAndLineFeed();//功能：打印并换行
//            esc.addPrintAndFeedLines((byte) 2);//打印并走纸 n 行

            esc.addPrintAndFeedLines((byte) 5);//打印并走纸 n 行
            esc.addCutPaper();//半切纸并走纸，这条命令只在行首有效
//        /* 加入查询打印机状态，用于连续打印 */

            byte[] bytes = {29, 114, 1};
            esc.addUserCommand(bytes);
            Vector<Byte> datas = esc.getCommand();
            /* 发送数据 */
            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].sendDataImmediately(datas);

        }

    }

    public void bigFormat(EscCommand esc, String name, String count) {
        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);//
        esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.ON);//放大字体
        int tabCount = (11 - name.length() % 12) / 2 - 1;
        Log.d("--------", "tabcount:" + tabCount);
        if (name.length() % 10 == 0 || name.length() % 11 == 0 || name.length() % 12 == 0) {
            esc.addText(name);//菜名
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addSelectJustification(EscCommand.JUSTIFICATION.RIGHT);//
            esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON,
                    EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);//00110
            esc.addText(count);
        } else {
            esc.addText(name);//菜名
            for (int i = tabCount; i >= 0; i--) {
                esc.addHorTab();
            }
            esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON,
                    EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);//00110
            if (count.length() > 3) {
                esc.addText(count + "  ");
            } else if (count.length() > 2) {
                esc.addText(" " + count);
            } else {
                esc.addText("  " + count);
            }
        }

        esc.addPrintAndLineFeed();//功能：打印并换行

    }

    public void NowprintOrder2(BillViewModel model, boolean openBox, int id) {

        if (model.houchuDetail.get() != null) {
            HouChuPrintEntity Houbean = model.houchuDetail.get();
            EscCommand esc = new EscCommand();
            esc.addInitializePrinter();//初始化打印机


            esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
            esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.ON);//放大字体
            esc.addText(Houbean.getMerchant().getName());
            esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.OFF);//不放大字体
            esc.addPrintAndLineFeed();//功能：打印并换行
//            esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
            esc.addPrintAndLineFeed();//功能：打印并换行

            esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
            esc.addText("テーブル：" + Houbean.getSeat_info().getNumber());
            esc.addHorTab();
            esc.addHorTab();
            esc.addText(Houbean.getSeat_info().getCount() + "人");
            esc.addPrintAndLineFeed();//功能：打印并换行

            esc.addText("-----------------------------------------------");
//            esc.addPrintAndFeedLines((byte) 2);//打印并走纸 n 行
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addPrintAndLineFeed();//功能：打印并换行
            List<HouChuPrintEntity.MenuInfoBean> HouOrderList = Houbean.getMenu_info();//这个事获取的AA人数
            for (int i = 0; i < HouOrderList.size(); i++) {
                // 这个事获取的AA人数的菜数aaOrderList_i
                esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);


                smallFromat(esc, HouOrderList.get(i).getName());
                esc.addText(HouOrderList.get(i).getCount());
                esc.addHorTab();

                esc.addText("￥" + HouOrderList.get(i).getPrice_format());
                esc.addHorTab();
//                esc.addPrintAndLineFeed();//功能：打印并换行
//                esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
                if (HouOrderList.get(i).getAttributes().equals("")) {
                    esc.addPrintAndLineFeed();//功能：打印并换行
//                    esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
                    esc.addPrintAndLineFeed();//功能：打印并换行

                } else {
                    esc.addPrintAndLineFeed();//功能：打印并换行
                    esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
                    esc.addText("(" + HouOrderList.get(i).getAttributes() + ")");
                    esc.addPrintAndLineFeed();//功能：打印并换行
//                    esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
                    esc.addPrintAndLineFeed();//功能：打印并换行
                    esc.addPrintAndLineFeed();//功能：打印并换行
                }


            }

//            esc.addPrintAndFeedLines((byte) 2);//打印并走纸 n 行
            esc.addText("-----------------------------------------------");
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addPrintAndLineFeed();//功能：打印并换行
//            esc.addPrintAndFeedLines((byte) 2);//打印并走纸 n 行

            esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
            esc.addTurnEmphasizedModeOnOrOff(EscCommand.ENABLE.ON);
            esc.addText("ご来店時刻：" + Houbean.getOrder_info().getOpened_at());
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
            esc.addTurnEmphasizedModeOnOrOff(EscCommand.ENABLE.ON);
            esc.addText("印刷時刻：" + Houbean.getPrint_time());
            esc.addPrintAndLineFeed();//功能：打印并换行


            esc.addPrintAndFeedLines((byte) 5);//打印并走纸 n 行
            esc.addCutPaper();//半切纸并走纸，这条命令只在行首有效
//        /* 加入查询打印机状态，用于连续打印 */

            byte[] bytes = {29, 114, 1};
            esc.addUserCommand(bytes);
            Vector<Byte> datas = esc.getCommand();
            /* 发送数据 */
            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].sendDataImmediately(datas);
        }

    }


    public void NowprintOrder(BillViewModel model, boolean openBox) {

        id = 0;

        if (model.houchuDetail.get() != null) {
            HouChuPrintEntity Houbean = model.houchuDetail.get();
            EscCommand esc = new EscCommand();
            esc.addInitializePrinter();//初始化打印机


            esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
            esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.ON);//放大字体
            esc.addText(Houbean.getMerchant().getName());
            esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.OFF);//不放大字体
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行


            esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
            esc.addText("テーブル：" + Houbean.getSeat_info().getNumber());
            esc.addHorTab();
            esc.addHorTab();
            esc.addText(Houbean.getSeat_info().getCount() + "人");
            esc.addPrintAndLineFeed();//功能：打印并换行

            esc.addText("-----------------------------------------------");
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
            List<HouChuPrintEntity.MenuInfoBean> HouOrderList = Houbean.getMenu_info();//这个事获取的AA人数
            if (HouOrderList != null) {
                for (int i = 0; i < HouOrderList.size(); i++) {
                    // 这个事获取的AA人数的菜数aaOrderList_i
                    esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);


                    smallFromat(esc, HouOrderList.get(i).getName());
                    esc.addText(HouOrderList.get(i).getCount());
                    esc.addHorTab();

                    esc.addText("￥" + HouOrderList.get(i).getPrice_format());
                    esc.addHorTab();
//                esc.addPrintAndLineFeed();//功能：打印并换行
//                esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行

                    if (HouOrderList.get(i).getAttributes().equals("")) {
                        esc.addPrintAndLineFeed();//功能：打印并换行
                        esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
//                        esc.addPrintAndLineFeed();//功能：打印并换行


                    } else {
//                        esc.addPrintAndLineFeed();//功能：打印并换行
                        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
                        esc.addText("(" + HouOrderList.get(i).getAttributes() + ")");
                        esc.addPrintAndLineFeed();//功能：打印并换行
                        esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
//                        esc.addPrintAndLineFeed();//功能：打印并换行
                    }


                }
            }


            esc.addText("-----------------------------------------------");
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addPrintAndFeedLines((byte) 2);//打印并走纸 n 行
//            esc.addPrintAndLineFeed();//功能：打印并换行
//            esc.addPrintAndLineFeed();//功能：打印并换行

            esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
            esc.addTurnEmphasizedModeOnOrOff(EscCommand.ENABLE.ON);
            esc.addText("ご来店時刻：" + Houbean.getOrder_info().getOpened_at());
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addText("印刷時刻：" + Houbean.getPrint_time());


            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addPrintAndFeedLines((byte) 5);//打印并走纸 n 行
            esc.addCutPaper();//半切纸并走纸，这条命令只在行首有效
////        /* 加入查询打印机状态，用于连续打印 */

            byte[] bytes = {29, 114, 1};
            esc.addUserCommand(bytes);
            Vector<Byte> datas = esc.getCommand();
            /* 发送数据 */
            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].sendDataImmediately(datas);
        }

    }


    public void smallFromat(EscCommand esc, String name) {
        int tabCount = 3 - name.length() / 4;
        esc.addText(name);
        for (int i = tabCount; i >= 0; i--) {
            esc.addHorTab();
        }
    }

    /**
     * 发送票据
     */
    void sendReceiptWithResponse(BillViewModel model, boolean openBox) {
        EscCommand esc = new EscCommand();
        esc.addInitializePrinter();//初始化打印机
        esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行

        if (model != null) {


            if (model.printQr.get() != null) {
                //打印并换行
                esc.addPrintAndLineFeed();
                QrcodeEntity bean = model.printQr.get();

                URL fileUrl = null;
                Bitmap bitmap = null;

                try {
                    fileUrl = new URL(bean.getPath());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection) fileUrl
                            .openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
                esc.addRastBitImage(bitmap, 420, 0);

//                esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
                esc.addPrintAndLineFeed();//功能：打印并换行
                esc.addPrintAndLineFeed();//功能：打印并换行
                esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.ON);
                esc.addSelectPrintModes(EscCommand.FONT.FONTB,
                        EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON,
                        EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);//00110
                esc.addText(bean.getNumber());
                esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);

                esc.addPrintAndLineFeed();//功能：打印并换行
                esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
                esc.addText(bean.getPrint_time());
                esc.addPrintAndLineFeed();//功能：打印并换行
            }

        }
        esc.addPrintAndFeedLines((byte) 6);//打印并走纸 n 行
        esc.addCutPaper();//半切纸并走纸，这条命令只在行首有效
//        /* 加入查询打印机状态，用于连续打印 */

        Log.d("--------openBox", openBox + "");
        if (openBox) {
//                // 开钱箱
            esc.addGeneratePlus(LabelCommand.FOOT.F2, (byte) 255, (byte) 255);
        }

        byte[] bytes = {29, 114, 1};
        esc.addUserCommand(bytes);
        Vector<Byte> datas = esc.getCommand();
        /* 发送数据 */
        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].sendDataImmediately(datas);
    }

    /**
     * 发送票据
     */
    void sendReceiptWithResponse2(BillViewModel model, boolean openBox) {

        EscCommand esc = new EscCommand();
        esc.addInitializePrinter();//初始化打印机

        if (model.LingshoushuDetail.get() != null) {
            esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
            esc.addText(model.LingshoushuDetail.get().getCreated_at());
            esc.addHorTab();
            esc.addText("  No." + model.LingshoushuDetail.get().getSn());
//        esc.addHorTab();
//        esc.addSelectPrintModes(EscCommand.FONT.FONTA,
//                EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON,
//                EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);//00110
//        esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addPrintAndFeedLines((byte) 2);//打印并走纸 n 行
//        esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
            esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.ON);//放大字体
            esc.addText("領収書");
//            esc.addPrintAndLineFeed();//功能：打印并换行
//        esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.OFF);//不放大字体
            esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
            esc.addPrintAndLineFeed();//功能：打印并换行

            esc.addSelectJustification(EscCommand.JUSTIFICATION.RIGHT);
            esc.addText("様");
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON,
                    EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);//00110
            esc.addText("------------------------");

            esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
//        esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.ON);//放大字体
            esc.addText("￥");

            esc.addText(model.LingshoushuDetail.get().getPrice());
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
//        esc.addPrintAndLineFeed();//功能：打印并换行

            esc.addText("------------------------");
            esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);//00110
            esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.OFF);//不放大字体
            esc.addSelectJustification(EscCommand.JUSTIFICATION.RIGHT);
            esc.addText("(消费税等 ￥");
            esc.addText(model.LingshoushuDetail.get().getTax_fee() + ")");
            esc.addPrintAndLineFeed();//功能：打印并换行

            esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
            esc.addText("但し、食事代として");
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addText("上記正に領収致しました。");
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addPrintAndFeedLines((byte) 2);//打印并走纸 n 行
//        esc.addPrintAndLineFeed();//功能：打印并换行
//        esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addSelectJustification(EscCommand.JUSTIFICATION.RIGHT);
            esc.addText("担当者：");
            esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON);//00110
            esc.addTurnUnderlineModeOnOrOff(EscCommand.UNDERLINE_MODE.UNDERLINE_2DOT);

            esc.addText("        ");
            esc.addPrintAndLineFeed();//功能：打印并换行
//        esc.addPrintAndFeedLines((byte) 2);
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addPrintAndLineFeed();//功能：打印并换行

            esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
            esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);//00110
            esc.addText(model.LingshoushuDetail.get().getMerchant_name());
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
            esc.addText(model.LingshoushuDetail.get().getMerchant_address());
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
            esc.addText("TEL：" + model.LingshoushuDetail.get().getPhone());
            esc.addPrintAndLineFeed();//功能：打印并换行
        }

////        设置绝对打印位置
//        esc.addSetAbsolutePrintPosition((short) 10);
//打印并换行
        esc.addPrintAndLineFeed();
        esc.addPrintAndFeedLines((byte) 5);//打印并走纸 n 行
        esc.addCutPaper();//半切纸并走纸，这条命令只在行首有效
//        /* 加入查询打印机状态，用于连续打印 */
        byte[] bytes = {29, 114, 1};
        esc.addUserCommand(bytes);
        Vector<Byte> datas = esc.getCommand();
//        /* 发送数据 */
        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].sendDataImmediately(datas);
    }


    public void printDingdan(BillViewModel model, boolean openBox) {
        if (model.printMaidan.get() != null) {
            PrintMaidanlEntity bean = model.printMaidan.get();
            EscCommand esc = new EscCommand();
            esc.addInitializePrinter();//初始化打印机


            esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
            esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.ON);//放大字体
            esc.addText(bean.getMerchant().getName());
            esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.OFF);//不放大字体
//            esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
            esc.addText(bean.getMerchant().getAddress());
            esc.addPrintAndLineFeed();//功能：打印并换行
//            esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
            esc.addText("TEL：");
            esc.addText(bean.getMerchant().getPhone());
            esc.addPrintAndLineFeed();//功能：打印并换行
//            esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
            esc.addPrintAndLineFeed();//功能：打印并换行

            esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
            esc.addText(bean.getPrint_time());
            esc.addHorTab();

            esc.addSelectJustification(EscCommand.JUSTIFICATION.RIGHT);
            esc.addText("  No." + bean.getOrder_info().getSn());
            esc.addPrintAndLineFeed();//功能：打印并换行


            //桌号以及人数
            esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
            esc.addText("No.  ");
            esc.addText(bean.getSeat_info().getNumber());
            esc.addHorTab();

            esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
            esc.addText("  人数   " + bean.getSeat_info().getCount());
//            esc.addPrintAndFeedLines((byte) 2);//打印并走纸 n 行
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addPrintAndLineFeed();//功能：打印并换行
            List<PrintMaidanlEntity.MenuInfo> menuInfoList = bean.getMenu_info();
            for (int i = 0; i < menuInfoList.size(); i++) {
                esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
                smallFromat(esc, menuInfoList.get(i).getName());
                esc.addText(menuInfoList.get(i).getCount() + "");
                esc.addHorTab();

                esc.addText("￥" + menuInfoList.get(i).getTotal_format());
                esc.addHorTab();
//                esc.addPrintAndLineFeed();//功能：打印并换行
//                esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
                if (menuInfoList.get(i).getAttributes().equals("")) {
                    esc.addPrintAndLineFeed();//功能：打印并换行
                    esc.addPrintAndLineFeed();//功能：打印并换行
//                    esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行


                } else {
                    esc.addPrintAndLineFeed();//功能：打印并换行
                    esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
                    esc.addText("(" + menuInfoList.get(i).getAttributes() + ")");
                    esc.addPrintAndLineFeed();//功能：打印并换行
                    esc.addPrintAndLineFeed();//功能：打印并换行
//                    esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
                }
            }
            esc.addText("-----------------------------------------------");
            esc.addPrintAndLineFeed();//功能：打印并换行
//            esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
            esc.addPrintAndLineFeed();//功能：打印并换行

            esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
            esc.addText("小計： ");
            esc.addHorTab();
            esc.addHorTab();
            esc.addHorTab();
            esc.addHorTab();
            esc.addText("  ￥" + bean.getOrder_info().getTotal_format());
            esc.addPrintAndLineFeed();//功能：打印并换行


            //合计
            esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
            esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.ON);//放大字体

            esc.addText("合計：");
            esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.OFF);//不放大字体
            esc.addHorTab();
            esc.addHorTab();
            if(bean.getOrder_info().getPrice_format().length() < 7) {
                esc.addHorTab();
            }
            esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.OFF);//不放大字体
            esc.addText("￥");
            esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON,
                    EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);//0011
            esc.addText(bean.getOrder_info().getPrice_format());
            esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);//0011
            esc.addPrintAndLineFeed();//功能：打印并换行
            //10.00%

            esc.addHorTab();
            esc.addText(bean.getOrder_info().getTax_rate() + "%対象計 ");
            esc.addHorTab();
            esc.addHorTab();
            esc.addText("  ￥" + bean.getOrder_info().getTotal_format());
            esc.addHorTab();
            esc.addPrintAndLineFeed();//功能：打印并换行

            //内消费税

            esc.addHorTab();
            esc.addText("(内消費税 ");
            esc.addHorTab();
            esc.addHorTab();
            esc.addText("  ￥" + bean.getOrder_info().getTax_fee_format() + ")");
            esc.addHorTab();

            if (bean.getOrder_info().getPayment_id() == 1) {
                //收款
//                esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
                esc.addPrintAndLineFeed();//功能：打印并换行
                esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
                esc.addText("お預かり：");
                esc.addHorTab();
                esc.addHorTab();
                esc.addHorTab();
                esc.addText("  ￥" + bean.getOrder_info().getCash_format());
                esc.addHorTab();
                //找零
//                esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
                esc.addPrintAndLineFeed();//功能：打印并换行
                esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
                esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.ON);//放大字体
                esc.addText("お釣り");
                esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.OFF);//不放大字体
                esc.addHorTab();
                esc.addHorTab();
                esc.addHorTab();

                esc.addSelectJustification(EscCommand.JUSTIFICATION.RIGHT);
                esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.ON);//放大字体
                esc.addText("￥");
                esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.OFF);//不放大字体

                esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                        EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON,
                        EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);//0011
                esc.addText(bean.getOrder_info().getReturn_format());
                esc.addHorTab();
            } else {
                //收款
//                esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
                esc.addPrintAndLineFeed();//功能：打印并换行
                esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
                esc.addText(bean.getOrder_info().getPayment_name() + ":");
                if (bean.getOrder_info().getPayment_name().length() >= 8) {
                    esc.addHorTab();
                    esc.addHorTab();
                } else {
                    esc.addHorTab();
                    esc.addHorTab();
                    esc.addHorTab();
                }
                esc.addText("  ￥" + bean.getOrder_info().getPrice_format());
            }

            esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);//0011
            esc.addPrintAndLineFeed();//功能：打印并换行

//            esc.addPrintAndFeedLines((byte) 2);//打印并走纸 n 行
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addSelectJustification(EscCommand.JUSTIFICATION.RIGHT);
            esc.addText("担当者：");
            esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON);//00110
            esc.addTurnUnderlineModeOnOrOff(EscCommand.UNDERLINE_MODE.UNDERLINE_2DOT);
            esc.addText("            ");
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addTurnUnderlineModeOnOrOff(EscCommand.UNDERLINE_MODE.UNDERLINE_2DOT);
//            esc.addSetKanjiUnderLine(EscCommand.UNDERLINE_MODE.UNDERLINE_2DOT);

            esc.addPrintAndFeedLines((byte) 5);//打印并走纸 n 行
            esc.addCutPaper();//半切纸并走纸，这条命令只在行首有效
//        /* 加入查询打印机状态，用于连续打印 */

            Log.d("--------openBox", openBox + "");
            if (openBox) {
                // 开钱箱
                esc.addGeneratePlus(LabelCommand.FOOT.F2, (byte) 255, (byte) 255);
            }

            byte[] bytes = {29, 114, 1};
            esc.addUserCommand(bytes);
            Vector<Byte> datas = esc.getCommand();
            /* 发送数据 */
            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].sendDataImmediately(datas);


        }


    }


    /**
     * 发送今日打印票据
     */
    void rijiPrint(BillViewModel model, boolean openBox) {
        EscCommand esc = new EscCommand();
        esc.addInitializePrinter();//初始化打印机
//        esc.addCutPaper();//半切纸并走纸，这条命令只在行首有效
//        esc.addCutAndFeedPaper((byte) 10);//半切纸并走纸，这条命令只在行首有效---10切纸后走纸距离
//        esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
        esc.addPrintAndLineFeed();//功能：打印并换行
        /* 设置打印居中 */
        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
        /* 设置为倍高倍宽 */
//        esc.addSelectPrintModes(EscCommand.FONT.FONTA,
//                EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON,
//                EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);//00110
        //取消倍高倍宽
        esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
        esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.ON);//放大字体
        esc.addText("日計表");
        esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.OFF);//不放大字体

        esc.addPrintAndLineFeed();//功能：打印并换行
//        esc.addPrintAndFeedLines((byte) 2);//打印并走纸 n 行
        esc.addPrintAndLineFeed();//功能：打印并换行
        esc.addPrintAndLineFeed();//功能：打印并换行
        /* 打印文字 */
        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
        esc.addText(model.PrintCashDetail.get().getMerchant().getName());
        esc.addPrintAndLineFeed();//功能：打印并换行
        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
        esc.addText(model.PrintCashDetail.get().getMerchant().getAddress());
        esc.addPrintAndLineFeed();//功能：打印并换行
        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
        esc.addText("TEL  " + model.PrintCashDetail.get().getMerchant().getPhone());
        esc.addPrintAndLineFeed();//功能：打印并换行
        esc.addText("------------------------------------------------");

        esc.addPrintAndLineFeed();//功能：打印并换行
        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
        esc.addText("出勤期間：");
        esc.addText(model.PrintCashDetail.get().getDate());
        esc.addPrintAndLineFeed();//功能：打印并换行
        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
        esc.addText("コメント：");
        esc.addText("");

        esc.addPrintAndLineFeed();//功能：打印并换行
        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);

        esc.addText("------------------------------------------------");
        esc.addPrintAndLineFeed();//功能：打印并换行
        esc.addText("売上高：");
        esc.addText(model.PrintCashDetail.get().getTotal_format());
        esc.addPrintAndLineFeed();//功能：打印并换行
        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
        esc.addText("準備金：");
        esc.addText(model.PrintCashDetail.get().getCheck_input_format());
        esc.addPrintAndLineFeed();//功能：打印并换行
        esc.addText("------------------------------------------------");
        esc.addPrintAndLineFeed();//功能：打印并换行

        if (model.PrintCashDetail.get() != null) {
            List<MoneyPrintEntity.PaymentsBeanX> bean = model.PrintCashDetail.get().getPayments();
            for (int i = 0; i < model.PrintCashDetail.get().getPayments().size(); i++) {
                esc.addText(model.PrintCashDetail.get().getPayments().get(i).getName() + "：");
                esc.addText(model.PrintCashDetail.get().getPayments().get(i).getTotal_format());
                esc.addPrintAndLineFeed();//功能：打印并换行
            }
            esc.addText("------------------------------------------------");
            esc.addPrintAndLineFeed();//功能：打印并换行
//            esc.addSelectPrintModes(EscCommand.FONT.FONTA,
//                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON,
//                    EscCommand.ENABLE.ON, EscCommand.ENABLE.ON);//00110
            //取消倍高倍宽
            esc.addPrintAndLineFeed();//功能：打印并换行
//            esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
            esc.addPrintAndLineFeed();//功能：打印并换行

            esc.addSelectJustification(EscCommand.JUSTIFICATION.RIGHT);
            esc.addText("担当者：");
            esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON);//00110
            esc.addTurnUnderlineModeOnOrOff(EscCommand.UNDERLINE_MODE.UNDERLINE_2DOT);
            esc.addText("            ");
            esc.addPrintAndLineFeed();//功能：打印并换行

            if (openBox) {
//                // 开钱箱
                esc.addGeneratePlus(LabelCommand.FOOT.F2, (byte) 255, (byte) 255);
            }

            //设置绝对打印位置
            esc.addSetAbsolutePrintPosition((short) 10);
            //打印并换行
            esc.addPrintAndLineFeed();
//            /* 开钱箱 */
//            esc.addGeneratePlus(LabelCommand.FOOT.F5, (byte) 255, (byte) 255);
            esc.addPrintAndFeedLines((byte) 5);//打印并走纸 n 行
            esc.addCutPaper();//半切纸并走纸，这条命令只在行首有效

//        /* 加入查询打印机状态，用于连续打印 */

            byte[] bytes = {29, 114, 1};
            esc.addUserCommand(bytes);
            Vector<Byte> datas = esc.getCommand();
            /* 发送数据 */
            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].sendDataImmediately(datas);
        }

    }

    /**
     * 发送今日打印票据
     */
    void rijiPrint2(BillViewModel model, boolean openBox) {
        EscCommand esc = new EscCommand();
        esc.addInitializePrinter();//初始化打印机
//        esc.addCutPaper();//半切纸并走纸，这条命令只在行首有效
//        esc.addCutAndFeedPaper((byte) 10);//半切纸并走纸，这条命令只在行首有效---10切纸后走纸距离
//        esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
        esc.addPrintAndLineFeed();//功能：打印并换行
        /* 设置打印居中 */
        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
        /* 设置为倍高倍宽 */
//        esc.addSelectPrintModes(EscCommand.FONT.FONTA,
//                EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON,
//                EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);//00110
        //取消倍高倍宽
        esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
        esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.ON);//放大字体
        esc.addText("日計表");
        esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.OFF);//不放大字体

        esc.addPrintAndLineFeed();//功能：打印并换行
//        esc.addPrintAndFeedLines((byte) 2);//打印并走纸 n 行
        esc.addPrintAndLineFeed();//功能：打印并换行
        esc.addPrintAndLineFeed();//功能：打印并换行
        /* 打印文字 */
        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
        esc.addText(model.PrintCashDetail.get().getMerchant().getName());
        esc.addPrintAndLineFeed();//功能：打印并换行
        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
        esc.addText(model.PrintCashDetail.get().getMerchant().getAddress());
        esc.addPrintAndLineFeed();//功能：打印并换行
        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
        esc.addText("TEL  " + model.PrintCashDetail.get().getMerchant().getPhone());
        esc.addPrintAndLineFeed();//功能：打印并换行
        esc.addText("------------------------------------------------");

        esc.addPrintAndLineFeed();//功能：打印并换行
        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
        esc.addText("出勤期間：");
        esc.addText(model.PrintCashDetail.get().getDate());
        esc.addPrintAndLineFeed();//功能：打印并换行
        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
        esc.addText("コメント：");
        esc.addText("");

        esc.addPrintAndLineFeed();//功能：打印并换行
        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);

        esc.addText("------------------------------------------------");
        esc.addPrintAndLineFeed();//功能：打印并换行
        esc.addText("売上高：");
        esc.addText(model.PrintCashDetail.get().getTotal_format());
        esc.addPrintAndLineFeed();//功能：打印并换行
        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
        esc.addText("準備金：");
        esc.addText(model.PrintCashDetail.get().getCheck_input_format());
        esc.addPrintAndLineFeed();//功能：打印并换行
        esc.addText("------------------------------------------------");
        esc.addPrintAndLineFeed();//功能：打印并换行

        if (model.PrintCashDetail.get() != null) {
            List<MoneyPrintEntity.PaymentsBeanX> bean = model.PrintCashDetail.get().getPayments();
            for (int i = 0; i < model.PrintCashDetail.get().getPayments().size(); i++) {
                esc.addText(model.PrintCashDetail.get().getPayments().get(i).getName() + "：");
                esc.addText(model.PrintCashDetail.get().getPayments().get(i).getTotal_format());
                esc.addPrintAndLineFeed();//功能：打印并换行
            }
            esc.addText("------------------------------------------------");
            esc.addPrintAndLineFeed();//功能：打印并换行
//            esc.addSelectPrintModes(EscCommand.FONT.FONTA,
//                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON,
//                    EscCommand.ENABLE.ON, EscCommand.ENABLE.ON);//00110
            //取消倍高倍宽
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行

            if (model.TakeMoneyDetail.get() != null) {
                if (model.TakeMoneyDetail.get().getPrice_format() != null) {
                    esc.addText("出金：" + model.TakeMoneyDetail.get().getPrice_format());
                    esc.addPrintAndLineFeed();//功能：打印并换行
                }
            }

            esc.addSelectJustification(EscCommand.JUSTIFICATION.RIGHT);
            esc.addText("担当者：");
            esc.addSelectPrintModes(EscCommand.FONT.FONTA,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF,
                    EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON);//00110
            esc.addTurnUnderlineModeOnOrOff(EscCommand.UNDERLINE_MODE.UNDERLINE_2DOT);
            esc.addText("            ");
            esc.addPrintAndLineFeed();//功能：打印并换行

            if (openBox) {
//                // 开钱箱
                esc.addGeneratePlus(LabelCommand.FOOT.F2, (byte) 255, (byte) 255);
            }

            //设置绝对打印位置
            esc.addSetAbsolutePrintPosition((short) 10);
            //打印并换行
            esc.addPrintAndLineFeed();
//            /* 开钱箱 */
//            esc.addGeneratePlus(LabelCommand.FOOT.F5, (byte) 255, (byte) 255);
            esc.addPrintAndFeedLines((byte) 5);//打印并走纸 n 行
            esc.addCutPaper();//半切纸并走纸，这条命令只在行首有效

//        /* 加入查询打印机状态，用于连续打印 */

            byte[] bytes = {29, 114, 1};
            esc.addUserCommand(bytes);
            Vector<Byte> datas = esc.getCommand();
            /* 发送数据 */
            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].sendDataImmediately(datas);
        }

    }


    // AA打印

    public void AAprintOrder(BillViewModel model, boolean openBox) {

        if (model.aaPrint.get() != null) {
            AAPrintEntity AAbean = model.aaPrint.get();
            EscCommand esc = new EscCommand();
            esc.addInitializePrinter();//初始化打印机


            esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
            esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.ON);//放大字体
            esc.addText(AAbean.getMerchant().getName());
            esc.addSetQuadrupleModeForKanji(EscCommand.ENABLE.OFF);//不放大字体
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行


            esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
            esc.addText("テーブル：" + AAbean.getSeat_info().getNumber());
            esc.addHorTab();
            esc.addHorTab();
            esc.addText(AAbean.getSeat_info().getCount() + "人");
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addText("-----------------------------------------------");

            List<AAPrintEntity.CustomerInfoBean> aaOrderList = AAbean.getCustomer_info();//这个事获取的AA人数
            Boolean state = true;
            for (int i = 0; i < aaOrderList.size(); i++) {
                // 这个事获取的AA人数的菜数aaOrderList_i
                List<AAPrintEntity.CustomerInfoBean.MenusBean> aaOrderList_i = AAbean.getCustomer_info().get(i).getMenus();
                for (int j = 0; j < aaOrderList_i.size(); j++) {
                    esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
                    smallFromat(esc, aaOrderList_i.get(j).getName());
                    esc.addText(aaOrderList.get(i).getMenus().get(j).getCount());
                    esc.addHorTab();

                    esc.addText("￥" + aaOrderList.get(i).getMenus().get(j).getPrice_format());
                    esc.addHorTab();

//                    esc.addPrintAndLineFeed();//功能：打印并换行

                    if (aaOrderList_i.get(j).getAttributes().equals("")) {
                    } else {
                        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
                        esc.addText("(" + aaOrderList_i.get(j).getAttributes() + ")");

                    }
                    esc.addPrintAndFeedLines((byte) 2);//打印并走纸 n 行
                    if (aaOrderList_i.size() == (j + 1)) {
                        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
                        esc.addText("小计：");
                        esc.addHorTab();
                        esc.addHorTab();
                        esc.addHorTab();
                        esc.addHorTab();
                        esc.addHorTab();

                        esc.addText("￥" + aaOrderList.get(i).getTotal_price_format());

                        esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
                        esc.addSelectJustification(EscCommand.JUSTIFICATION.RIGHT);
                        esc.addText(aaOrderList.get(i).getName() + "  ");
                        esc.addPrintAndFeedLines((byte) 1);//打印并走纸 n 行
                        esc.addText("-----------------------------------------------");

                        esc.addPrintAndFeedLines((byte) 2);//打印并走纸 n 行
                        continue;
                    }
                }


            }
//            esc.addText("-----------------------------------------------");
            esc.addPrintAndLineFeed();//功能：打印并换行
//            esc.addPrintAndFeedLines((byte) 2);//打印并走纸 n 行

            esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
            esc.addTurnEmphasizedModeOnOrOff(EscCommand.ENABLE.ON);
            esc.addText("ご来店時刻：" + AAbean.getOrder_info().getOpened_at());
            esc.addPrintAndLineFeed();//功能：打印并换行
            esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
            esc.addTurnEmphasizedModeOnOrOff(EscCommand.ENABLE.ON);
            esc.addText("印刷時刻：" + AAbean.getPrint_time());
            esc.addPrintAndLineFeed();//功能：打印并换行


            esc.addPrintAndFeedLines((byte) 5);//打印并走纸 n 行
            esc.addCutPaper();//半切纸并走纸，这条命令只在行首有效
//        /* 加入查询打印机状态，用于连续打印 */

            byte[] bytes = {29, 114, 1};
            esc.addUserCommand(bytes);
            Vector<Byte> datas = esc.getCommand();
            /* 发送数据 */
            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].sendDataImmediately(datas);

        }


    }

}