package com.android.kotlin.demo.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.android.kotlin.demo.APP
import com.android.kotlin.demo.model.EditTextModel

/**
 * @author hanxw
 * @time 2020/3/31 14:40
 */
class EditViewModel(application: Application) : AndroidViewModel(application) {

    fun set(vaule: EditTextModel) {
        APP.sEditData.value = vaule
    }

    fun get(): LiveData<EditTextModel> {
        return APP.sEditData;
    }
}