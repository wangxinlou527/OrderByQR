package com.jydp.obqr.entity;

import com.jydp.obqr.net.BasePresenter;
import com.jydp.obqr.net.BaseView;

/**
 * Created by Administrator on 2019/12/11.
 */

public class Tab1PayContract {
       public interface View extends BaseView {
        void   convertMsg(String msg);
    }



    /**
     * 订单 详细信息
     *
     */
      public interface Presenter extends BasePresenter {
        void requestSource();
    }
}
