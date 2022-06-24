package com.softwrengr.task.baseClasses

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.kaopiz.kprogresshud.KProgressHUD
import com.softwrengr.task.R

//Common for all activities
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    lateinit var binding:VB
    var isDisableOnBackPress = true
    var progressHUD: KProgressHUD? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)

        setUpView()
        showProgressBar()

    }

    abstract fun setUpView()

    abstract fun getViewBinding() : VB


    public override fun onResume() {
        super.onResume()
    }
    public override fun onPause() {
        super.onPause()
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
    @TargetApi(Build.VERSION_CODES.O)
    private fun disableAutoFill() {
        window.decorView.importantForAutofill = View.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS
    }

    fun showToast(context: Context,message: String?){
        Toast.makeText(context,""+message,Toast.LENGTH_SHORT).show()
    }

    private fun showProgressBar() {
        progressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(false)
            .setBackgroundColor(resources.getColor(R.color.purple_500)).setAnimationSpeed(2);
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}