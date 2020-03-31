package com.android.kotlin.demo.base.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Looper
import android.text.TextUtils
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.android.kotlin.demo.base.ui.IBase

/**
 * @author hanxw
 * @time 2019/9/27 16:40
 */
abstract class BaseActivity : AppCompatActivity(), IBase {

    private lateinit var mViewDataBinding: ViewDataBinding;

    protected fun <T : ViewDataBinding> viewBinding(): T {
        return mViewDataBinding as T
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        windowLoadAttr();
        mViewDataBinding = DataBindingUtil.setContentView(this, layoutId());
//        setContentView(layoutId());
        initViews();
        initData(savedInstanceState);
        initListener();
    }

    protected open fun windowLoadAttr() {
        //去除标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        //禁止软键盘自动弹出
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    override fun onDestroy() {
        super.onDestroy();
        destroy();
        mViewDataBinding.unbind();
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
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } else {
            ArchTaskExecutor.getMainThreadExecutor().execute {
                Toast.makeText(this@BaseActivity, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}