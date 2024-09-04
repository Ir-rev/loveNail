package ru.pervov.procedure_list_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.pervov.lovenail.procedure_list_screen.databinding.ItemEmptyProcedureListBinding
import ru.pervov.lovenail.procedure_list_screen.databinding.ItemProcedureBinding

private const val EMPTY_ITEM = 0
private const val PROCEDURE_ITEM = 1

class ProcedureListAdapter(
    private val procedureList: List<ProcedureRecyclerItem>,
    private val onProcedureClick: (id: String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PROCEDURE_ITEM -> {
                ProcedureItemHolder(
                    ItemProcedureBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }

            else -> {
                ProcedureListEmptyHolder(
                    ItemEmptyProcedureListBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }
    }

    override fun getItemCount(): Int = procedureList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ProcedureItemHolder -> {
                val item =
                    (procedureList[position] as? ProcedureRecyclerItem.ProcedureItem)?.procedure
                        ?: return
                holder.procedureNameTextView.text = item.name
                holder.procedurePriceTextView.text = item.price.toString()
                holder.root.setOnClickListener {
                    onProcedureClick(item.id)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (procedureList[position]) {
            is ProcedureRecyclerItem.ProcedureItem -> PROCEDURE_ITEM
            is ProcedureRecyclerItem.EmptyListItem -> EMPTY_ITEM
        }
    }

    class ProcedureListEmptyHolder(
        binding: ItemEmptyProcedureListBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class ProcedureItemHolder(
        binding: ItemProcedureBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val procedureNameTextView = binding.procedureNameTextView
        val procedurePriceTextView = binding.procedurePriceTextView
        val root = binding.rootContainer
    }
}