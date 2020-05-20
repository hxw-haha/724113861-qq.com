package com.android.kotlin.demo

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.kotlin.demo.base.ui.activity.DefaultActivity
import com.android.kotlin.demo.model.EditTextModel
import com.android.kotlin.demo.viewModel.EditViewModel


class MainActivity : DefaultActivity(), MainContact.MainPresenter {

    private lateinit var mMainView: MainView;

    override fun layoutId(): Int {
        return R.layout.activity_main;
    }

    override fun initViews() {
        super.initViews()
        mMainView = MainView(viewBinding(), this);
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)

        val editTextModel = EditTextModel();
        mMainView.mainBinding.editTextModel = editTextModel;

        val editViewModel = ViewModelProvider(this)[EditViewModel::class.java];
//        val editViewModel = ViewModelProviders.of(this).get(EditViewModel::class.java)
        editViewModel.get().observe(this, Observer {
            mMainView.mainBinding.editTextModel = it
            mMainView.loadEditTextData(it.mainEditText)
        })
    }

    override fun submit(message: String) {
        val editViewModel = ViewModelProvider(this)[EditViewModel::class.java];
//        val editViewModel = ViewModelProviders.of(this).get(EditViewModel::class.java)
        val editTextModel = EditTextModel();
        editTextModel.mainEditText = message;
        editViewModel.set(editTextModel)
    }

    override fun jumpSecondPage() {
        SecondActivity.actionStart(this)
    }
}
