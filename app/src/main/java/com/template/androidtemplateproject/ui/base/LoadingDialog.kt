package com.template.androidtemplateproject.ui.base

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import com.template.androidtemplateproject.R

class LoadingDialog(private val activity: Activity) {

    private val dialog by lazy {
        val dialogBuilder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        dialogBuilder.setView(inflater.inflate(R.layout.loading_custom_dialog, null))
        dialogBuilder.create()
    }

    @SuppressLint("InflateParams")
    fun startLoadingDialog() {
        dialog.show()
    }

    fun dismissLoadingDialog() {
        dialog.dismiss()
    }
}