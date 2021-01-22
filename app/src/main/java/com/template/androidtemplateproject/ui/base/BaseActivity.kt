package com.template.androidtemplateproject.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

abstract class BaseActivity : AppCompatActivity(), ConnectionErrorDialogFragment.DialogListener {

    private lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingDialog = LoadingDialog(this)
    }

    fun showLoading() {
        loadingDialog.startLoadingDialog()
    }

    fun hideLoading() {
        loadingDialog.dismissLoadingDialog()
    }

    fun showErrorScreen() {
        val errorDialogFragment = ConnectionErrorDialogFragment()
        val bundle = Bundle()
        bundle.putBoolean("fullScreen", true)
        bundle.putBoolean("notAlertDialog", true)
        errorDialogFragment.arguments = bundle

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        val prev: Fragment? = supportFragmentManager.findFragmentByTag("dialog")
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)

        errorDialogFragment.show(ft, "dialog")
    }

    override fun onFinishEditDialog() {
        val intent = intent
        finish()
        startActivity(intent)
    }
}