package com.android.kotlin.demo.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.android.kotlin.demo.APP
import java.io.Serializable

/**
 * @author hanxw
 * @time 2020/3/31 13:44
 */
class EditTextModel : BaseObservable(), Serializable {

    @get:Bindable
    var mainEditText: String = APP.editText
        set(value) {
            field = value
            APP.editText = value;
            notifyPropertyChanged(BR._all)
        }
}