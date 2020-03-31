package com.android.kotlin.demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.NonNull
import com.android.kotlin.demo.base.ui.activity.BaseFragmentActivity
import com.android.kotlin.demo.databinding.ActivitySecondBinding
import com.android.kotlin.demo.databinding.HeaderBinding
import com.android.kotlin.demo.model.HeaderModel

/**
 * @author hanxw
 * @time 2019/9/30 13:31
 */
class SecondActivity : BaseFragmentActivity() {

    public companion object {
        @JvmStatic
        public fun actionStart(@NonNull context: Context) {
            val intent = Intent(context, SecondActivity::class.java)
            context.startActivity(intent);
        }
    }

    private lateinit var activitySecondBinding: ActivitySecondBinding;

    override fun layoutId(): Int {
        return R.layout.activity_second;
    }

    override fun fragmentLayoutId(): Int {
        return R.id.second_content_view;
    }

    override fun initViews() {
        super.initViews();
        replaceFragment(SecondFragment());

        activitySecondBinding = viewBinding()
        val headerBinding: HeaderBinding = activitySecondBinding.header;
        headerBinding.headerLeft.setBackgroundResource(R.mipmap.back2x);
        headerBinding.headerCenter.text = "第二个页面";
        headerBinding.headerLeft.setOnClickListener { finish() };
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        val headerModel = HeaderModel();
        headerModel.leftVisible = true;
        headerModel.centerVisible = true;
        activitySecondBinding.headerModel = headerModel;
    }

}