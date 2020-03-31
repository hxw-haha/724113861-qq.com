package com.android.kotlin.demo.base.ui.activity

import androidx.fragment.app.FragmentTransaction
import com.android.kotlin.demo.base.ui.fragment.BaseFragment

/**
 * @author hanxw
 * @time 2019/9/29 11:09
 */
abstract class BaseFragmentActivity : DefaultActivity() {

    private var lastFrameLayout: BaseFragment? = null;

    protected fun getLastFrameLayout(): BaseFragment? {
        return lastFrameLayout
    }

    /**
     * 设置替换布局 FragmentLayout 的 ID
     */
    abstract fun fragmentLayoutId(): Int

    /**
     * 替换显示的 Fragment
     *
     * @param baseFragment 显示的 FrameLayout
     * @param isAddToBack
     *
     *是否为Fragment添加回退栈。true:添加回退栈；
     * @param <F>             extends BaseFragment
     */
    public fun <F : BaseFragment> replaceFragment(baseFragment: F) {
        replaceFragment(baseFragment, false, "");
    }

    /**
     * 替换显示的 Fragment
     *
     * @param baseFragment    需要显示的fragment
     * @param isAddToBack     是否添加回退栈
     * @param isAddToBackFlag 回退栈的标识
     *
     *是否为Fragment添加回退栈。true:添加回退栈；
     * @param <F>             extends BaseFragment
     */
    public fun <F : BaseFragment> replaceFragment(baseFragment: F, isAddToBack: Boolean, isAddToBackFlag: String) {
        if (fragmentLayoutId() == 0) {
            throw NullPointerException("FragmentLayout id 不能为空")
        }
        lastFrameLayout = baseFragment
        val transaction = getFragmentTransaction(isAddToBack, isAddToBackFlag);
        transaction.replace(fragmentLayoutId(), baseFragment).commit()
    }

    /**
     * @param isAddToBack     是否添加回退栈
     * @param isAddToBackFlag 回退栈的标识
     */
    private fun getFragmentTransaction(isAddToBack: Boolean, isAddToBackFlag: String): FragmentTransaction {
        val supportFragmentManager = supportFragmentManager
        val transaction = supportFragmentManager
                .beginTransaction()
        //        transaction.setCustomAnimations(
        //                R.anim.slide_right_in,
        //                R.anim.slide_left_out,
        //                R.anim.slide_left_in,
        //                R.anim.slide_right_out
        //        );
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        if (isAddToBack) {
            //将当前的事务添加到了回退栈
            transaction.addToBackStack(isAddToBackFlag)
        }
        return transaction;
    }

    /**
     * 显示fragment
     *
     * @param showFragment 需要显示的fragment
     * @param <F>
    </F> */
    protected fun <F : BaseFragment> showFragment(fragment: F) {
        showFragment(fragment, false, "")
    }

    /**
     * 显示fragment
     *
     * @param showFragment    需要显示的fragment
     * @param isAddToBack     是否添加回退栈
     * @param isAddToBackFlag 回退栈的标识
     * @param <F>
     */
    protected fun <F : BaseFragment> showFragment(showFragment: F, isAddToBack: Boolean, isAddToBackFlag: String) {
        if (fragmentLayoutId() == 0) {
            throw NullPointerException("FragmentLayout id 不能为空")
        }
        if (getLastFrameLayout() == null) {
            replaceFragment(showFragment, isAddToBack, isAddToBackFlag)
            return
        }
        if (getLastFrameLayout() === showFragment) {
            return
        }
        val transaction = getFragmentTransaction(isAddToBack, isAddToBackFlag)
        // 先判断是否被add过
        if (!showFragment.isAdded()) {
            // 隐藏当前的fragment，add下一个到Activity中
            transaction.hide(getLastFrameLayout()!!).add(fragmentLayoutId(), showFragment).commit()
            lastFrameLayout = showFragment
        } else {
            // 隐藏当前的fragment，显示下一个
            transaction.hide(getLastFrameLayout()!!).show(showFragment).commit()
            lastFrameLayout = showFragment
        }
    }

}