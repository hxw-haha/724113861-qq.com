package com.android.kotlin.demo.base

import android.content.Context
import androidx.annotation.NonNull
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

/**
 * @author hanxw
 * @time 2019/9/27 13:44
 */
abstract class BaseModel<P : BasePresenter>(@NonNull context: Context, @NonNull presenter: P) : LifecycleEventObserver {

    private var mContext: Context = context;
    private var mPresenter: P = presenter;

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            onDestroy();
        }
    }

    init {
        getPresenter().lifecycle.addObserver(this);
    }

    protected open fun onDestroy() {
        getPresenter().lifecycle.removeObserver(this);
    }


    protected fun getPresenter(): P {
        return mPresenter
    }

    protected fun getContext(): Context {
        return mContext
    }

    /**
     * 加载数据
     *
     * @param url url接口
     */
    protected abstract fun loadData(url: String)
}