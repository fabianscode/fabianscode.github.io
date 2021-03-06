package com.example.schachklubbingen

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager


class ExampleDialog : DialogFragment() {
    private var toolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view =
            inflater.inflate(R.layout.update_event_layout, container, false)
        toolbar = view.findViewById(R.id.toolbar)
        return view
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        toolbar!!.setNavigationOnClickListener { v: View? -> dismiss() }
        toolbar!!.title = "Some Title"
        toolbar!!.inflateMenu(R.menu.example_dialog)
        toolbar!!.setOnMenuItemClickListener { item: MenuItem? ->
            dismiss()
            true
        }
    }

    companion object {
        const val TAG = "example_dialog"
        fun display(fragmentManager: FragmentManager?): ExampleDialog {
            val exampleDialog = ExampleDialog()
            exampleDialog.show(fragmentManager!!, TAG)
            return exampleDialog
        }
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
            dialog.window?.setWindowAnimations(R.style.AppTheme_Slide)
        }
    }
}