package sk.itsovy.android.opendatagame

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sk.itsovy.android.opendatagame.databinding.ItemLayoutBinding

class NamesAdapter : ListAdapter<Record, NamesAdapter.NamesViewHolder>(DiffCallback) {

    class NamesViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(record: Record) {
            binding.textViewName.text = record.name
            binding.textViewNumber.text = "Count: ${record.count}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamesViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NamesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NamesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object DiffCallback : DiffUtil.ItemCallback<Record>() {
        // porovnavam podla primarneho kluca
        override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean =
            oldItem.name == newItem.name

        // porovnavam obsah - vsetky parametre
        override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean =
            oldItem.name == newItem.name && oldItem.count == newItem.count
    }
}