package com.android.kotlin.demo

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.android.kotlin.demo.base.ui.fragment.DefaultFragment
import com.android.kotlin.demo.model.EditTextModel
import com.android.kotlin.demo.viewModel.EditViewModel

/**
 * @author hanxw
 * @time 2019/9/30 14:06
 */
class SecondFragment : DefaultFragment(), SecondContact.SecondPresenter {

    private lateinit var mSecondView: SecondView;


    override fun layoutId(): Int {
        return R.layout.fragment_second;
    }

    override fun initViews() {
        super.initViews()
        mSecondView = SecondView(viewBinding(), this);
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        val editViewModel = ViewModelProviders.of(this).get(EditViewModel::class.java);
        mSecondView.secondBinding.editTextModel = editViewModel.get().value;
    }

    override fun backPage() {
        activity?.finish();
    }

    override fun updateAndBackPage() {
        val editViewModel = ViewModelProviders.of(this).get(EditViewModel::class.java);
        val editTextModel = EditTextModel();
        editTextModel.mainEditText = mSecondView.secondBinding.editText.text.toString()
        editViewModel.set(editTextModel);

        backPage()
    }

}