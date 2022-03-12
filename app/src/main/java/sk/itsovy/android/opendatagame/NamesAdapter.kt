package sk.itsovy.android.opendatagame

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sk.itsovy.android.opendatagame.databinding.ItemLayoutBinding
import java.util.*

class NamesAdapter(private val onClick: (RecyclerView.ViewHolder) -> Unit) :
    ListAdapter<Record, NamesAdapter.NamesViewHolder>(DiffCallback) {

    // hovori ci ma zobrazit cisla
    var visibleCounts = false
        // vlastny setter - pri zmene hodnoty sa aj prekresli recycler view
        set(value) {
            // nastavenie hodnoty
            field = value
            // notifyDataSetChanged() dalo warning - v tomto pripade je aj toto v pohode
            notifyItemRangeChanged(0, currentList.size)
        }

    // property ktora hovori ci je konfiguracia vyherna
    val win: Boolean
        get() {
            // zip with next - spoji i-ty prvok s i+1 - ak je horna hodnota mensia tak nie je vyhra
            currentList.zipWithNext { a, b -> if (a.count < b.count) return false }
            // nenasiel sa problem, teda je vyherna konfiguracia
            return true
        }

    class NamesViewHolder(
        private val binding: ItemLayoutBinding,
        private val onClick: (RecyclerView.ViewHolder) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(record: Record, visibleCounts: Boolean) {
            binding.textViewName.text = record.name
            binding.textViewNumber.text = "Count: ${record.count}"
            binding.textViewNumber.visibility = if (visibleCounts) View.VISIBLE else View.INVISIBLE

            binding.reorderIcon.setOnTouchListener { _, motionEvent ->
                if (motionEvent.actionMasked == MotionEvent.ACTION_DOWN) {
                    onClick(NamesViewHolder@this)
                }

                return@setOnTouchListener true
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamesViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NamesViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: NamesViewHolder, position: Int) {
        holder.bind(getItem(position), visibleCounts)
    }

    fun exchangeItems(from: Int, to: Int) {
        val list = currentList.toMutableList()
        Collections.swap(list, from, to)
        submitList(list)
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