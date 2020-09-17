package com.example.schachklubbingen

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

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
    }

    public fun setContent(content: String) {
        findViewById<TextView>(R.id.item_text).text = content
    }
}