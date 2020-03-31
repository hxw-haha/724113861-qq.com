package com.android.kotlin.demo.base.ui

import android.os.Bundle
import com.android.kotlin.demo.base.BasePresenter

/**
 * @author hanxw
 * @time 2019/9/27 13:38
 */
interface IBase : BasePresenter {
    abstract fun layoutId(): Int;

    abstract fun initViews();

    abstract fun initData(savedInstanceState: Bundle?);

    abstract fun initListener();

    abstract fun destroy();
}