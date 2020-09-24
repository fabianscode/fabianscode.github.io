package com.example.schachklubbingen

import android.content.Context
import android.system.Os.accept
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.view_holder_content.view.*

class CustomAdapter(private val dataset: List<String>) :
    RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    class CustomViewHolder(val customViewHolderView: CustomViewHolderView) : RecyclerView.ViewHolder(customViewHolderView)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CustomViewHolder {

        val contentView = CustomViewHolderView(parent.context)

        return CustomViewHolder(contentView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.customViewHolderView.setContent(dataset[position])
    }

    override fun getItemCount() = dataset.size
}

class CustomViewHolderView(context: Context) : LinearLayout(context) {
    init {
        View.inflate(context, R.layout.view_holder_content, this)

        item_text.setOnClickListener {
            App.mainActivity.showEventEditingDialog()
        }
    }

    private lateinit var date: String
    private lateinit var topic: String

    public fun setContent(content: String) {
        date = content.split("{")[1].split("}")[0].split("=")[0]
        topic = content.split("{")[1].split("}")[0].split("=")[1]

        findViewById<TextView>(R.id.item_text).text = date
    }
}