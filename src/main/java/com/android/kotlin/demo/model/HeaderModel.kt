package com.android.kotlin.demo.model

import android.view.View
import androidx.databinding.BaseObservable

/**
 * @author hanxw
 * @time 2019/10/10 11:04
 */
class HeaderModel : BaseObservable() {

    public var leftVisible: Boolean? = false;
    public var centerVisible: Boolean? = false;
    public var rightVisible: Boolean? = false;

    public fun getVisible(visible: Boolean): Int {
        if (visible) {
            return View.VISIBLE;
        } else {
            return View.INVISIBLE;
        }
    }
}