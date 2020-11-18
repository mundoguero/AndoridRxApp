package br.com.brisotti.rxapp.sections.subjects

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.brisotti.rxapp.R

class SubjectsAdapter(private val dataSource: MutableList<String>): RecyclerView.Adapter<SubjectsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_subject, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = dataSource[position]
    }

    fun updateList(dataSource: List<String>) {
        this.dataSource.clear()
        this.dataSource.addAll(dataSource)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.item_view_subject_title_text_view)
    }
}