package com.android.kotlin.demo

import com.android.kotlin.demo.base.BasePresenter
import com.android.kotlin.demo.base.BaseView
import com.android.kotlin.demo.databinding.FragmentSecondBinding

/**
 * @author hanxw
 * @time 2019/9/30 14:31
 */
public class SecondContact {

    public interface SecondPresenter : BasePresenter {
        fun backPage();
        fun updateAndBackPage();
    }

    public abstract class SecondView(secondBinding: FragmentSecondBinding, presenter: SecondPresenter)
        : BaseView<SecondPresenter>(secondBinding, presenter) {

    }
}