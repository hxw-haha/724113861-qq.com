package com.android.kotlin.demo.base.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Looper
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.android.kotlin.demo.base.ui.IBase
import com.android.kotlin.demo.base.ui.activity.BaseFragmentActivity

/**
 * @author hanxw
 * @time 2019/9/27 18:23
 */
abstract class BaseFragment : Fragment(), IBase {
    protected lateinit var mContext: Context;
    private lateinit var mViewDataBinding: ViewDataBinding;

    protected fun getContentView(): View {
        return mViewDataBinding.root
    }

    protected fun <T : ViewDataBinding> viewBinding(): T {
        return mViewDataBinding as T
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context;
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false);
        initViews();
        return getContentView();
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);
        initData(savedInstanceState);
        initListener();
    }

    override fun onDestroy() {
        super.onDestroy()
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
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        } else {
            ArchTaskExecutor.getMainThreadExecutor().execute {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * 替换显示页面的FragmentLayout
     * <P>注：需要传递多个值时，页面传递参数，请调用该方面</P>
     *
     * @param baseFrameLayout 加载的页面
     * @param pageParam       加载的参数类型
     * @param key             加密的key
     * @param isAddToBack     是否添加回退栈
     * @param <F>             extends BaseFragment
     * @param <P>             extends BaseParam
     */
    protected fun <F : BaseFragment, P : BaseParam> replaceFragment(baseFragment: F, key: String, pageParam: P) {
        val bundle = Bundle()
        bundle.putSerializable(key, pageParam)
        baseFragment.arguments = bundle
        replaceFragment(baseFragment)
    }

    /**
     * 替换显示页面的FragmentLayout
     * <P>注：传递单个值时，页面传递参数，请调用该方面</P>
     *
     * @param baseFrameLayout
     * @param key
     * @param value
     * @param isAddToBack
     * @param <F>
     */
    protected fun <F : BaseFragment> replaceFragment(baseFragment: F, key: String, value: String) {
        val bundle = Bundle()
        bundle.putString(key, value)
        baseFragment.arguments = bundle
        replaceFragment(baseFragment)
    }

    /**
     * 替换显示页面的FragmentLayout
     * <P>注：此方法不传递参数</P>
     *
     * @param baseFragment 需要显示的FragmentLayout
     * @param isAddToBack     是否添加回退栈
     * @param <F>             extends BaseFragment
     */
    protected fun <F : BaseFragment> replaceFragment(baseFragment: F) {
        if (mContext is BaseFragmentActivity) {
            (mContext as BaseFragmentActivity).replaceFragment(baseFragment);
        }
    }


    /**
     * 替换显示页面的FragmentLayout
     * <P>注：需要传递多个值时，页面传递参数，请调用该方面</P>
     *
     * @param baseFragment 加载的页面
     * @param pageParam       加载的参数类型
     * @param key             加密的key
     * @param isAddToBackFlag     添加回退栈的标识
     * @param <F>             extends BaseFragment
     * @param <P>             extends BaseParam
     */
    protected fun <F : BaseFragment, P : BaseParam> replaceFragment(baseFragment: F, key: String, pageParam: P, isAddToBackFlag: String) {
        val bundle = Bundle()
        bundle.putSerializable(key, pageParam)
        baseFragment.arguments = bundle
        replaceFragment(baseFragment, true, isAddToBackFlag);
    }

    /**
     * 替换显示页面的FragmentLayout
     * <P>注：传递单个值时，页面传递参数，请调用该方面</P>
     *
     * @param baseFragment
     * @param key
     * @param value
     * @param isAddToBackFlag     添加回退栈的标识
     * @param <F>
     */
    protected fun <F : BaseFragment> replaceFragment(baseFragment: F, key: String, value: String, isAddToBackFlag: String) {
        val bundle = Bundle()
        bundle.putString(key, value)
        baseFragment.arguments = bundle
        replaceFragment(baseFragment, true, isAddToBackFlag)
    }

    /**
     * 替换显示页面的FragmentLayout
     * <P>注：此方法不传递参数</P>
     *
     * @param baseFragment 需要显示的FragmentLayout
     * @param isAddToBack     是否添加回退栈
     * @param <F>             extends BaseFragment
     */
    protected fun <F : BaseFragment> replaceFragment(baseFragment: F, isAddToBack: Boolean, isAddToBackFlag: String) {
        if (mContext is BaseFragmentActivity) {
            (mContext as BaseFragmentActivity).replaceFragment(baseFragment, isAddToBack, isAddToBackFlag);
        }
    }
}