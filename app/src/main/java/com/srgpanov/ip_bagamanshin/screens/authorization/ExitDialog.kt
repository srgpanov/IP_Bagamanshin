package com.srgpanov.ip_bagamanshin.screens.authorization

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.srgpanov.ip_bagamanshin.R

class ExitDialog : DialogFragment() {
    var exitCallback: OnExitCallback? = null

    companion object {
        val TAG = this::class.java.simpleName
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return AlertDialog.Builder(requireActivity())
            .setPositiveButton("Yes") { _: DialogInterface, _: Int ->
                exitCallback?.onExit(true)
            }.setNegativeButton("No") { _: DialogInterface, _: Int ->
                exitCallback?.onExit(false)
            }
            .setTitle(R.string.exit_question)
            .create()
    }


    interface OnExitCallback {
        fun onExit(exit: Boolean)
    }


}