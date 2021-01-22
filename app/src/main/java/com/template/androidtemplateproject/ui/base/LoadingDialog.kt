package com.template.androidtemplateproject.ui.base

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import com.template.androidtemplateproject.R

class LoadingDialog(val activity: Activity) {

    private lateinit var dialog: AlertDialog

    @SuppressLint("InflateParams")
    fun startLoadingDialog() {
        val dialogBuilder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        dialogBuilder.setView(inflater.inflate(R.layout.loading_custom_dialog, null))

        dialog = dialogBuilder.create()
        dialog.show()
    }

    fun dismissLoadingDialog() {
        dialog.dismiss()
    }
}