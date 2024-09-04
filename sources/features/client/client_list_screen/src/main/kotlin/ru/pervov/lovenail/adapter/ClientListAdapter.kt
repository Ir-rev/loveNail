package ru.pervov.lovenail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.util.Util
import ru.pervov.lovenail.client_list_screen.databinding.ItemClientBinding
import ru.pervov.lovenail.client_list_screen.databinding.ItemEmptyClientListBinding

private const val EMPTY_ITEM = 0
private const val CLIENT_ITEM = 1

class ClientListAdapter(
    private val clientList: List<ClientRecyclerItem>,
    private val onClientClick: (id: String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CLIENT_ITEM -> {
                ClientItemHolder(
                    ItemClientBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }

            else -> {
                ClientListEmptyHolder(
                    ItemEmptyClientListBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }
    }

    override fun getItemCount(): Int = clientList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ClientItemHolder -> {
                val item =
                    (clientList[position] as? ClientRecyclerItem.ClientItem)?.client ?: return
                holder.nameTextView.text = item.name
                holder.phoneTextView.text = item.phoneNumber
                holder.root.setOnClickListener {
                    onClientClick(item.id)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (clientList[position]) {
            is ClientRecyclerItem.ClientItem -> CLIENT_ITEM
            is ClientRecyclerItem.EmptyListItem -> EMPTY_ITEM
        }
    }

    class ClientListEmptyHolder(
        binding: ItemEmptyClientListBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class ClientItemHolder(
        binding: ItemClientBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val nameTextView = binding.nameTextView
        val phoneTextView = binding.phoneTextView
        val root = binding.rootContainer
    }
}