package com.template.androidtemplateproject.ui.base

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment
import com.template.androidtemplateproject.R

class ConnectionErrorDialogFragment : DialogFragment() {
    @NonNull
    override fun onCreateDialog(@Nullable savedInstanceState: Bundle?): Dialog {
        if (arguments != null) {
            if (requireArguments().getBoolean("notAlertDialog")) {
                return super.onCreateDialog(savedInstanceState)
            }
        }
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setTitle("Alert Dialog")
        builder.setMessage("Alert Dialog inside DialogFragment")
        builder.setPositiveButton(
            "Ok"
        ) { _, _ -> dismiss() }
        builder.setNegativeButton(
            "Cancel"
        ) { _, _ -> dismiss() }
        return builder.create()
    }

    @Nullable
    override fun onCreateView(
        @NonNull inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.connection_error_fragment, container, false)
        val btSend = root.findViewById<Button>(R.id.btSend)

        btSend.setOnClickListener {
            val dialogListener = activity as DialogListener?
            dialogListener!!.onFinishEditDialog()
            dismiss()
        }

        return root
    }

    override fun onViewCreated(@NonNull view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*btCancel.setOnClickListener {
            dismiss()
        }*/
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("API123", "onCreate")
        var setFullScreen = false
        if (arguments != null) {
            setFullScreen = requireArguments().getBoolean("fullScreen")

        }
        if (setFullScreen) setStyle(
            STYLE_NORMAL,
            android.R.style.Theme_Black_NoTitleBar_Fullscreen
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    interface DialogListener {
        fun onFinishEditDialog()
    }

}