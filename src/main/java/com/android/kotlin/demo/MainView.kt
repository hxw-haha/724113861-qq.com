package com.android.kotlin.demo

import android.view.View
import androidx.annotation.NonNull
import com.android.kotlin.demo.databinding.ActivityMainBinding

/**
 * @author hanxw
 * @time 2019/9/29 14:49
 */
class MainView(mainBinding: ActivityMainBinding, @NonNull presenter: MainContact.MainPresenter)
    : MainContact.MainView(mainBinding, presenter) {

    lateinit var mainBinding: ActivityMainBinding;

    override fun initViews(view: View?) {
        super.initViews(view)
        mainBinding = viewBinding();
    }

    override fun initListener() {
        super.initListener()
        this.mainBinding.submit.setOnClickListener { getPresenter().submit(this.mainBinding.editText.text.toString().trim()); }

        this.mainBinding.second.setOnClickListener { getPresenter().jumpSecondPage(); }
    }

    override fun loadEditTextData(editText: String) {
        this.mainBinding.editText.setText(editText);
    }

    override fun onDestroy() {
        super.onDestroy()
        mainBinding.unbind()
    }
}