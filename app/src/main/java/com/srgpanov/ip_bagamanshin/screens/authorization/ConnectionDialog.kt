package com.srgpanov.ip_bagamanshin.screens.authorization

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.srgpanov.ip_bagamanshin.R
import retrofit2.Response.error

class ConnectionDialog: DialogFragment()  {
    companion object{
        const val TAG = "ConnectionDialog"
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireActivity())
            .setPositiveButton("Yes") { _: DialogInterface, _: Int ->
                requireActivity().finish()
            }
            .setOnDismissListener { requireActivity().finish() }
            .setMessage(R.string.connection_message)
            .setIcon(R.drawable.ic_baseline_error_24)
            .setTitle(R.string.error)
            .create()
    }
}