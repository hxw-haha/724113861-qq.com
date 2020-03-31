package com.android.kotlin.demo.base

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.SensorManager
import android.os.Looper
import android.text.TextUtils
import android.view.OrientationEventListener
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

/**
 * @author hanxw
 * @time 2019/9/27 14:05
 */
public abstract class BaseView<P : BasePresenter>(viewDataBinding: ViewDataBinding?, @NonNull presenter: P) : LifecycleEventObserver {

    private var mPresenter: P = presenter;
    private var mOrientationListener: OrientationEventListener? = null
    private var mViewDataBinding: ViewDataBinding? = viewDataBinding;


    protected fun <T : ViewDataBinding> viewBinding(): T {
        return mViewDataBinding as T
    }

    init {
        getPresenter().lifecycle.addObserver(this);
        this.mOrientationListener = object : OrientationEventListener(getContext(),
                SensorManager.SENSOR_DELAY_NORMAL) {
            override fun onOrientationChanged(orientation: Int) {
                onConfigurationChanged(orientation)
            }
        }
        if ((mOrientationListener as OrientationEventListener).canDetectOrientation()) {
            (mOrientationListener as OrientationEventListener).enable()
        } else {
            (mOrientationListener as OrientationEventListener).disable()
        }
        initViews(mViewDataBinding?.root)
        initData();
        initListener();
    }

    public open fun initViews(view: View?) {}

    public open fun initData() {}

    public open fun initListener() {}

    /**
     * 屏幕旋转
     *
     * @param orientation
     */
    protected fun onConfigurationChanged(orientation: Int) {

    }

    protected fun getPresenter(): P {
        return mPresenter;
    }

    protected fun getView(): View? {
        return mViewDataBinding?.root;
    }

    protected fun getContext(): Context? {
        return getView()?.context;
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_CREATE) {
            onCreate()
        } else if (event == Lifecycle.Event.ON_START) {
            onStart()
        } else if (event == Lifecycle.Event.ON_RESUME) {
            onResume()
        } else if (event == Lifecycle.Event.ON_PAUSE) {
            onPause()
        } else if (event == Lifecycle.Event.ON_STOP) {
            onStop()
        } else if (event == Lifecycle.Event.ON_DESTROY) {
            onDestroy()
        }
    }

    public open fun onCreate() {}

    public open fun onStart() {}

    public open fun onResume() {}

    public open fun onPause() {}

    public open fun onStop() {}

    public open fun onDestroy() {
        getPresenter().lifecycle.removeObserver(this);
        mViewDataBinding?.unbind();
        mViewDataBinding = null;
        mOrientationListener?.disable()
        mOrientationListener = null;
    }


    /**
     * Toast 信息
     * @param message 显示的提示信息
     */
    @SuppressLint("RestrictedApi")
    protected fun showToast(message: String) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show()
        } else {
            ArchTaskExecutor.getMainThreadExecutor().execute {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}