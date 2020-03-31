package com.android.kotlin.demo;

import android.app.Application;

import com.android.kotlin.demo.model.EditTextModel;

import androidx.lifecycle.MutableLiveData;

/**
 * @author hanxw
 * @time 2020/3/31 16:03
 */
public class APP extends Application {


    /**
     * 提取全局 MutableLiveData 可以使多个页面共享数据，这样当一个地方修改数据，全局通知数据修改
     */
    public static MutableLiveData<EditTextModel> sEditData = new MutableLiveData<>();
    public static String editText="hello word";
}
