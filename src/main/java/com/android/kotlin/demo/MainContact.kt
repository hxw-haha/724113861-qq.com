package com.android.kotlin.demo

import androidx.annotation.NonNull
import com.android.kotlin.demo.base.BasePresenter
import com.android.kotlin.demo.base.BaseView
import com.android.kotlin.demo.databinding.ActivityMainBinding

/**
 * @author hanxw
 * @time 2019/9/29 14:50
 */
public class MainContact {
    public interface MainPresenter : BasePresenter {
        fun submit(message: String);

        fun jumpSecondPage();
    }

    public abstract class MainView(mainBinding: ActivityMainBinding, @NonNull presenter: MainPresenter)
        : BaseView<MainPresenter>(mainBinding, presenter) {
        abstract fun loadEditTextData(editText: String);
    }
}