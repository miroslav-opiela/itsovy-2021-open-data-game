package sk.itsovy.android.opendatagame

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class NamesAdapter : ListAdapter<Record, NamesAdapter.NamesViewHolder>(DiffCallback) {

    class NamesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamesViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: NamesViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    object DiffCallback : DiffUtil.ItemCallback<Record>() {
        override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
            TODO("Not yet implemented")
        }

    }
}