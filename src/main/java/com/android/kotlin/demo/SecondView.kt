package com.android.kotlin.demo

import android.view.View
import com.android.kotlin.demo.databinding.FragmentSecondBinding

/**
 * @author hanxw
 * @time 2019/9/30 14:35
 */
class SecondView(secondBinding: FragmentSecondBinding, presenter: SecondContact.SecondPresenter) : SecondContact.SecondView(secondBinding, presenter) {

    lateinit var secondBinding: FragmentSecondBinding;

    override fun initViews(view: View?) {
        super.initViews(view)
        secondBinding = viewBinding()
    }

    override fun initListener() {
        super.initListener()
        secondBinding.backMain.setOnClickListener { getPresenter().backPage() };
        secondBinding.updateBtn.setOnClickListener { getPresenter().updateAndBackPage(); };
    }
}