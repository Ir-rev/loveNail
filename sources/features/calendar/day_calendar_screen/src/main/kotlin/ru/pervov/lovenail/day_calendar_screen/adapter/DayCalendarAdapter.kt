package ru.pervov.lovenail.day_calendar_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.pervov.lovenail.day_calendar_screen.databinding.ItemEmptyEventListBinding
import ru.pervov.lovenail.day_calendar_screen.databinding.ItemEventBinding

private const val EMPTY_ITEM = 0
private const val EVENT_ITEM = 1

class DayCalendarAdapter(
    private val eventList: List<EventRecyclerItem>,
    private val onEventClick: (id: String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            EVENT_ITEM -> {
                EventItemHolder(
                    ItemEventBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }

            else -> {
                EventListEmptyHolder(
                    ItemEmptyEventListBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (eventList[position]) {
            is EventRecyclerItem.EventItem -> EVENT_ITEM
            is EventRecyclerItem.EmptyListItem -> EMPTY_ITEM
        }
    }

    override fun getItemCount(): Int = eventList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EventItemHolder -> {
                val item =
                    (eventList[position] as? EventRecyclerItem.EventItem)?.event ?: return
                holder.nameTextView.text = item.procedureId
                holder.dateTextView.text = item.dateStart.toString()
                holder.root.setOnClickListener {
                    onEventClick(item.id)
                }
            }
        }
    }

    class EventListEmptyHolder(
        binding: ItemEmptyEventListBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class EventItemHolder(
        binding: ItemEventBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val nameTextView = binding.eventNameTextView
        val dateTextView = binding.dateTextView
        val root = binding.rootContainer
    }

}