package com.jydp.obqr.util;

import android.content.Context;

import com.bumptech.glide.request.RequestOptions;

/**
 * @author yeq
 * @description:
 * @date :2020/7/24 15:14
 */
public class GlideUtil {
    private static RequestOptions roundRequestOptions = null;

    /**
     * 获取圆角属性
     *
     * @param radius
     * @return
     */
    public static RequestOptions getRoundRe(Context context, int radius) {
        if (null == roundRequestOptions) {
            roundRequestOptions = new RequestOptions()
                    .transform(new GlideRoundTransform(context, radius));
        }
        return roundRequestOptions;
    }
}
